import database.Database;
import database.IDatabase;
import model.Ticket;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Main;

import java.util.ArrayList;
import java.util.List;

public class TicketReportTest {
    FrameFixture window;
    
    IDatabase mockDatabase = Mockito.mock(IDatabase.class);

    @BeforeEach
    void setup() {
        Database.setDatabase(mockDatabase);
        Main frame = GuiActionRunner.execute(()-> new Main()) ;
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    void showingFlightRecordCalled() throws InterruptedException {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        ticketList.add(new Ticket("1230", "123456a", "456asd", "First Class", 369, 93, "2021-12-23"));
        ticketList.add(new Ticket("1231", "123456a", "457asd", "Coach Class", 380, 92, "2021-12-23"));
        ticketList.add(new Ticket("1232", "123456a", "458asd", "Coach Class", 393, 91, "2021-12-23"));
        ticketList.add(new Ticket("1233", "123456a", "459asd", "First Class", 299, 90, "2021-12-23"));
        ticketList.add(new Ticket("1234", "123456a", "460asd", "First Class", 412, 89, "2021-12-23"));
        Mockito.when(Database.getDatabase().getTickets()).thenReturn(ticketList);

        window.menuItem("TicketMenuItem").click();
        window.menuItem("TicketReportItem").click();


        Assertions.assertEquals(5, window.table("TicketTable").rowCount());
        Mockito.verify(mockDatabase).getTickets();
    }



    @AfterEach
    void cleanUp() {
        window.cleanUp();
    }
}
