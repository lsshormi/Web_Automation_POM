# Sauce Demo Test Automation

## Project Overview
This test automation validates the functionality of the Sauce Demo e-commerce website (https://www.saucedemo.com/). The framework implements the Page Object Model design pattern using Selenium WebDriver with Java, TestNG for test execution, and Allure for comprehensive reporting.

## Project Structure
```
Automation_POM/
├── .idea/
├── allure-report/
├── allure-results/
├── src/
│   └── main/
│   └── test/
│        └── java/
│           ├── pages/
│           │   ├── BasePage.java
│           │   ├── CartPage.java
│           │   ├── CheckoutPage.java
│           │   ├── LoginPage.java
│           │   └── ProductPage.java
│           ├── testcases/
│               │   ├── TestCartPage.java
│               │   ├── TestCheckoutPage.java
│               │   ├── TestEndToEndFlow.java
│               │   ├── TestLoginPage.java
│               │   ├── TestLogoutFunctionality.java
│               │   └── TestProductPage.java
│               └── utilities/
│               ├── DataSet.java
│               └── DriverSetup.java
├── target/
├── .gitignore
├── pom.xml
├── testng.xml
├── External Libraries
└── Scratches and Consoles
```

## Core Components

### Page Objects
The framework includes page objects that encapsulate the functionality of each major section of the application:

- BasePage.java: Contains common methods and utilities used across all page objects
- LoginPage.java: Handles authentication functionality
- ProductPage.java: Manages product listing and sorting operations
- CartPage.java: Controls shopping cart interactions
- CheckoutPage.java: Manages the checkout process

### Test Cases
The framework includes comprehensive test coverage across multiple test classes:

- TestLoginPage: Validates authentication scenarios
- TestProductPage: Verifies product sorting and filtering functionality
- TestCartPage: Tests shopping cart operations
- TestCheckoutPage: Validates the checkout process
- TestEndToEndFlow: Performs complete user journey testing
- TestLogoutFunctionality: Verifies logout operations

### Utilities
- DataSet.java: Centralizes test data management
- DriverSetup.java: Handles WebDriver initialization and configuration

## Setup Requirements

- Java JDK 11 or higher
- Maven 3.8.x or higher
- Chrome or Firefox browser
- Chrome WebDriver or Driver matching your browser version
- Git for version control

## Installation Process

1. Clone the repository:
```bash
git clone https://github.com/lsshormi/Web_Automation_POM/
cd Automation_POM
```

2. Install project dependencies:
```bash
mvn clean install
```

3. Execute the test suite:
```bash
mvn test
```

4. Generate and view the Allure report:
```bash
mvn allure:serve
```

## Test Execution

The framework supports multiple ways to execute tests:

1. Run all tests:
```bash
mvn test
```

2. Run specific test class:
```bash
mvn test -Dtest=TestLoginPage
```

3. Run tests with Allure reporting:
```bash
mvn clean test allure:serve
```

## Reporting

The framework utilizes Allure reporting to provide detailed test execution results:

1. The allure-results directory stores raw test execution data
2. The allure-report directory contains the generated HTML report
3. Reports include:
   - Test execution statistics
   - Step-by-step test execution details
   - Screenshots of failures
   - Test execution timeline
   - Environment information

![image](https://github.com/user-attachments/assets/601af317-15ef-4724-acba-852d754194d1)

## Configuration Management

Test configuration can be modified through:

1. DriverSetup.java for browser configuration
2. DataSet.java for test data management
3. Maven profiles in pom.xml for environment-specific settings

## Best Practices for Framework Usage

1. Test Development
   - Create new page objects for new application sections
   - Extend BasePage.java for common functionality
   - Follow the existing naming conventions
   - Maintain test independence

2. Test Execution
   - Clean the project before test runs
   - Review Allure reports after execution
   - Maintain the WebDriver version compatibility

3. Maintenance
   - Regular updates to dependencies
   - Periodic review of locators
   - Documentation updates as needed


## License

This project is licensed under the MIT License - see the LICENSE.md file for details

---
