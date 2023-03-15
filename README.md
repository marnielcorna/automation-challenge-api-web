# Challenge automation framework

This is a Java-based automation framework built using Selenium, Cucumber and TestNG. The framework addresses automated testing for API and the UI of the application.

## Getting started
### Prerequisites
1. Apache-maven-3.9.0
2. JDK 19.0.2
3. IDE (IntelliJ IDEA recommended)

### Instalation

1. Clone the repository to your local machine:
   >https://github.com/marnielcorna/api-web-automation.git
2. Import the project into your IDE.
3. Run `mvn clean install` to install all the dependencies.
4. Plugins: 
   - Gherkin 
   - Cucumber for Java

## Running Tests
1. Run all tests `mvn test`
2. Run a specific tag in `cmd` i.e. `mvn test -Dcucumber.filter.tags="@api_test"`
3. The output report is located at: 
`target/cucumber-html-reports/overview-features.html`

## Structure of the project
- src
  - main
    - java
      - pageobjects
- src
  - java
    - StepDefinitions
      - Apisteps
      - Websteps
  - resources
    - features
      - api
        - apitest.feature (`Scenarios`)
      - web
        - webtest.feature (`Scenarios`)
        - 