
Feature: Ticket Reservation System

  Scenario: Creating a Trip
    Given The system is running
    And a number of cities and trains have been created in it
    When  a trip is created from city Isfahan to Tehran by Train1, departure time 2023-11-24 10:00 and arrival time  2023-11-24 12:00
    Then The system creates a new trip
    And adds it to the trip list.

