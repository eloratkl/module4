# group5

## E-commerce Application (PentaMall) - CI/CD Pipeline Configuration

### Overview

This repository contains the CI/CD pipeline configuration for the E-commerce Application developed using Java Spring Boot. The pipeline is set up using CircleCI as the CI/CD platform, and it utilizes Git Flow with two main branches: `develop` and `release`.

### CI/CD Configuration

The pipeline consists of the following main components:

1. **CI Workflow (`ci_workflow`):**
   - Triggered on every push to the `develop` branch.
   - Executes the following steps:
     - Build: Compiles the project using Maven.
     - Test: Runs unit tests and ensures that the code passes the tests.
     - Scan: Performs security scanning using Snyk.
     - Publish: Publishes the Docker image to the Docker Hub.

2. **CI/CD Workflow (`cicd_workflow`):**
   - Triggered only on pushes to the `release` branch.
   - Inherits all the steps from the CI Workflow and adds an additional step:
     - Pull-and-Deploy: Pulls the Docker image from Docker Hub and deploys it to Heroku.

### Unit Tests

The project includes unit tests for the following classes:

- AddressControllerTest.java
- CartControllerTest.java
- CustomerControllerTest.java
- EcommerceApplicationTests.java
- ProductControllerTest.java
- TestSellerServiceforTest.java

The CI/CD pipeline ensures that there are minimally three unit tests to simulate test pass and fail scenarios. If any test fails, the CD step will not be executed in the release branch.

### CI/CD Pipeline Configuration (`config.yml`)

The `.circleci/config.yml` file defines the entire CI/CD pipeline. It includes the following main jobs:

- `build`: Compiles the project.
- `test`: Runs unit tests and ensures test pass/fail scenarios.
- `scan`: Performs security scanning using Snyk on the Docker image.
- `publish`: Publishes the Docker image to Docker Hub.
- `pull-and-deploy`: Pulls the Docker image from Docker Hub and deploys it to Heroku.

### How to Contribute

To contribute to this project, follow the Git Flow methodology:

1. Create a feature branch from `develop` for new features.
2. Create a release branch from `develop` when ready for release.
3. Merge changes into `develop` for ongoing development.
4. Merge changes into `main` when a release is ready.

### Usage

Follow the steps below to use the CI/CD pipeline:

1. Push changes to the `develop` branch for continuous integration.
2. Push changes to the `release` branch for continuous integration and continuous deployment.

### Dependencies

The project uses the following tools and technologies:

- Java Spring Boot
- Maven
- CircleCI
- Heroku

### Preview
https://github.com/catherinetkl/module4/assets/70590533/bdbff3de-d659-4e0b-81a8-2d7dd0381084







### Acknowledgments

Special thanks to the contributors and maintainers of the project. \
Feel free to open issues or contribute to the development of this project! \
*Note: Ensure that you replace placeholders such as `$DOCKER_LOGIN`, `$IMAGE_NAME`, `$HEROKU_APP_NAME`, etc., with actual values in your configuration.*
