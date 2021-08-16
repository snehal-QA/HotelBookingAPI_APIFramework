package generic;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.requestPayload.createBookingRequest;
import pojo.requestPayload.createTokenRequest;
import resources.apiResources;
import stepDefinitions.generic_Steps;

/*---Single point for all HTTP methods used across for performing GET,POST,PATCH,DELETE operations--- */
/*----Returns response for HTTP methods; only exception is PATCH method which returns request spec for further operations------*/
public class Endpoints {
	
	private static final String BaseURL="https://restful-booker.herokuapp.com";
		
	//Auth Endpoint
	public static Response authenticateUser(createTokenRequest createtoken) {
		apiResources resource=apiResources.valueOf("AuthEndpoint");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.body(createtoken).post(resource.getResource());
        return response;      
	}
	
	//CreateBookingEndpoint
	public static Response createBooking(createBookingRequest createbookingdata) {
		apiResources resource=apiResources.valueOf("CreateBookingEndpoint");
        RequestSpecification request = RestAssured.given();
        request.contentType("application/json").log().all();
        Response response = request.body(createbookingdata).post(resource.getResource());
        return response;
    }
	
	//GetBookingEndpoint
	public static Response getBooking(int bookingid)
	{
		apiResources resource=apiResources.valueOf("GetBooking");
		Response response=RestAssured.given()
				//.accept(ContentType.JSON)
				.pathParam("id",bookingid).log().all()
				.when()
				.get(resource.getResource());
		return response;	
	}
	
	//GetBookingIdsEndpoint
	public static Response getBookingIds()
	{
		apiResources resource=apiResources.valueOf("GetBookingIds");
		Response response=RestAssured.given()
				//.accept(ContentType.JSON)
				.when()
				.get(resource.getResource());
		return response;			
	}
	
	//Overloaded method for GetBookingIds for Query search.
	public static Response getBookingIds(String parameter, String value)
	{
		apiResources resource=apiResources.valueOf("GetBookingIds");
		Map<String,String> queryparam=new HashMap<String,String>();
		if(parameter.contains("&"))
			{
			 String parameters[]=parameter.split("&");
			 String values[]=value.split("&");
			 int i;
			 for(i=0;i<parameters.length;i++)
			 {
				 queryparam.put(parameters[i], values[i]);
			 }
			}
		else
		{
			queryparam.put(parameter, value);
		}
		Response response=RestAssured.given()
				.queryParams(queryparam)
				.log().all()
				.when()
				.get(resource.getResource());
		return response;		
	}
	
	//DeleteBookingEndpoint
	public static Response deleteBooking(int bookingid)
	{
		apiResources resource=apiResources.valueOf("DeleteBooking");
		String token_cookie="token=" + generic_Steps.token;
		Response response=RestAssured.given()
				//.accept(ContentType.JSON)
				.pathParam("id",bookingid)
				.header("Cookie",token_cookie).log().all()
				.when()
				.delete(resource.getResource());
		response.then().log().all();
		return response;	
	}
	
//	//PartialUpdateBookingEndpoint
	public static RequestSpecification partialupdateBooking(int bookingid)
	{
		apiResources resource=apiResources.valueOf("PartialUpdateBooking");
		RequestSpecification request= RestAssured.given().pathParam("id",bookingid);;
		return request;	
    }
	

}
