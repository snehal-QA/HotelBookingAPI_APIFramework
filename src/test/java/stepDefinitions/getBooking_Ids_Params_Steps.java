package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import generic.Endpoints;
import generic.testDataBuild;
import generic.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.requestPayload.createBookingRequest;

public class getBooking_Ids_Params_Steps {
	
	createBookingRequest requestbody;
	Response response;
	@When("user access the getbookings endpoint")
	public void user_access_the_getbookings_endpoint() {
	   response=Endpoints.getBookingIds();
	   GenericResponse_Steps.response=response;
	}

	@When("User access getbookings endpoint to search booking using {string} and {string}")
	public void user_access_getbookings_endpoint_to_search_booking_using_and(String parameter, String value) {
		response=Endpoints.getBookingIds(parameter, value);
		GenericResponse_Steps.response=response;
	}
	
	@Then("the count of bookings is {string}")
	public void the_count_of_bookings_is(String expectednoofbookings) {
	   response.then().log().all();
	   JsonPath js= new JsonPath(response.asString());
	   int count=js.getInt("$.size()");
	   System.out.println("Count of items is: "+count);
	   assertEquals(Integer.parseInt(expectednoofbookings),count);
	}

}
