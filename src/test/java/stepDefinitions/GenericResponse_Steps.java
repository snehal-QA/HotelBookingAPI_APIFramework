package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import generic.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pojo.responsePayload.partialUpdateResponse;

public class GenericResponse_Steps {
	
	public static Response response;
	partialUpdateResponse resp;
	
	@Then("the response code is {int}")
	public void the_response_code_is(Integer statuscode) {
		response.then().log().all();
		assertEquals((Integer) response.getStatusCode(), statuscode);		

	}
		
	@Then("response contains following values")
	public void response_contains_following_values(DataTable dataTable) throws JsonMappingException, JsonProcessingException {
		Map<String,String> expectedkeyvalues=dataTable.asMap(String.class, String.class);
   
		utils.handleJSONObject(new JSONObject(response.asString()));
		for (Map.Entry<String, String> entry: expectedkeyvalues.entrySet()) {
			if(entry.getKey().contains("&")) {
				 String parameters[]=entry.getKey().split("&");
				 String values[]=entry.getValue().split("&");
				 for(int i=0;i<parameters.length;i++) {	
					assertEquals(values[i],utils.mapStr.get(parameters[i]));
				}				
			  
			}
			else {
				
			//System.out.println("Map Str entry " + entry.getValue() + " @@@@@@@@@@" + utils.mapStr.get(entry.getKey()));
			assertEquals(entry.getValue(),utils.mapStr.get(entry.getKey()));
			}
		} 
		
	}
	

}
