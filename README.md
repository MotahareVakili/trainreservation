# Train Ticket Reservation System

This project is a simple Train Ticket Reservation System implemented in Java. It provides functionality for managing trains, cities, journeys, and tickets, ensuring that all operations are executed correctly within the system.

## Project Objective
The main goal is to simulate a ticket reservation system, allowing users to create train journeys, reserve tickets, cancel tickets, and manage travel schedules using an intuitive and structured Java-based implementation.

## Key Features
- **Train and City Management:** Define and manage multiple trains and cities within the reservation system.
- **Journey Management:** Create and manage journeys with defined departure and arrival times, including the ability to update or cancel journeys.
- **Ticket Reservation:** Reserve, cancel, and modify tickets for different journeys.
- **Handling Delays:** Introduce delays in journeys and handle changes accordingly.

## Technologies Used
- **Programming Language:** Java
- **Testing Frameworks:** JUnit 5, Cucumber for BDD (Behavior-Driven Development)

## How to Use
1. **Initialize the System:**
   - Create an instance of the `TicketReservationSystemImpl` class.
   - Set up initial data for cities, trains, and schedules.
2. **Manage Journeys:**
   - Add journeys with departure and arrival times.
   - Retrieve and view existing journeys.
   - Cancel or delay journeys as needed.
3. **Ticket Operations:**
   - Reserve tickets for a specific journey.
   - Cancel or change ticket details.

## Testing
- **Test Scenarios:** Five different test scenarios have been designed to validate the core functionalities of the reservation system.
- **BDD Testing:** Three Gherkin-based scenarios are implemented to test the system's end-to-end functionality.
- **JUnit Testing:** Includes 10 test methods covering key operations of the system.

## How to Run
- Compile and run the Java classes using the command line or an IDE like IntelliJ IDEA or Eclipse.
- Test cases can be executed using JUnit 5 and Cucumber.

