package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import generic.Endpoints;
import generic.log;
import generic.utils;
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
	    log.startTestCase(" GetBookings by paramenter ");
	   response=Endpoints.getBookingIds();
	   GenericResponse_Steps.response=response;
	}

	@When("User access getbookings endpoint to search booking using {string} and {string}")
	public void user_access_getbookings_endpoint_to_search_booking_using_and(String parameter, String value) {
		response=Endpoints.getBookingIds(parameter, value);
		GenericResponse_Steps.response=response;
		log.info(" GetBookings by "+ parameter);
	}
	
	@Then("countofbookings is {string} {int}")
	public void the_count_of_bookings_is(String mathexpression,int expecteddata) {
	   response.then().log().all();
	   JsonPath js= new JsonPath(response.asString());
	   int count=js.getInt("$.size()");
	   log.info("Count of booking "+ count);
	   if(mathexpression.equals(">"))
		   assertTrue(count>expecteddata);
	   else if(mathexpression.equals("="))
		   assertTrue(count==expecteddata);
	   
	}
	
	@Then("retreiving {string} from bookings using GetBookingbyId endpoint must have expected {string}")
	public void validate_correct_bookings_retreived(String parameter, String value) {
		List<String>bookingids=utils.jsonArray_getkey(response.asString(),"bookingid");
		
		for(int i=0;i<bookingids.size();i++)
		{
			response=Endpoints.getBooking(Integer.parseInt(bookingids.get(i)));
			assertEquals(value,utils.getJsonPath(response, parameter));
		}
		log.endTestCase(" GetBookings by paramenter completed ");
	}


}