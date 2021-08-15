package pojo.requestPayload;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"firstname",
"lastname",
"totalprice",
"depositpaid",
"bookingdates",
"additionalneeds"
})
@javax.annotation.processing.Generated("jsonschema2pojo")
public class createBookingRequest {

@JsonProperty("firstname")
private String firstname;
@JsonProperty("lastname")
private String lastname;
@JsonProperty("totalprice")
private Integer totalprice;
@JsonProperty("depositpaid")
private Boolean depositpaid;
@JsonProperty("bookingdates")
private Bookingdates bookingdates;
@JsonProperty("additionalneeds")
private String additionalneeds;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("firstname")
public String getFirstname() {
return firstname;
}

@JsonProperty("firstname")
public void setFirstname(String firstname) {
this.firstname = firstname;
}

@JsonProperty("lastname")
public String getLastname() {
return lastname;
}

@JsonProperty("lastname")
public void setLastname(String lastname) {
this.lastname = lastname;
}

@JsonProperty("totalprice")
public Integer getTotalprice() {
return totalprice;
}

@JsonProperty("totalprice")
public void setTotalprice(Integer totalprice) {
this.totalprice = totalprice;
}

@JsonProperty("depositpaid")
public Boolean getDepositpaid() {
return depositpaid;
}

@JsonProperty("depositpaid")
public void setDepositpaid(Boolean depositpaid) {
this.depositpaid = depositpaid;
}

@JsonProperty("bookingdates")
public Bookingdates getBookingdates() {
return bookingdates;
}

@JsonProperty("bookingdates")
public void setBookingdates(Bookingdates bookingdates) {
this.bookingdates = bookingdates;
}

@JsonProperty("additionalneeds")
public String getAdditionalneeds() {
return additionalneeds;
}

@JsonProperty("additionalneeds")
public void setAdditionalneeds(String additionalneeds) {
this.additionalneeds = additionalneeds;
}
}
