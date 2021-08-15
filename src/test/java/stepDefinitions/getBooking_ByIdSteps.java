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
		List<Integer> bookingid=new ArrayList<Integer>();
		bookingid=generic_Steps.bookingids;
		System.out.println("In getdetailby id method id is: "+bookingid.get(0));
		System.out.println("ID PRINGING getbooking******************************");
		   bookingid.stream().forEach(System.out::println);
		response=Endpoints.getBooking(bookingid.get(0));
		response.then().log().all();
		GenericResponse_Steps.response=response;
	}
			  
	@Then("response contains following values")
	public void response_contains_following_values(DataTable dataTable) {
		Map<String,String> expectedkeyvalues=dataTable.asMap(String.class, String.class);
		assertEquals(utils.getJsonPath(response,"firstname"),expectedkeyvalues.get("firstname"));
		assertEquals(utils.getJsonPath(response,"lastname"),expectedkeyvalues.get("lastname"));
	}
	
	
}
	


