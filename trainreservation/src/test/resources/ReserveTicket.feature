Feature: Ticket Reservation System

  Scenario: user reserves a ticket for a trip
    Given  system is running
    And  number of cities and trains have been created in it
    And  there is a trip from Isfahan to Tehran by Train1 , departing at 10:00 and arriving at 12:00
    When user with the name Ali reserves a ticket for this trip
    Then The ticket is successfully reserved for a passenger named Ali
    And  added to the list of reserved tickets for this trip.

