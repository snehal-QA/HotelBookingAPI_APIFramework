package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import generic.Endpoints;
import generic.log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.apiResources;

public class deleteBookingSteps {
	

	//List<Integer> ids=new ArrayList<Integer>();
	int ids;
	Response response;	
	
	@When("user deletes the booking using DeleteBooking endpoint with id")
	public void user_deletes_the_booking_using_delete_booking_endpoint_with_id() {
		log.startTestCase("Delete Booking Started");
		ids=generic_Steps.bookingid;
		response=Endpoints.deleteBooking(ids);
		GenericResponse_Steps.response=response;  
	
	}

	@Then("booking no longer exists in system with status code {int}")
	public void booking_no_longer_exists_in_system(int expectedstatus) {
	    //response=Endpoints.getBooking(ids.get(0));
	    response=Endpoints.getBooking(ids);
	    log.info("Booking does not exist "+ response.getStatusCode());
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
		log.warn("User trying to delete non-existing booking");
	}

	@Then("the response status is {string}")
	public void the_response_status_is(String statusmessage) {
		log.warn("Status message : " + response.getStatusLine());
		assertTrue(response.getStatusLine().contains(statusmessage));	
	}
	
	@When("Unauthoriozed user deletes created booking via Delete Booking endpoint")
	public void unauthoriozed_user_deletes_created_booking_via_delete_booking_endpoint() {
		log.fatal("Unauthoriozed user trying to delete booking");
		//ids=generic_Steps.bookingids;
		ids=generic_Steps.bookingid;
		apiResources resource=apiResources.valueOf("DeleteBooking");
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		response=RestAssured.given()
				//.accept(ContentType.JSON)
				.pathParam("id",ids)
				.when()
				.delete(resource.getResource());
		response.then().log().all();
		GenericResponse_Steps.response=response;
		log.fatal("Response Status : "+ response.getStatusLine());
		log.endTestCase("Delete Booking Finished");
	}

	

}
