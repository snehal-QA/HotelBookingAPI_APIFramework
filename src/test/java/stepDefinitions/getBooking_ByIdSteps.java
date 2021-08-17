package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import generic.Endpoints;
import generic.log;
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
		log.startTestCase("GetBooking with id");
		bookingid=generic_Steps.bookingid;
		response=Endpoints.getBooking(bookingid);
		response.then().log().all();
		GenericResponse_Steps.response=response;
		log.info("Response : \n"+response.asPrettyString());
		log.endTestCase("GetBooking with id");
	}
			  
	
}
