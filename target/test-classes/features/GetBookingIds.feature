Feature: Retreives all bookings for user based on provided parameters

  @regression @DeleteTestData
  Scenario Outline: User calls a webservice to get booking ids with parameters for existing bookings
    Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | TGeo      | IHanks   |        111 | true        | 2019-12-26 | 2019-12-29 | Breakfast       |
    When User access getbookings endpoint to search booking using "<parameter>" and "<value>"
    Then the response code is 200

    #And the count of bookings is "<no.ofbookings>"
    Examples: 
      | parameter | value         | no.ofbookings |
      | firstname | TGeo+testauto |             3 |

  #| lastname                            | IHanks                          |
  #| lastname                            | IHanks                          |
  #| checkin                             | 2019-12-26                      |
  #| checkout                            | 2020-03-08                      |
  #| firstname&lastname                  | Geo&Hanks                       |
  #| checkin&checkout                    | 2019-12-26&2020-03-08           |
  #| firstname&lastname&checkin&checkout | Geo&Hanks&2019-12-26&2020-03-08 |
  @regression
  Scenario: User calls a webservice to get all booking ids
    When user access the getbookings endpoint
    Then the response code is 200
    #And the count of bookingids > 0
  #
  #@GetBookingIds_endpointtest
  #Scenario Outline: User calls a webservice to get booking ids for bookings that do not exist
    #Given Bookings exists in system for provided <parameter> and <value>
    #When User access getbookings endpoint to search booking using <parameter> and <value>
    #Then the response code is 200
    #And the count of bookings is 0
#
    #Examples: 
      #| parameter                           | value                               |
      #| firstname                           | AAAAA                               |
      #| lastname                            | eeeBrown                            |
      #| firstname&lastname                  | Tim23&Brown12                       |
      #| checkin&checkout                    | 2007-01-01&2008-01-01               |
      #| firstname&lastname&checkin&checkout | Jittm&Brttown&2018-01-01&2019-01-01 |
