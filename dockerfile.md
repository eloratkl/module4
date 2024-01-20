# This line specifies the base image for the Docker image.
# In this case, it's using a Maven image with version 3.9.6,
# which also includes Amazon Corretto
# (a distribution of the Open Java Development Kit) version 17.
# This base image comes with Maven and Java pre-installed.

FROM maven:3.9.6-amazoncorretto-17-al2023

# Sets the working directory for the Docker image.
WORKDIR /app

# Copies the pom.xml file and the mvnw and mvnw.cmd files
# mvnw and mvnw.cmd are Maven wrapper files that allow you to run Maven
# without having to install Maven on your local machine.
# mvnw is for Unix based systems and mvnw.cmd is for Windows.
COPY mvnw mvnw.cmd pom.xml ./

# Copies the src directory from local to ./src in the Docker container.
COPY src ./src

# Runs the Maven command to build the application
# and cleans the build directory before packaging the application
RUN mvn clean package -DskipTests=true

# Execute the application when the Docker container runs
CMD ["mvn", "spring-boot:run"]
