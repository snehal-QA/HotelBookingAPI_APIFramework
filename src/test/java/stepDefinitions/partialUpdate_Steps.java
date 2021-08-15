package stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import generic.Endpoints;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.requestPayload.Bookingdates;
import pojo.requestPayload.createBookingRequest;
import resources.apiResources;


public class partialUpdate_Steps {
	Response response;
	
	@When("user updates the booking details {string} and {string}")
	public void user_updates_the_booking_details_and(String parameter, String value) throws JsonProcessingException {
		apiResources resource=apiResources.valueOf("PartialUpdateBooking");
		
		String token_cookie="token=" + generic_Steps.token;
		ObjectMapper objectMapper = new ObjectMapper();
		String partial_upd_body;
		Bookingdates bookingdate=new Bookingdates();
		Map<Object,Object> queryparam=new HashMap<Object,Object>();
		if(parameter.contains("&"))
			{
			 String parameters[]=parameter.split("&");
			 String values[]=value.split("&");
			 for(int i=0;i<parameters.length;i++)
			 {  
				 if(parameters[i].equals(("checkin"))) {
					 bookingdate.setCheckin(values[i]);
				 }
				else if (parameters[i].equals(("checkout"))) {
						 bookingdate.setCheckout(values[i]);
				 }
				else 
				 queryparam.put(parameters[i], values[i]);
				
			 }
			 partial_upd_body=objectMapper.writeValueAsString(queryparam);
			}
		else
		{
			queryparam.put(parameter, value);
			partial_upd_body=objectMapper.writeValueAsString(queryparam);
		}
		
		System.out.println(bookingdate.equals(null) + "checkin g.df.dsfadsfdsfadsfadsfdsaf88838488**********" + bookingdate.getCheckin());
		

	 if(bookingdate.getCheckin() != " " && bookingdate.getCheckout() != " " ) {
	
		response=Endpoints.partialupdateBooking(generic_Steps.bookingid)
				.header("Content-Type", "application/json")
				.header("Cookie",token_cookie)
				.body(partial_upd_body)
				.log().all()
				.when().patch(resource.getResource());
		response.then().log();
		 }
	 else
	 {
		 
		 System.out.println("In ELSE statement" + bookingdate.equals(null) + "checkin g.df.dsfadsfdsfadsfadsfdsaf88838488**********" + bookingdate.getCheckin());
			//MapObject test=new Object();
			// test=bookingdate;
		    createBookingRequest requestbody= new createBookingRequest();
		    requestbody.setBookingdates(bookingdate);
			response=Endpoints.partialupdateBooking(generic_Steps.bookingid)
					.header("Content-Type", "application/json")
					.header("Cookie",token_cookie)
					.body(requestbody)
					.log().all()
					.when().patch(resource.getResource());
			response.then().log();
		 
	 }
		System.out.println("&&&&***********         partial response   ************* &&&&\n" + response.asString() );
		
		GenericResponse_Steps.response=response;
	}

}

