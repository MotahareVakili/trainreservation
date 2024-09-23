package Cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.example.impl.*;
import org.example.util.TimeManagement;
import java.time.Instant;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;


public class CreateTripCucumberTest {
    public TicketReservationSystem system;
    public ZoneId timeZone;
    public   City Isfahan, Tehran;
    public   Train Train1;
    public Trip Trip1;
    public Instant departure, arrival;

    @Given("The system is running")
    public void theSystemIsRunning() {
        timeZone = ZoneId.systemDefault();
        system = new TicketReservationSystemImpl(timeZone);
    }

    @And("a number of cities and trains have been created in it")
    public void aNumberOfCitiesAndTrainsHaveBeenCreatedInIt() {
        Isfahan=new CityImpl("Isfahan");
        Tehran=new CityImpl("Tehran");
        system.addCity(Isfahan);
        system.addCity(Tehran);
        Train1=new TrainImpl("Train1",3);
        system.addTrain(Train1);
    }

    @When("a trip is created from city Isfahan to Tehran by Train1, departure time 2023-11-24 10:00 and arrival time  2023-11-24 12:00")
    public void aTripIsCreatedFromCityIsfahanToTehranByTrain() throws Exception {
         departure= TimeManagement.createInstant("2023-11-24 10:00",timeZone);
         arrival= TimeManagement.createInstant("2023-11-24 12:00",timeZone);
         Trip1=system.createTrip(Isfahan,Tehran,Train1,departure,arrival);
    }

    @Then("The system creates a new trip")
    public void theSystemCreatesANewTrip() {
        assertEquals(Isfahan, Trip1.getOrigin());
        assertEquals(Tehran, Trip1.getDestination());
        assertEquals(Train1, Trip1.getTrain());
        assertEquals(departure, Trip1.findRealDepartureTime());
        assertEquals(arrival, Trip1.findRealArrivalTime());

    }

    @And("adds it to the trip list.")
    public void addsItToTheTripList() {
        assertTrue(system.getAllTrips().contains(Trip1));
    }
}
