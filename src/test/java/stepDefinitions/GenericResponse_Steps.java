package stepDefinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class GenericResponse_Steps {
	
	public static Response response;
	
	@Then("the response code is {int}")
	public void the_response_code_is(Integer statuscode) {
		response.then().log().all();
		assertEquals((Integer) response.getStatusCode(), statuscode);		

	}
	
	

}
