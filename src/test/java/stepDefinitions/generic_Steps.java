package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import generic.Endpoints;
import generic.testDataBuild;
import generic.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import pojo.requestPayload.createBookingRequest;
import pojo.requestPayload.createTokenRequest;

public class generic_Steps {
	
	public static List<Integer> bookingids=new ArrayList<Integer>();
	public static String token;
	@Given("we create test data as follows:")
	public void we_create_booking_data_as_follows(DataTable dataTable) {
		
		createBookingRequest requestbody;
		bookingids.clear();
		Response response;
		//Reading the datatable as key:columnname &value:columnvalue
		 List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);
		 int i;
		 for(i=0;i<data.size();i++)
		 {			 
			 //Create Bookings
			 requestbody=testDataBuild.createbookingpayload(data.get(i).get("firstname"), data.get(i).get("lastname"), 
					 Integer.parseInt(data.get(i).get("totalprice")),data.get(i).get("depositpaid"),
					 data.get(i).get("checkin"), data.get(i).get("checkout"), data.get(i).get("additionalneeds"));
			 response=Endpoints.createBooking(requestbody);
			 //Collect all booking ids 
			 bookingids.add(Integer.parseInt(utils.getJsonPath(response,"bookingid")));		 
		 }
	}
	
	@Given("I am authorized user")
	public void user_is_authorized() throws IOException {
		Response response;
		createTokenRequest tokenpayload=new createTokenRequest();
		
		tokenpayload.setUsername(utils.getPropertyValue("auth_username"));
		tokenpayload.setPassword(utils.getPropertyValue("auth_password"));
		
		response=Endpoints.authenticateUser(tokenpayload);
		System.out.println("............"+response.asString());
		token=utils.getJsonPath(response,"token");
	}

}
