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

/**
 * The BookTicketTest class will cover all tests based on the Book Ticket Scene and its implementations
 */
public class BookTicketTest {
    // tests for customerID, search by Flight, and Seat counter changes
    FrameFixture window;
    // Create and set mock database.
    @Mock
    IDatabase database = Mockito.mock(IDatabase.class);

    /**
     * The BeforeEach annotation is useful as it setups up key reoccurring features
     * for all tests in the test suite
     */
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

    /**
     * Ensures that the data/values of a Flight Object shows up on the Table
     */
    @Test
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

    /**
     * This test ensures that the total cost of a ticket is correct
     * when given a number of seats and price value
     */
    @Test
    void seatCounterChanges(){
        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.textBox("priceOfSeats").setText("500");
        window.spinner("chooseNumSeats").increment();
        window.label("totalPrice").requireText("500");
    }

    /**
     * This test allows us to see if the selected flight populates
     * the given fields on the right side of the scene
     */
    @Test
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

    /**
     * This test will ensure that all branches of false forms will
     * return false and thus not create a ticket
     */
    @Test
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

    /**
     * This test shows a unique error message when no customer is added
     */
    @Test
    void showErrorMessageForNullCustomer(){

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.button("customerSearchBTN").click();
        window.dialog().optionPane().requireMessage("Record not Found");
    }

    /**
     * This test will assert that the error message comes up
     * if/when the book button is clicked but a field is missing
     */
    @Test
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

    /**
     * Ensure that the book ticket scene closes on cancel
     */
    @Test
    void bookTicketWindowClosed(){

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.button("cancelBTN").click();

        Assertions.assertThrows(Exception.class, () -> window.button("bookTicketBTN").click());
    }

    /**
     * This test will ensure that the exception is handled
     * @throws SQLException
     */
    @Test
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

    /**
     * This test will affirm that a ticket is created when all other parameters are filled in
     */
    @Test
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

    /**
     * This tests what happens when the FlightID is left blank, displaying the proper message
     */
    @Test
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

    /**
     * Similar to the above test, make sure the customerID is not blank when booking a ticket
     */
    @Test
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

    /**
     * Test assures that an invalid form throws the proper exception
     */
    @Test
    void invalidBooking(){
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                window.button("bookTicketBtn").click();
            }
        });
    }

    /**
     * The AfterEach annotation allows us to clean up the window scenes after each test
     */
    @AfterEach
    void cleanup(){
        window.cleanUp();
    }
}
