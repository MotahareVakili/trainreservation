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

public class ReserveTicketCucumberTest {
    public TicketReservationSystem system;
    public ZoneId timeZone;
    public   City Isfahan, Tehran;
    public   Train Train1;
    public Trip Trip1;
    public Ticket Ticket1;

    @Given("system is running")
    public void systemIsRunning() {
        timeZone = ZoneId.systemDefault();
        system = new TicketReservationSystemImpl(timeZone);
    }
    @And("number of cities and trains have been created in it")
    public void numberOfCitiesAndTrainsHaveBeenCreatedInIt() {
        Isfahan=new CityImpl("Isfahan");
        Tehran=new CityImpl("Tehran");
        system.addCity(Isfahan);
        system.addCity(Tehran);
        Train1=new TrainImpl("Train1",3);
        system.addTrain(Train1);
    }

    @And("there is a trip from Isfahan to Tehran by Train1 , departing at 10:00 and arriving at 12:00")
    public void thereIsATripFromIsfahanToTehranByTrain() throws  Exception {
        Instant departure= TimeManagement.createInstant("2023-11-24 10:00",timeZone);
        Instant arrival= TimeManagement.createInstant("2023-11-24 12:00",timeZone);
        Trip1=system.createTrip(Isfahan,Tehran,Train1,departure,arrival);
    }

    @When("user with the name Ali reserves a ticket for this trip")
    public void userWithTheNameAliReservesATicketForThisTrip() throws  Exception {
         Ticket1=Trip1.bookTicket("Ali");
    }

    @Then("The ticket is successfully reserved for a passenger named Ali")
    public void theTicketIsSuccessfullyReservedForAPassengerNamedAli() {
        assertEquals("Ali", Ticket1.getPassengerName());
    }

    @And("added to the list of reserved tickets for this trip.")
    public void addedToTheListOfReservedTicketsForThisTrip() {
        assertEquals(1, Trip1.getBookedTickets().size());
    }
}
