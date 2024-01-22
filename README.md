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
<video src='https://github.com/catherinetkl/module4/assets/70590533/d32986e2-348d-4221-9356-d4a3a6bea7c5' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/123daf0d-9925-4241-97f4-411896632f0b' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/f88ff939-c56d-4bf4-9923-944082a5b47d' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/512b15b5-0021-438b-9607-da646b69474f' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/ebbb3ca8-8d95-4018-b5e8-bd96eef70ba1' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/ea5b7aee-bc25-463d-8a27-1397066265fc' width=180></video>
<video src='https://github.com/catherinetkl/module4/assets/70590533/384e0500-bd9d-4e68-a154-6e3c1721582c' width=180></video>
<img width="1177" alt="Screenshot 2024-01-22 at 2 18 51 PM" src="https://github.com/catherinetkl/module4/assets/70590533/1f245fe2-6ba9-4db7-a126-51ce446b8e08">
<img width="1180" alt="Screenshot 2024-01-22 at 2 19 35 PM" src="https://github.com/catherinetkl/module4/assets/70590533/d4e5f737-b55c-4248-beb9-5009229df7c8">
<img width="1177" alt="Screenshot 2024-01-22 at 2 20 19 PM" src="https://github.com/catherinetkl/module4/assets/70590533/9e9649b3-e818-492f-a781-ed748107b32f">
<img width="1179" alt="Screenshot 2024-01-22 at 2 20 40 PM" src="https://github.com/catherinetkl/module4/assets/70590533/6263785e-076e-416a-8866-b9aa3ab890c8">
<img width="1182" alt="Screenshot 2024-01-22 at 2 21 04 PM" src="https://github.com/catherinetkl/module4/assets/70590533/cbcdc05e-5f93-49d1-a338-21d72281cf37">
<img width="1178" alt="Screenshot 2024-01-22 at 2 23 03 PM" src="https://github.com/catherinetkl/module4/assets/70590533/8df41586-7b31-4b67-8f4b-66ac77171602">
<img width="1183" alt="Screenshot 2024-01-22 at 2 23 51 PM" src="https://github.com/catherinetkl/module4/assets/70590533/41baba87-bfeb-44ba-8f41-e359ce7a7d17">
<img width="1177" alt="Screenshot 2024-01-22 at 2 24 19 PM" src="https://github.com/catherinetkl/module4/assets/70590533/a159121f-f95a-46b7-8b85-37dead5da7c8">
<img width="1174" alt="Screenshot 2024-01-22 at 2 24 53 PM" src="https://github.com/catherinetkl/module4/assets/70590533/b6f4dffd-6181-4959-a212-111107acaa3c">
<img width="1170" alt="Screenshot 2024-01-22 at 2 25 20 PM" src="https://github.com/catherinetkl/module4/assets/70590533/fb77f9ab-8d63-4994-aa20-61221cc0b391">
<img width="1168" alt="Screenshot 2024-01-22 at 2 25 43 PM" src="https://github.com/catherinetkl/module4/assets/70590533/a4950b28-20f3-40cd-9062-e65f31f2a499">
<img width="1174" alt="Screenshot 2024-01-22 at 2 26 20 PM" src="https://github.com/catherinetkl/module4/assets/70590533/58a93eea-7cdb-4471-a4af-b90cc2bf388e">
<img width="1172" alt="Screenshot 2024-01-22 at 2 26 52 PM" src="https://github.com/catherinetkl/module4/assets/70590533/d2ec4cb9-52a9-4ac9-bcd2-b51cc8dc9f0e">
<img width="1173" alt="Screenshot 2024-01-22 at 2 27 24 PM" src="https://github.com/catherinetkl/module4/assets/70590533/3cabc8b1-d1d4-486a-a4f4-babc6b727ff2">
<img width="1175" alt="Screenshot 2024-01-22 at 2 27 55 PM" src="https://github.com/catherinetkl/module4/assets/70590533/5957d411-95ec-4424-a012-5855f4d983be">
<img width="1172" alt="Screenshot 2024-01-22 at 2 28 21 PM" src="https://github.com/catherinetkl/module4/assets/70590533/d3ad6d85-3897-46e5-92a7-1852d7f4c911">

### Acknowledgments

Special thanks to the contributors and maintainers of the project. \
[Siti](https://github.com/sitiaminahak), [Zhen Jian](https://github.com/zhenjianlee), [Sariha](https://github.com/sareeha), [Saranya](https://github.com/saran2585)\
Feel free to open issues or contribute to the development of this project! \
*Note: Ensure that you replace placeholders such as `$DOCKER_LOGIN`, `$IMAGE_NAME`, `$HEROKU_APP_NAME`, etc., with actual values in your configuration.*
