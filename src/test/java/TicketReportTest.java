import database.Database;
import database.IDatabase;
import model.Ticket;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.Main;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TicketReportTest {
    FrameFixture window;
    
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    
    @Mock
    IDatabase mockDatabase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
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


        Assert.assertEquals(5, window.table("TicketTable").rowCount());
        Mockito.verify(mockDatabase).getTickets();
    }



    @AfterEach
    void cleanUp() {
        window.cleanUp();
    }
}
