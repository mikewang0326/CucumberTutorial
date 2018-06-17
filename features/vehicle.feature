Feature:
  Background:
    Given I am connected to the WOF database
    And I have login successfully by email "hi@gmail.com" and password "123456"

  Scenario: Subscribe a vehicle by a new plate
    Given vehicle information contains plate "KPC123" type "MA"  make "Toyota" model "Reiz" manufacture_date "2017-1-1" and fuel_type "diesel" are all valid
    And The plate does not exists in the database
    When I subscribe a vehicle by these information with a new plate
    Then The vehicle should be subscribed successfully, the information should as same as what I subscribed



  Scenario: Subscribe a vehicle by an existed plate
    Given vehicle information contains plate "KPC123" type "MA"  make "Toyota" model "Reiz" manufacture_date "2017-1-2" and fuel_type "diesel" are all valid
    And The plate exists in the database
    When I subscribe a vehicle by these information with an existed plate
    Then Screen will display vehicle subscription error information
