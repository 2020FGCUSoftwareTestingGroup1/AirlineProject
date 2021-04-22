import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import view.Main;
import view.addflight;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.IdentityHashMap;

public class AddFlightTest {
    @Test
    void validDepartureTime(){
        addflight flightScreen = new addflight();

        boolean isValidDepartureTime = flightScreen.canSubmitTravelTime("12:59");
        boolean isValidUpperBVADepartureTime = flightScreen.canSubmitTravelTime("23:58");

        Assertions.assertTrue(isValidDepartureTime);
        Assertions.assertTrue(isValidUpperBVADepartureTime);
    }

    @Test
    void ValidArrivaleTime(){
        addflight flightScreen = new addflight();

        boolean isValidArrivalTime = flightScreen.canSubmitTravelTime("12:34");
        Assertions.assertTrue(isValidArrivalTime);
    }

    @Test
    void invalidDepartureTime(){
        addflight flightScreen = new addflight();

        boolean isNotValidUpperBVADepartureTime = flightScreen.canSubmitTravelTime("24:00");
        boolean departureTimeNotFormatedProperly = flightScreen.canSubmitTravelTime("1234");
        Assertions.assertFalse(isNotValidUpperBVADepartureTime);
        Assertions.assertFalse(departureTimeNotFormatedProperly);
    }

    @Test
    void validFlightCost(){
        addflight flightScreen = new addflight();
        boolean isValidFlightCost = flightScreen.validDollarAmount("23");
        Assertions.assertTrue(isValidFlightCost);
    }

    @Test
    void checksValidFlightNameInput(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addFlightNameInput").setText("lkj");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addArrivalInput").setText("2:34");
        window.textBox("addFlightChargeInput").setText("23.2");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();


//        Assertions.assertThrows(Exception.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                window.panel("addFlightPanel");
//            }
//        });
        window.cleanUp();
    }

    @Test
    void checksInvalidFlightNamePopUP(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addArrivalInput").setText("2:34");
        window.textBox("addFlightChargeInput").setText("23.2");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();
        window.cleanUp();
    }

    @Test
    void checksInvalidFlightDatePopUp(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.textBox("addFlightNameInput").setText("lkj");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addArrivalInput").setText("2:34");
        window.textBox("addFlightChargeInput").setText("23.2");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();
        window.cleanUp();
    }

    @Test
    void checksInvalidDepartureTimePopUp(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addFlightNameInput").setText("lkj");
        window.textBox("addArrivalInput").setText("2:34");
        window.textBox("addFlightChargeInput").setText("23.2");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();
        window.cleanUp();
    }

    @Test
    void checksArrivalTimePopUp(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addFlightNameInput").setText("lkj");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addFlightChargeInput").setText("23.2");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();
        window.cleanUp();
    }

    @Test
    void checksInvalidFlightChargePopUp(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        //
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addFlightNameInput").setText("lkj");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addArrivalInput").setText("2:34");

        window.button("addFlightButton").click();
        window.button("cancelFlightButton").click();
        window.cleanUp();
    }
}
