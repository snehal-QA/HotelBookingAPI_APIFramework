package resources;

public enum apiResources {
	
	//Resources
	AuthEndpoint("/auth"),
	CreateBookingEndpoint("/booking"),
	GetBooking("/booking/{id}"),
	GetBookingIds("/booking"),
	PartialUpdateBooking("/booking/{id}"),
	DeleteBooking("/booking/{id}");
	
private String resource;
	
	apiResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
