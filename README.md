# Train Ticket Reservation System

This project focuses on writing test cases for an existing Train Ticket Reservation System implemented in Java. The aim is to ensure that the reservation service operates correctly, covering various scenarios such as booking, cancelling, and modifying tickets, as well as managing train journeys and schedules.

## Project Objective
The main goal is to create comprehensive test cases for the ticket reservation service to validate its functionality, reliability, and robustness. The testing process includes verifying operations like ticket booking, journey management, and handling delays in train schedules.

## Key Features
- **Test Case Development:** Developed test cases to validate different functionalities of the train reservation service.
- **Unit Testing:** Ensures that individual components like ticket booking, journey creation, and ticket cancellation work correctly.
- **Integration Testing:** Verifies that different parts of the system interact seamlessly.
- **Error Handling Tests:** Checks the system's response to incorrect inputs or invalid operations.

## Technologies Used
- **Programming Language:** Java
- **Testing Frameworks:** JUnit 5 for unit testing, Cucumber for Behavior-Driven Development (BDD) testing

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

