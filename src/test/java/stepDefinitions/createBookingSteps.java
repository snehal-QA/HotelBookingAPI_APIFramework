package stepDefinitions;

import generic.Endpoints;
import generic.testDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.requestPayload.createBookingRequest;

public class createBookingSteps {
	
	Response response;
	createBookingRequest requestbody;
	
	@Given("User enters information {string} {string} {int} {string} {string} {string} {string}")
	public void user_enters_information(String firstname, String lastname, Integer totalprice
			, String depositpaid, String checkin, String checkout, String additionalneeds) {
		//Creates a payload requestbody
		requestbody=testDataBuild.createbookingpayload(firstname, lastname, totalprice, depositpaid, 
				checkin, checkout, additionalneeds);
	}

	@When("user creates a booking using CreateBookingEndpoint")
	public void user_creates_a_booking_using_post_call() {
		response=Endpoints.createBooking(requestbody);
		GenericResponse_Steps.response=response;

	}	

}
