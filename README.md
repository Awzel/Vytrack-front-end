# VyTrack_Front_End Automation
This is a demonstration of a fleet application automation testing project with UI acceptance. The framework
covers most of the testing strategy with Java cucumber page object model and retrieving the data
from JSON file. The goal is to cover most of the test cases and try to automate them all and have a
stable passing rate. Meanwhile, secure the sensitive data from publicly read, instead
they should be encrypted.


## Getting Started

### pre-requirements:
- Java 17 or higher
- Maven
    - To install Maven go to https://maven.apache.org/download.cgi
      download and install it.
    - If you are using homebrew run
      ```brew install maven``` on terminal to install maven

## Concept Included
- Parallel test runs (scenario parallel execution could be done in the near future but not the top priority.)
- Shared state across cucumber step definitions
- Page Object pattern
- Common web page interaction methods
- Cucumber expression
- Externalised test configuration
- Commonly used test utility classes
- Extent cucumber adapter 7 


## Tools
- Maven
- Cucumber-JVM
- Junit Cucumber
- Selenium Webdriver
- Selenium Grid (To be implemented)
- Docker (To be implemented)
- Jenkins (To be implemented)

## Usage
The project is only for front-end functional testing. The tests can be
utilized independently of the others using maven profiles.

To run the test , navigate to ``Vytrack-front-end`` directory and simply run
``mvn clean verify``
To run the test remotely, change driver to 'remote' in the configuration.properties file


Selenium-gird: To be implemented
## Reporting
Reports are under reports folder.
Pdf and excel reports are also available in the location mentioned above.
## Test Cases
All the test cases are written in [this](https://docs.google.com/spreadsheets/d/1E9a5syBCIpWJbewapRCqp2eXGG2uRicorV7RJRL76Ek/edit#gid=0) location







