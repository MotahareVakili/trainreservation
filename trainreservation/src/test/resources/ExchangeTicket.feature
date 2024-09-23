Feature: Ticket Reservation System

  Scenario: user exchanges his ticket (for the same origin and destination)
    Given Ticket Reservation system is running
    And  number of cities and trains have been created in system
    And  there is a trip from Isfahan to Tehran by Train1 , from 10 to 12 and and another trip from Isfahan to Tehran by Train2 from 11 to 13
    And  user with the name Ali reserves  ticket1 for Trip1
    When user with the name Ali exchanges Ticket1 for Trip2.
    Then Ticket1 is canceled for a passenger named Ali
    And  Ticket2 is created for Ali for Trip2
    And  Ticket1 is added to  list of canceled tickets for Trip1
    And  Ticket2 is added to the list of saved tickets for Trip2


