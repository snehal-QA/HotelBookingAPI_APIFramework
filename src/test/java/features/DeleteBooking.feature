@Regression
Feature: Delete booking of user

  Background: Creates TestData for testing scenarios
    Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Larry     | Potter   |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |

  Scenario: Authorized User calls the webservice to delete existing booking
    Given I am authorized user
    When user deletes the booking using DeleteBooking endpoint with id
    Then the response code is 201
    And booking no longer exists in system with status code 404
    And the response status is "Not Found"

  Scenario: Authorized user calls webservice to delete non-existing booking
    Given I am authorized user
    And user deletes created booking via Delete Booking endpoint
    When user access DeleteEndpoint to delete non-existing deleted booking
    Then the response code is 405
    And the response status is "Method Not Allowed"

  Scenario: Delete endpoint webservice is accessed without Authorisation
    When Unauthoriozed user deletes created booking via Delete Booking endpoint
    Then the response code is 403
    And the response status is "Forbidden"

  #Scenario: Delete endpoint is accessed without providing BookingId parameter
    #Given I am authorized user
    #When user deletes booking via Delete Booking endpoint without passing BookingId
    #Then the response code is 404
    #And the response status is "Not Found"
