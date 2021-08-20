Feature: Retreives all bookings for user based on provided parameters

  @Regression @DeleteTestData
  Scenario Outline: User calls a webservice to retrieve booking ids <parameter> with  for existing bookings
    Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | TGeo      | IHanks   |        111 | true        | 2019-12-26 | 2019-12-29 | Breakfast       |
    When User access getbookings endpoint to search booking using "<parameter>" and "<value>"
    Then the response code is 200
    And countofbookings is ">" 0
    When user hits GetBooking endpoint with id
    And response contains following values
      | <parameter> | <value> |
    Examples: 
      | parameter                           | value                             |
      | firstname                           | TGeo                              |
      | lastname                            | IHanks                            |
      | checkin                             | 2019-12-26                        |
      | checkout                            | 2019-12-29                        |
      | firstname&lastname                  | TGeo&IHanks                       |
      | checkin&checkout                    | 2019-12-26&2019-12-29             |

  @Regression
  Scenario: User calls a webservice to get all booking ids
    When user access the getbookings endpoint
    Then the response code is 200
    And countofbookings is ">" 0
  
  @Regression
  Scenario Outline: User calls a webservice to retreive booking ids with <parameter> for bookings that do not exist
    When User access getbookings endpoint to search booking using "<parameter>" and "<value>"
    Then the response code is 200
    And countofbookings is "=" 0
    Examples: 
      | parameter                           | value                               |
      | firstname                           | AAAAA                               |
      | lastname                            | eeeBrown                            |
      | firstname&lastname                  | Tim23&Brown12                       |
      | checkin&checkout                    | 2007-01-01&2008-01-01               |
      | firstname&lastname&checkin&checkout | Jittm&Brttown&2018-01-01&2019-01-01 |
