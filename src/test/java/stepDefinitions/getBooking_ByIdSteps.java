package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import generic.Endpoints;
import generic.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.requestPayload.createBookingRequest;


public class getBooking_ByIdSteps {
	
	int bookingid;
	Response response; 
	createBookingRequest requestbody;	
	
	@When("user hits GetBooking endpoint with id")
	public void user_hits_endpoint_with() {
		bookingid=generic_Steps.bookingid;
		response=Endpoints.getBooking(bookingid);
		response.then().log().all();
		GenericResponse_Steps.response=response;
	}
			  
	
}
	


