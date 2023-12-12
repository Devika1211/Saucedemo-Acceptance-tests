# Sauce Demo UI Acceptance Tests

This project contains acceptance level UI tests for the Sauce demo web E2E shopping checkout and different login process for consumers

The tests cover major sauce demo UI function with an emphasis on covering user journey

## Coverage

The following is a snapshot of the current coverage as implemented.It is planned to

-E2E Shopping Checkout and different login to Sauce demo website
-Verify E2E shopping and different login flow for users

## Setting up the project

### Pre-requisites

-Java 8 installed and environment variable `JAVA_HOME` defined
-Maven 3.5.4(minimum) installed and environment variable `M2_HOME` defined

### Building the project

Build the project using `mvn clean compile`

### Optional reporting fields

We can add some extra information to our Serenity reports with `Duser`and `Dversion`.
These will appear as `User` and `Application Version`in report.

### Running test locally

To run all test with the default configuration,use the `mvn clean verify`command.
However, in most cases we need to supply additional options to the command so that our test will run.

Other example command:
mvn clean install
mvn clean compile
mvn clean

#### Environment

We can specify the environment with the option`-Denvironment`

```
qa
test
uat
```
#### Browser

We can specify the browser for the tests to run on with the option `-Dbrowser`.
Accepted values can be found in the `DriverFactory`class,and are as follows:

```
chrome
firefox
edge
opera
IE
```

#### Cucumber Options (Running specific tagged tests)

We can specify which cucumber tags to run with the option `-Dcucumber.options`.
We can then use the `--tags` parameter to define which tags to run.(We can also use the cucumber)
"
`-Dcucumber.options="--tags @mytag"`

#### Example command

`mvn clean verify -Denvironment=qa" -Dname="Devika" -D"version="Version Number" -Dcucumber.options="--tags @mytag"`
For example
#### to run @smoke tests only in INTE environment

Not yet running in pipeline.

## Serenity Reports Access

To access the report follow below link:
Go to target > site > serenity > index.html (Right click - open in - select the browser )

## Build with

-Maven
-Serenity

## Contributing to this framework

This project is open to all pull requests.On pull requests to the master branch.

## Documentation

Documentation is in the process of being written[here](Company document link)

## Contributors

- Devika Patel
