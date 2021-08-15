package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import generic.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.apiResources;

public class deleteBookingSteps {
	

	List<Integer> ids=new ArrayList<Integer>();
	Response response;	
	
	@When("user deletes the booking using DeleteBooking endpoint with id")
	public void user_deletes_the_booking_using_delete_booking_endpoint_with_id() {
		ids=generic_Steps.bookingids;
		   List<Response> r1=ids.stream().map(j -> Endpoints.deleteBooking(j)).collect(Collectors.toList());
		   GenericResponse_Steps.response=r1.get(0);
		   System.out.println("ID PRINGING ******************************");
		   ids.stream().forEach(System.out::println);
		   response=r1.get(0);
		   
		
	}

	@Then("booking no longer exists in system with status code {int}")
	public void booking_no_longer_exists_in_system(int expectedstatus) {
	    response=Endpoints.getBooking(ids.get(0));
	   // ids.remove(0);
	    assertEquals(expectedstatus, response.getStatusCode());
	}

	@Given("user deletes created booking via Delete Booking endpoint")
	public void user_deletes_created_booking_via_delete_booking_endpoint() {
		user_deletes_the_booking_using_delete_booking_endpoint_with_id();  
	}

	@When("user access DeleteEndpoint to delete non-existing deleted booking")
	public void user_access_delete_endpoint_to_delete_already_deleted_booking() {
		user_deletes_the_booking_using_delete_booking_endpoint_with_id();
		//ids.remove(0);
	}

	@Then("the response status is {string}")
	public void the_response_status_is(String statusmessage) {
		assertTrue(response.getStatusLine().contains(statusmessage));	
	}
	
	@When("Unauthoriozed user deletes created booking via Delete Booking endpoint")
	public void unauthoriozed_user_deletes_created_booking_via_delete_booking_endpoint() {
		ids=generic_Steps.bookingids;
		apiResources resource=apiResources.valueOf("DeleteBooking");
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		response=RestAssured.given()
				//.accept(ContentType.JSON)
				.pathParam("id",ids.get(0))
				.when()
				.delete(resource.getResource());
		response.then().log().all();
		GenericResponse_Steps.response=response;
	}

	

}
