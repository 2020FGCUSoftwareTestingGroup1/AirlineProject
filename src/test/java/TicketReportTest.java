import database.Database;
import database.IDatabase;
import model.Ticket;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import view.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for each testing method in the "Ticket Report" section of the Airline Project.
 */
public class TicketReportTest {
    FrameFixture window;
    
    IDatabase mockDatabase = Mockito.mock(IDatabase.class);

    /**
     * The setup method is ran before each test is executed. It opens a mock of the test class,
     * sets the database to the mock database, and executes and opens a window frame which shows
     * the main screen of the project.
     */
    @BeforeEach
    void setup() {
        Database.setDatabase(mockDatabase);
        Main frame = GuiActionRunner.execute(()-> new Main()) ;
        window = new FrameFixture(frame);
        window.show();
    }

    /**
     * An integration testing method which tests for the flight records of each ticket.
     */
    @Test
    @Tag("ui")
    void showingFlightRecordCalled() {
        // Ticket List Model - 5 Ticket Models are populated into the List.
        List<Ticket> ticketList = new ArrayList<Ticket>();
        ticketList.add(new Ticket("1230", "123456a", "456asd", "First Class", 369, 93, "2021-12-23"));
        ticketList.add(new Ticket("1231", "123456a", "457asd", "Coach Class", 380, 92, "2021-12-23"));
        ticketList.add(new Ticket("1232", "123456a", "458asd", "Coach Class", 393, 91, "2021-12-23"));
        ticketList.add(new Ticket("1233", "123456a", "459asd", "First Class", 299, 90, "2021-12-23"));
        ticketList.add(new Ticket("1234", "123456a", "460asd", "First Class", 412, 89, "2021-12-23"));

        // Mockito Test to find the list of tickets.
        Mockito.when(Database.getDatabase().getTickets()).thenReturn(ticketList);

        // Mockito - Executes the test: Finding the tickets.
        window.menuItem("TicketMenuItem").click();
        window.menuItem("TicketReportItem").click();

        // Asserts that there are 5 tickets in the list.
        Assertions.assertEquals(5, window.table("TicketTable").rowCount());

        // Mockito - Verify that a ticket was found.
        Mockito.verify(mockDatabase).getTickets();
    }

    /**
     * The cleanup method is a method that is ran after each test is executed. It cleans up the window
     * that is currently running through the test to prepare for the next test to be properly
     * executed.
     */
    @AfterEach
    void cleanUp() {
        window.cleanUp();
    }
}
