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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeTicketCucumberTest {

    public TicketReservationSystem system;
    public ZoneId timeZone;
    public City Isfahan, Tehran;
    public Train Train1,Train2;
    public Trip Trip1,Trip2;
    public Ticket Ticket1,Ticket2;

    @Given("Ticket Reservation system is running")
    public void ticketReservationSystemIsRunning() {
        timeZone = ZoneId.systemDefault();
        system = new TicketReservationSystemImpl(timeZone);
    }

    @And("number of cities and trains have been created in system")
    public void numberOfCitiesAndTrainsHaveBeenCreatedInSystem() {
        Isfahan=new CityImpl("Isfahan");
        Tehran=new CityImpl("Tehran");
        system.addCity(Isfahan);
        system.addCity(Tehran);
        Train1=new TrainImpl("Train1",3);
        Train2=new TrainImpl("Train2" ,3);
        system.addTrain(Train1);
        system.addTrain(Train2);
    }

    @And("there is a trip from Isfahan to Tehran by Train1 , from 10 to 12 and and another trip from Isfahan to Tehran by Train2 from 11 to 13")
    public void thereIsATripFromIsfahanToTehranByTrain() throws Exception {
        Instant departure1= TimeManagement.createInstant("2023-11-24 10:00",timeZone);
        Instant arrival1= TimeManagement.createInstant("2023-11-24 12:00",timeZone);
        Instant departure2= TimeManagement.createInstant("2023-11-24 11:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-24 13:00",timeZone);
        Trip1=system.createTrip(Isfahan,Tehran,Train1,departure1,arrival1);
        Trip2=system.createTrip(Isfahan,Tehran,Train2,departure2,arrival2);
    }

    @And("user with the name Ali reserves  ticket1 for Trip1")
    public void userWithTheNameAliReservesTicketForTrip() throws  Exception {
        Ticket1=Trip1.bookTicket("Ali");
    }

    @When("user with the name Ali exchanges Ticket1 for Trip2.")
    public void userWithTheNameAliExchangesTicketForTrip() throws Exception {
        Ticket2=Ticket1.exchangeTicket(Trip2);
    }

    @Then("Ticket1 is canceled for a passenger named Ali")
    public void ticketIsCanceledForAPassengerNamedAli() {
        assertTrue(Ticket1.isCancelled());
    }

    @And("Ticket2 is created for Ali for Trip2")
    public void ticketIsCreatedForAliForTrip() {
        assertEquals(Trip2,Ticket2.getTrip());
        assertEquals("Ali",Ticket2.getPassengerName());
    }

    @And("Ticket1 is added to  list of canceled tickets for Trip1")
    public void ticketIsAddedToListOfCanceledTicketsForTrip() {
        assertTrue(Trip1.getCancelledTickets().contains(Ticket1));
    }

    @And("Ticket2 is added to the list of saved tickets for Trip2")
    public void ticketIsAddedToTheListOfSavedTicketsForTrip() {
        assertTrue(Trip2.getBookedTickets().contains(Ticket2));
    }
}
