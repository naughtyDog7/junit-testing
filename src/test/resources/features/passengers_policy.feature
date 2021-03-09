Feature: Passenger Policy
  The company follows a policy of adding and removing passengers
  depending on the passenger level and on the flight type

  Scenario: Economy Flight, Standard Passenger
    Given there is economy flight
    When we have a standard passenger
    Then you can add him to flight
    And you can remove him from the flight
    And you cannot add him more than once

  Scenario: Economy Flight, VIP Passenger
    Given there is economy flight
    When we have a VIP passenger
    Then you can add him to flight
    But you cannot remove him from the flight
    And you cannot add him more than once

  Scenario: Business Flight, Standard Passenger
    Given there is business flight
    When we have a standard passenger
    Then you cannot add him to the flight

  Scenario: Business Flight, VIP Passenger
    Given there is business flight
    When we have a VIP passenger
    Then you can add him to flight
    But you cannot remove him from the flight
    And you cannot add him more than once

  Scenario: Premium Flight, Standard Passenger
    Given there is premium flight
    When we have a standard passenger
    Then you cannot add him to the flight

  Scenario: Premium Flight, VIP Passenger
    Given there is premium flight
    When we have a VIP passenger
    Then you can add him to flight
    And you can remove him from the flight
    And you cannot add him more than once
