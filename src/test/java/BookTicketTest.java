import database.Database;
import database.IDatabase;
import model.Customer;
import model.Flight;
import model.Ticket;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.Main;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookTicketTest {
    // tests for customerID, search by Flight, and Seat counter changes

    // Create and set mock database.
    IDatabase database = Mockito.mock(IDatabase.class);

    @BeforeEach
    void setup() {
        Database.setDatabase(database);

    }

    /** This method will test when a user searches for a ticket through a customer id
     *
     * If customerID is in db then TRUE;
     * the program will fill in first name, last name, and passport id
     */
    @Test
    void searchCustomerShowsUserInformation(){

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

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

        window.cleanUp();
    }

    @Test
    void searchFlightShowsInfo(){

        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "8.00AM","10.00PM", "50000");
        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

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

        window.cleanUp();
    }

    @Test
    void seatCounterChanges(){

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.textBox("priceOfSeats").setText("500");
        window.spinner("chooseNumSeats").increment();
        window.label("totalPrice").requireText("500");

        window.cleanUp();
    }
    @Test
    void checkTableRowSelectShowsInfo(){

        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                "8.00AM","10.00PM", "50000");
        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

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

        window.cleanUp();
    }
    @Test
    void showErrorMessageForNullCustomer(){

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        //navigate to Book Ticket screen
        window.menuItem("TicketMenuItem").click();
        window.menuItem("BookTicket").click();

        window.button("customerSearchBTN").click();
        window.dialog().optionPane().requireMessage("Record not Found");

        window.cleanUp();
    }
    @Disabled
    @Test
    void showErrorMessageForInvalidForm(){

        //create a flight and add to an arraylist to populate table
        Flight myFlight = new Flight("FO001", "JetBlue", "India","Uk", "2019-06-14",
                null,"10.00PM", "50000");

        ArrayList <Flight> theFlights = new ArrayList<>();
        theFlights.add(myFlight);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

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

        window.cleanUp();
    }
}
