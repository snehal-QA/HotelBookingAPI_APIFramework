@Regression
Feature: Retreives Booking deatils for a booking

  @DeleteTestData
  Scenario: User calls the webservice to retreive details of booking
    Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Harry     | Potter   |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
    When user hits GetBooking endpoint with id
    Then the response code is 200
    And response contains following values
      | firstname | Harry  |
      | lastname  | Potter |
