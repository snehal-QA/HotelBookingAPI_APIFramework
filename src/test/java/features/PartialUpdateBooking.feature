@DeleteTestData
Feature: Updates details of the existing bookings
Background: Creates Booking Testdata
 Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Carry     | Trotller |        111 | true        | 2018-01-01 | 2019-01-01 | Breakfast       |

  @Regression
  Scenario Outline: Authorized User calls the webservice to update existing booking
    Given I am authorized user
    When user updates the booking details "<parameter>" and "<value>"
    Then the response code is 200
    And response contains following values 
      | <parameter> | <value> |

    Examples: 
      | parameter | value  |
      | firstname | Sherry |
      | lastname           | High                  |
      | totalprice         |                   332 |
      | depositpaid        | false                 |
      | firstname&lastname | Terry&Low             |
 
      
      #Bug:As per documentation authorization is optional; however testing says its mandatory
      Scenario: User access PartialUpdate Endpoint without authorization (optional)
      When user updates the booking details "firstname" and "Tommy"
      Then the response code is 200			
      														
      
