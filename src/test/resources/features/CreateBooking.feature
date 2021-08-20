Feature: Creates a new booking

  @Regression
  Scenario Outline: User can create a booking
    Given User enters information "<firstname>" "<lastname>" <totalprice> "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
    When user creates a booking using CreateBookingEndpoint
    Then the response code is 200
    
    Examples: 
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Faker      | Brown    |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
      
   
