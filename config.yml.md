# Specifies version of the CircleCI pipeline configuration syntax
version: 2.1

# Orbs are reusable packages of configuration elements, including jobs, commands, and executors
orbs:
  snyk: snyk/snyk@2.0.2
  docker: circleci/docker@2.4.0
  heroku: circleci/heroku@2.0.0

# Declare a job called build
# It builds the java application using Maven
# Using container image cimg/openjdk:17.0.7
# which has Java 17 installed
# It also takes in the Docker Hub username and password as parameters
# it packages the application into a jar file skipping the tests
jobs:
  build:
    docker:
      - image: cimg/openjdk:17.0.7
    parameters:
      dockerhub-username:
        type: string
      dockerhub-password:
        type: string
    steps:
      - checkout
      - run: mvn clean package -DskipTests=true

  # This job runs the tests and rebuilds the project
  # uses 2 images, one for Java and the other for PostgreSQL
  # Waits for the PostgreSQL database to be ready before running the tests
  # Uses environment variables to configure the PostgreSQL database service
  # Port, database name, username, password and URL on which the database is running
  # The database host authentication method is set to trust
  # which means that no password is required to connect to the database
  test:
    docker:
      - image: cimg/openjdk:17.0.7
      - image: cimg/postgres:15.2-postgis
        environment:
          POSTGRES_PORT: ${PORT}
          POSTGRES_DB: ${DATABASE_NAME}
          POSTGRES_USER: ${DATABASE_USERNAME}
          POSTGRES_PASSWORD: ${DATABASE_PASSWORD}
          POSTGRES_URL: ${POSTGRES_DATABASE_URL}
          POSTGRES_HOST_AUTH_METHOD: trust

    # pulling source code from github repository into test job's workspace
    # Dockerize is a utility that helps you to set up your applications
    # in Docker containers, wait tells Dockerize to wait up to 1 minute
    # for the PostgreSQL database to be ready before running the tests
    steps:
      - checkout
      - run: dockerize -wait tcp://localhost:5432 -timeout 1m
      - run:
          name: Run Tests and rebuild the project
          command: mvn clean install

  # This job runs Snyk scan for vulnerabilities on the Docker image
  scan:
    docker:
      - image: cimg/openjdk:17.0.7
    steps:
      - setup_remote_docker
      - checkout
      # Build the Docker image
      - run: docker build -t $DOCKER_LOGIN/$IMAGE_NAME:latest .
      # Login to Docker Hub using environment variables
      - run: echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_LOGIN}" --password-stdin
      # Push the image to Docker Hub
      - run: docker push $DOCKER_LOGIN/$IMAGE_NAME:latest
      # Run Snyk scan on the Docker image
      - snyk/scan:
          docker-image-name: $DOCKER_LOGIN/$IMAGE_NAME:latest
          severity-threshold: critical

  # This job builds the Docker image and pushes it to Docker Hub
  build-and-push:
    # executor is a Docker image that contains a pre-installed Docker client
    executor: docker/docker

    # set up remote environment for Docker commands
    # needed for building, pushing and pulling Docker images
    steps:
      - setup_remote_docker
      - checkout
      # Command provided by Docker orb to check for semantic errors in Dockerfiles
      - docker/check
      # Build the Docker image
      # both the image name and tag are set using environment variables
      # and pipeline.git.tag is the tag of the git commit
      - docker/build:
          image: $DOCKER_LOGIN/$IMAGE_NAME
          tag: <<pipeline.git.tag>>
      # Push the Docker image to Docker Hub
      - docker/push:
          image: $DOCKER_LOGIN/$IMAGE_NAME
          tag: <<pipeline.git.tag>>

  pull-and-deploy:
    docker:
      - image: cimg/openjdk:17.0.7
    steps:
      - setup_remote_docker
      - heroku/install
      - run:
          name: Pull Docker Image From Docker Hub
          command: docker pull $DOCKER_LOGIN/$IMAGE_NAME:latest
      - run:
          name: Heroku Container Push With Image From Docker Hub
          command: |
            heroku container:login
            docker tag $DOCKER_LOGIN/$IMAGE_NAME:latest registry.heroku.com/$HEROKU_APP_NAME/web
            # format --- docker tag <image name from docker hub> registry.heroku.com/<app-name>/web
            docker push registry.heroku.com/$HEROKU_APP_NAME/web
            # format --- docker push registry.heroku.com/<app-name>/web
            heroku container:release web -a $HEROKU_APP_NAME

workflows:
  # Declare a workflow that runs on every push to the main branch
  simple_workflow:
    jobs:
      # Build job triggered on changes to the main branch
      - build:
          dockerhub-username: $DOCKER_LOGIN
          dockerhub-password: $DOCKER_PASSWORD
          filters:
            branches:
              only: main

      # Test job runs after the build job and only on the main branch
      - test:
          # to let `test` job run after the `build` job
          requires:
            - build
          filters:
            branches:
              only: main

      - scan:
          requires:
            - test

      # Build-and-push job runs after the test job
      - build-and-push:
          requires:
            - test

      # Deploy job runs after the build-and-push job
      - pull-and-deploy:
          requires:
            - build-and-push
