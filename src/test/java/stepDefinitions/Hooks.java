package stepDefinitions;

import java.io.IOException;

import generic.Endpoints;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import generic.utils;

public class Hooks {
	
	/*
	 * @Before("@CreateTestData") public void Createtestdata() { //Create a random
	 * booking. createBookingRequest requestbody=new createBookingRequest();
	 * requestbody=testDataBuild.createbookingpayload("Nancy","Jones",450,"true",
	 * "2020-07-01","2021-08-30", "Drinks"); Response
	 * response=Endpoints.createBooking(requestbody);
	 * bookingid=Integer.parseInt(utils.getJsonPath(response,"bookingid")); }
	 */
	@Before()
	public void createRestAssuredconn()
	{
		String baseurl="";
		try {
			baseurl = utils.getPropertyValue("BaseUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.baseURI=baseurl;		
	}
	
	@After("@DeleteTestData")
	public void deleteTestData() throws IOException
	{
		generic_Steps.user_is_authorized();
		Endpoints.deleteBooking(generic_Steps.bookingid);
	}

}
