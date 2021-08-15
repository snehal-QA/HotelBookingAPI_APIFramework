Feature: Retreives all bookings for user based on provided parameters

  Background: Prepare Test data
    Given we create test data as follows:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | TGeo      | IHanks   |        111 | true        | 2019-12-26 | 2019-12-29 | Breakfast       |
      | TGeo      | ITurtle  |        111 | true        | 2019-12-26 | 2019-12-29 | Breakfast       |
      | TGeo      | IHanks   |        111 | true        | 2020-03-03 | 2020-03-08 | Breakfast       |
      | Dim       | IHanks   |        111 | true        | 2020-03-03 | 2020-03-08 | Breakfast       |
      | Dim       | IHanks   |        111 | true        | 2020-03-08 | 2020-03-09 | Gym             |

  @regression @testdataprep
  Scenario Outline: User calls a webservice to get booking ids with parameters for existing bookings
    When User access getbookings endpoint to search booking using "<parameter>" and "<value>"      
    Then the response code is 200
    And the count of bookings is "<no.ofbookings>"
   Examples: 
    | parameter | value  | no.ofbookings |
      | firstname | TGeo   |             3 |
      | lastname  | IHanks |             4 |
    #| checkin                             | 2019-12-26                      |              2 |
    #| checkout                            | 2020-03-08                      |              2 |
    #| firstname&lastname                  | Geo&Hanks                       |              2 |
    #| checkin&checkout                    | 2019-12-26&2020-03-08          |              1 |
    #| firstname&lastname&checkin&checkout | Geo&Hanks&2019-12-26&2020-03-08 |              1 |
    
    
    #@regression
  #Scenario: User calls a webservice to get all booking ids
    #When user access the getbookings endpoint
    #Then the response code is 200
  #And jsonarray with bookingids is returned
  #
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
