import static org.junit.jupiter.api.Assertions.*;
import org.example.*;
import org.example.impl.*;
import org.example.util.TimeManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;


public class UnitTests {
    private TicketReservationSystem system;
    private ZoneId timeZone;
    private  City Isfahan, Tehran;
    private  Train train1;
    private  Instant departure, arrival;

    @BeforeEach
    void setUp() {
        timeZone = ZoneId.systemDefault();
        system = new TicketReservationSystemImpl(timeZone);
        Isfahan=new CityImpl("Isfahan");
        Tehran=new CityImpl("Tehran");
        system.addCity(Isfahan);
        system.addCity(Tehran);
        train1=new TrainImpl("train1",3);
        system.addTrain(train1);
        departure= TimeManagement.createInstant("2023-11-24 10:00",timeZone);
        arrival= TimeManagement.createInstant("2023-11-24 12:00",timeZone);

    }

    @Test
    void shouldCreateTripSuccessfully() throws Exception {

        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        assertTrue(system.getAllTrips().contains(trip1));
        assertEquals(Isfahan, trip1.getOrigin());
        assertEquals(Tehran, trip1.getDestination());
        assertEquals(train1, trip1.getTrain());
        assertEquals(departure, trip1.findRealDepartureTime());
        assertEquals(arrival, trip1.findRealArrivalTime());


    }
    @Test
    //WhenTripOverlapsWithExistingTrip
    void  CreateTripShouldThrowTripException() throws Exception{

        City Mashhad=new CityImpl("Mashhad");
        system.addCity(Mashhad);
        Instant departure2= TimeManagement.createInstant("2023-11-23 11:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-23 16:00",timeZone);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        assertThrows(TripException.class, () ->system.createTrip(Tehran,Mashhad,train1,departure2,arrival2));


    }
    @Test
    void  shouldBookTicketSuccessfully() throws  Exception{

        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        Ticket ticket1=trip1.bookTicket("Ali");
        assertEquals("Ali", ticket1.getPassengerName());
        assertEquals(1, trip1.getBookedTickets().size());

    }
    @Test
    //WhenTrainIsFull
    public void BookTicketShouldThrowReservationException() throws  Exception{

            Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
            Ticket ticket1=trip1.bookTicket("Alice");
            Ticket ticket2=trip1.bookTicket("Bob");
            Ticket ticket3=trip1.bookTicket("Sara");
            assertThrows(ReservationException.class, () -> trip1.bookTicket("Ali"));
    }
    @Test
    public void shouldExchangeTicketSuccessfully() throws Exception{

        Instant departure2= TimeManagement.createInstant("2023-11-24 11:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-24 13:00",timeZone);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        Train train2=new TrainImpl("train2",3);
        Trip trip2=system.createTrip(Isfahan,Tehran,train2,departure2,arrival2);
        Ticket ticket1=trip1.bookTicket("Ali");
        Ticket ticket2=ticket1.exchangeTicket(trip2);

        assertTrue(ticket1.isCancelled());
        assertTrue(trip2.getBookedTickets().contains(ticket2));
        assertTrue(trip1.getCancelledTickets().contains(ticket1));
        assertEquals(trip2,ticket2.getTrip());

    }
    @Test
    //WhenTicketDepartureTimeAfterNewTripDepartureTime
    public void ExchangeTicketShouldThrowReservationException() throws Exception{

        Instant departure2= TimeManagement.createInstant("2023-11-24 08:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-24 10:00",timeZone);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        Train train2=new TrainImpl("train2",3);
        Trip trip2=system.createTrip(Isfahan,Tehran,train2,departure2,arrival2);
        Ticket ticket1=trip1.bookTicket("Ali");
        assertThrows(ReservationException.class, () -> ticket1.exchangeTicket(trip2));
    }
    @Test
    public void shouldFindPreviousTripForValidTrip() throws Exception {

        Instant departure2= TimeManagement.createInstant("2023-11-24 06:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-24 08:00",timeZone);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure2,arrival2);
        Trip trip2=system.createTrip(Tehran,Isfahan,train1,departure,arrival);
        Optional<Trip> previousTrip = system.findPreviousTripOfTrain(train1, trip2);
        assertTrue(previousTrip.isPresent());
        assertEquals(previousTrip.get(),trip1);

    }
    @Test
    //WhenTrainMismatch
    public void FindPreviousTripShouldThrowTripException() throws Exception {

        Instant departure2= TimeManagement.createInstant("2023-11-24 06:00",timeZone);
        Instant arrival2= TimeManagement.createInstant("2023-11-24 08:00",timeZone);
        Train train2=new TrainImpl("Train2",3);
        system.addTrain(train2);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure2,arrival2);
        Trip trip2=system.createTrip(Tehran,Isfahan,train2,departure,arrival);
        assertThrows(TripException.class, () -> system.findPreviousTripOfTrain(train1, trip2));

    }
    @Test
    public void shouldCancelTicketSuccessfully() throws Exception {
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        Ticket ticket1=trip1.bookTicket("Ali");
        trip1.cancelTicket(ticket1);
        assertTrue(ticket1.isCancelled());
        assertFalse(trip1.getBookedTickets().contains(ticket1));
        assertTrue(trip1.getCancelledTickets().contains(ticket1));
    }

    @Test
    //WhenTicketDoesNotBelongToTrip
    public void CancelTicketShouldThrowReservationException() throws  Exception {

        Train train2=new TrainImpl("Train2",3);
        Trip trip1=system.createTrip(Isfahan,Tehran,train1,departure,arrival);
        Trip trip2=system.createTrip(Tehran,Isfahan,train2,departure,arrival);
        Ticket ticket1=trip2.bookTicket("Ali");
        assertThrows(ReservationException.class, () -> trip1.cancelTicket(ticket1));
    }

}