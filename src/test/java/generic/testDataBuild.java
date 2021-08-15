package generic;

import java.io.IOException;

import pojo.requestPayload.Bookingdates;
import pojo.requestPayload.createBookingRequest;
import pojo.requestPayload.createTokenRequest;

public class testDataBuild {
	
	public static createBookingRequest createbookingpayload(String firstname, String lastname, Integer totalprice,
			String depositpaid, String checkin, String checkout, String additionalneeds) {
		//---------Creates a Payload data: createBookingRequest object ------------------------------//		   
	    Bookingdates bookingdate=new Bookingdates();
		bookingdate.setCheckin(checkin);
		bookingdate.setCheckout(checkout);
		
		createBookingRequest requestbody= new createBookingRequest();
		requestbody.setFirstname(firstname);
		requestbody.setLastname(lastname);
		requestbody.setDepositpaid(Boolean.parseBoolean(depositpaid));
		requestbody.setBookingdates(bookingdate);
		requestbody.setAdditionalneeds(additionalneeds);
		requestbody.setTotalprice(totalprice);
		return requestbody;	
	}
	
}
