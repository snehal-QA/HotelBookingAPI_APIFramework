package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import generic.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pojo.requestPayload.Bookingdates;

public class GenericResponse_Steps {
	
	public static Response response;
	
	@Then("the response code is {int}")
	public void the_response_code_is(Integer statuscode) {
		response.then().log().all();
		assertEquals((Integer) response.getStatusCode(), statuscode);		

	}
	
	@Then("response contains following values")
	public void response_contains_following_values(DataTable dataTable) {
		Map<String,String> expectedkeyvalues=dataTable.asMap(String.class, String.class);
		System.out.println("Response for expectedkeyvalues .............*******....."+ expectedkeyvalues.size()); expectedkeyvalues.keySet().forEach(System.out::println);
		System.out.println("Response for partial booking .............*******....." + response.asString());
		// Need to getkey in utils.getJsonPath(response,"firstname")
		for (Map.Entry<String, String> entry: expectedkeyvalues.entrySet()) {
			if(entry.getKey().contains("&")) {
				 String parameters[]=entry.getKey().split("&");
				 String values[]=entry.getValue().split("&");
				 int i=0;
				 Bookingdates bookingdate=new Bookingdates();
				for(i=0;i<parameters.length;i++) {	
					System.out.println("Parameters .............*******....." + parameters[i] + "^^^^^^^^^^^"+values[i]);
					/*
					 * if(parameters[i].equals(("checkin"))) { bookingdate.setCheckin(values[i]);
					 * System.out.println("Booking date ***Parameters .............*******....." +
					 * bookingdate.getCheckin());
					 * System.out.println("Booking date ***Bookingdate .............*******....." +
					 * utils.getJsonPath(response,"firstname"));
					 * 
					 * assertEquals(values[i],utils.getJsonPath(response,"bookingdate.checkin")); }
					 * else if (parameters[i].equals(("checkout"))) {
					 * bookingdate.setCheckout(values[i]);
					 * assertEquals(values[i],utils.getJsonPath(response,bookingdate.getCheckout()))
					 * ; }
					 */
				   assertEquals(values[i],utils.getJsonPath(response,parameters[i]));
				}				
				
			}
			else {
			assertEquals(expectedkeyvalues.get(entry.getKey()),utils.getJsonPath(response,entry.getKey()));
			}
		}
		
	 
		
	}
	
	

}
