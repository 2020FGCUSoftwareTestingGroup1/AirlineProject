import database.Database;
import database.IDatabase;
import model.Customer;
import model.Flight;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import view.BookTicket;
import view.Main;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookTicketTest {
    // tests for customerID, search by Flight, and Seat counter changes
    FrameFixture window;
    // Create and set mock database.
    @Mock
    IDatabase database = Mockito.mock(IDatabase.class);

    @BeforeEach
    void setup() {
        Database.setDatabase(database);
        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
        window.show();
    }

    /** This method will test when a user searches for a ticket through a customer id
     *
     * If customerID is in db then TRUE;
     * the program will fill in first name, last name, and passport id
     */
    @Test
    @Tag("ui")
    void searchCustomerShowsUserInformation(){
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();

        window.label("fNameLabel").requireText("john");
        window.label("lNameLabel").requireText("Alex");
        window.label("passportIDLabel").requireText("3443");
    }

    @Test
    @Tag("ui")
    void searchFlightShowsInfo(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "8.00AM","10.00PM", "50000");
        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();

        //ensure the table was populated with expected amount of flights
        window.table("searchResultTable").requireRowCount(1);
    }

    @Test
    @Tag("ui")
    void seatCounterChanges(){
        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.textBox("priceOfSeats").setText("500");
        window.spinner("chooseNumSeats").increment();
        window.label("totalPrice").requireText("500");
    }

    @Test
    @Tag("ui")
    void checkTableRowSelectShowsInfo(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "8.00AM","10.00PM", "50000");
        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();

        window.label("flightNum").requireText("FO001");
        window.label("flightName").requireText("JetBlue");
        window.label("departTime").requireText("8.00AM");
    }

    @Test
    @Tag("unit")
    void producesCorrectFormResults() {
        var ticket = new BookTicket();

        Assertions.assertTrue(ticket.isFormValid("TO001", "FO001", "CO001", "Business", "123", "1", "2020-01-01"));

        Assertions.assertFalse(ticket.isFormValid("", "FO001", "CO001", "Business", "123", "1", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "", "CO001", "Business", "123", "1", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "", "", "", "", "", ""));
        Assertions.assertFalse(ticket.isFormValid("TO001", "FO001", "", "Business", "123", "1", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "FO001", "CO001", "", "123", "1", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "FO001", "CO001", "Business", "", "1", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "FO001", "CO001", "Business", "123", "", "2020-01-01"));
        Assertions.assertFalse(ticket.isFormValid("TO001", "FO001", "CO001", "Business", "123", "1", null));
    }

    @Test
    @Tag("ui")
    void showErrorMessageForNullCustomer(){

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.button("customerSearchBTN").click();
        window.dialog().optionPane().requireMessage("Record not Found");
    }

    @Test
    @Tag("ui")
    void showErrorMessageForInvalidForm(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "10:00PM","10:00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        Mockito.when(database.getNextTicketId()).thenReturn("");
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));
        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();
        window.spinner("chooseNumSeats").increment();
        window.button("bookTicketBTN").click();

        window.dialog().optionPane().requireMessage("All fields have not been filled");
    }

    @Test
    @Tag("ui")
    void bookTicketWindowClosed(){

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.button("cancelBTN").click();

        Assertions.assertThrows(Exception.class, () -> window.button("bookTicketBTN").click());
    }

    @Test
    @Tag("ui")
    void exceptionHandledOnBookTicket() throws SQLException {
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "10:00PM","10:00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        Mockito.when(database.getNextTicketId()).thenReturn("");
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));
        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        Mockito.doThrow(SQLException.class).when(database).saveTicket(Mockito.any());

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();
        window.spinner("chooseNumSeats").increment();
        window.button("bookTicketBTN").click();
    }

    @Test
    @Tag("ui")
    void testTicketCreated(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "10:00PM","10:00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        Mockito.when(database.getNextTicketId()).thenReturn("TO001");
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));
        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();
        window.spinner("chooseNumSeats").increment();
        window.button("bookTicketBTN").click();
    }

    @Test
    @Tag("ui")
    void checkBranchFightIdBlank(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("", "JetBlue", "India","Uk", "2019-06-14",
                "10:00PM","10:00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        Mockito.when(database.getNextTicketId()).thenReturn("TO001");
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));
        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();
        window.spinner("chooseNumSeats").increment();
        window.button("bookTicketBTN").click();

        window.dialog().optionPane().requireMessage("All fields have not been filled");
    }

    @Test
    @Tag("ui")
    void customerIdIsBlank(){
        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "10:00PM","10:00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        Mockito.when(database.getNextTicketId()).thenReturn("TO001");
        Mockito.when(database.getCustomer("CS001")).thenReturn(new Customer("CS001", "john",
                "Alex", "34232222", "3443", "Uk", "1996-06-01",
                "Male", 34324234, null));
        //return the instance of the flight
        Mockito.when(database.searchFlightsBySourceAndDestination("India","Uk")).thenReturn
                (theFlights);
        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        //enter data to search box
        window.textBox("customerIDbox").setText("CS001");
        window.button("customerSearchBTN").click();
        window.textBox("customerIDbox").setText("");

        //flight details
        window.comboBox("flightDepart").selectItem(2);
        window.button("searchFlightBTN").click();
        window.table("searchResultTable").selectRows(0).click();
        window.spinner("chooseNumSeats").increment();
        window.button("bookTicketBTN").click();

        window.dialog().optionPane().requireMessage("All fields have not been filled");
    }

    @Test
    @Tag("ui")
    void invalidBooking(){
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                window.button("bookTicketBtn").click();
            }
        });
    }


    @AfterEach
    void cleanup(){
        window.cleanUp();
    }
}
