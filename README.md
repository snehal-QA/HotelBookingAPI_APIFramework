# Project

This is  demo project to implement rest api testing using the below libraries.



## Tools/Framework/Libraries

- JDK 11

- Maven - build tool

- Junit - test runner

- Cucumber - BDD/Gherkin style feature files

- Rest assured - Rest api Java library



## Run the tests using maven

```shell script

mvn clean test -Dcucumber.options="--tags @Regression"
```


Reports: Cucumber reports>> target/jsonReports/cucumber-report.html



## BDD Cucumber(Feature file / Step definition)

BDD has a feature file to invoke the step definitions:

- Scenarios are created in feature file as per the requirements,step in feature file has to match a step definition in class file;

- Following the BDD practices for coding;



####src/test/java



###Structure: 

-Cucumber.Options: 

     This is heart of Cucumber; used cucumber-junit

- Hooks: 

	-@Before Annotation is used (This will execute before each scenario),this is the right place to set up the URI (endpoint) which will be called by HTTP request;

	-@After Annotation is used for deleting testdata created for scenarios

	

-FeatureFiles:
     
     Every Feature file is mapped to Step definition.java file >> Assertions on specific Then methods are perfomed in steps file
     -createBooking.Feature: mapped to createBookingsteps : Creates and validates bookings for passed data through scenario-outline: examples
     -deleteBooking.Feature: mapped to deleteBookingSteps: Deletes booking based on different scenarios
     -GetBooking.feature: mapped to getBooking_ByIdsteps: Retrieves booking details based on bookingid
     -GetBookingIds.feature: mapped to getBooking_Ids_Params_steps: Retrieves bokingid/ids based on query parameters & allBookings
     -PartialUpdateBooking.feature: mapped to partial_update_steps : Updates booking based on various scenarios

  	

-StepDefinitions:

	-Mapping to features is as noted above

	-To perform code resuability:

	Generic_steps.java: Given & When steps that are common across are various feature files are registered.

	GenericResponse.steps: Then statements that are common across various feature files are handled dynamically



	

###Generic:

	-Endpoints.java: All Httpmethods(POST,GET,PATCH,DELETE) required across Stepdefinition files are handled in there.

	-testDataBuild: This is used to create a java object for request/response payloads through Pojo implementation

	-utils.java: Reading from properties file, reading json data, reading json array

	-log.java: To test execution flow for better debugging

	

####src/main/java

    -pojo classes for request & response payloads

    -Enum files for Endpoints

    -Config files for Authenticationdetails & BaseURL