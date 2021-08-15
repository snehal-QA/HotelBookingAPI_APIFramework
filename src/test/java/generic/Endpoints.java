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

public class Endpoints {
	
	private static final String BaseURL="https://restful-booker.herokuapp.com";
		
	//Auth Endpoint
	public static Response authenticateUser(createTokenRequest createtoken) {
		apiResources resource=apiResources.valueOf("AuthEndpoint");
		RestAssured.baseURI = BaseURL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.body(createtoken).post(resource.getResource());
        return response;      
	}
	
	//CreateBookingEndpoint
	public static Response createBooking(createBookingRequest createbookingdata) {
		apiResources resource=apiResources.valueOf("CreateBookingEndpoint");
        RestAssured.baseURI = BaseURL;
        RequestSpecification request = RestAssured.given();
        request.contentType("application/json").log().all();
        Response response = request.body(createbookingdata).post(resource.getResource());
        return response;
    }
	
	//GetBookingEndpoint
	public static Response getBooking(int bookingid)
	{
		apiResources resource=apiResources.valueOf("GetBooking");
		RestAssured.baseURI=Endpoints.BaseURL;
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
		RestAssured.baseURI=Endpoints.BaseURL;
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
			 for(i=0;i<=parameters.length;i++)
			 {
				 queryparam.put(parameters[i], values[i]);
			 }
			}
		else
		{
			queryparam.put(parameter, value);
		}
		
		RestAssured.baseURI=Endpoints.BaseURL;
		Response response=RestAssured.given()
				.queryParams(queryparam)
				.when()
				.get(resource.getResource());
		return response;		
	}
	
	//DeleteBookingEndpoint
	public static Response deleteBooking(int bookingid)
	{
		apiResources resource=apiResources.valueOf("DeleteBooking");
		RestAssured.baseURI=Endpoints.BaseURL;
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
		RestAssured.baseURI=Endpoints.BaseURL;
		RequestSpecification request= RestAssured.given().pathParam("id",bookingid);
//		Response response=RestAssured.given()
//			//.accept(ContentType.JSON)
//				.pathParam("id",bookingid).log().all()
//				.when()
//				.patch(resource.getResource());
		return request;	
    }
	

}
