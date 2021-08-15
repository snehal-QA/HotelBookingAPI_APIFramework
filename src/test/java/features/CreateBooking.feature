Feature: Creates a new booking

  @regression
  Scenario Outline: User can create a booking
    Given User enters information "<firstname>" "<lastname>" <totalprice> "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
    When user creates a booking using CreateBookingEndpoint
    Then the response code is 200
    
    Examples: 
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Tim       | Brown    |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
      | Yamm      | Jam      |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
      
      
  #@CreateBooking_endpointtest
  #Scenario: User creates booking with no details
    #Given user access the createbooking endpoint with no payload data
    #When user creates a booking using POST call
    #Then the status code is 500
    #And response header Server is "Cowboy"
