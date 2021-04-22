import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Main;
import view.addflight;

import java.sql.SQLException;

/**
 * This AddFlightTest class contains the tests in order to ensure valid/invalid entries for the following fields
 *
 * Departure Time
 * Arrival Time
 * Flight Name
 * Flight Charge
 * Flight Date
 *
 * This class also handles GUI testing in order to make sure that all of the user interface elements
 * are able to be given valid inputs.
 */
public class AddFlightTest {

    /**
     * This test case asserts that a valid departure time is given.
     *
     * Equivalence Classes:
     * proper format:
     * 0-23
     * Only Ints:
     */
    @Test
    @Tag("unit")
    void validDepartureTime(){
        addflight flightScreen = new addflight();

        boolean isValidDepartureTime = flightScreen.canSubmitTravelTime("12:59");
        boolean isValidUpperBVADepartureTime = flightScreen.canSubmitTravelTime("23:58");

        Assertions.assertTrue(isValidDepartureTime);
        Assertions.assertTrue(isValidUpperBVADepartureTime);
    }

    /**
     * This test case ensures that a valid arrival time is given
     *
     * Equivalence Classes:
     * proper format:
     * 0-23
     * Only Ints:
     */
    @Test
    @Tag("unit")
    void validArrivalTime(){
        addflight flightScreen = new addflight();

        boolean isValidArrivalTime = flightScreen.canSubmitTravelTime("12:34");
        Assertions.assertTrue(isValidArrivalTime);
    }


    /**
     * This test case asserts that an invalid departure time is given.
     *
     * Equivalence Classes:
     * Improper format:
     * 0-23
     * Only Alpha:
     * asdlkjs
     */
    @Test
    @Tag("unit")
    void invalidDepartureTime(){
        addflight flightScreen = new addflight();

        boolean isNotValidUpperBVADepartureTime = flightScreen.canSubmitTravelTime("24:00");
        boolean departureTimeNotFormatedProperly = flightScreen.canSubmitTravelTime("1234");
        boolean invalidDepartureTimeAlpha = flightScreen.canSubmitTravelTime("addf");
        Assertions.assertFalse(isNotValidUpperBVADepartureTime);
        Assertions.assertFalse(departureTimeNotFormatedProperly);
        Assertions.assertFalse(invalidDepartureTimeAlpha);
    }

    /**
     * This test case asserts that a valid flight charge input is given.
     *
     * Equivalence Classes:
     * only Ints
     * only . or , as special characters
     */
    @Test
    @Tag("unit")
    void validFlightCost(){
        addflight flightScreen = new addflight();
        boolean isValidFlightCost = flightScreen.validDollarAmount("23");
        Assertions.assertTrue(isValidFlightCost);
    }

    /**
     * This test case asserts that a valid flight object is created.
     */
    @Test
    @Tag("ui")
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
        //window.button("cancelFlightButton").click();

        window.cleanUp();
    }

    /**
     * This test asserts that the invalid flight name notification window pops up when
     * an invalid flight name pops up.
     */
    @Test
    @Tag("ui")
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
        window.dialog().requireModal().optionPane().requireMessage("Enter Flight Name!");

        window.cleanUp();
    }

    /**
     * This test checks to ensure that the invalid flight date notification window pops up.
     */
    @Test
    @Tag("ui")
    void checksInvalidFlightDatePopUp(){
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

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
        window.dialog().requireModal().optionPane().requireMessage("Select a date!");
        window.cleanUp();
    }

    /**
     * This test case asserts that the invalid departure time notification window pops up.
     */
    @Test
    @Tag("ui")
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
        window.dialog().requireModal().optionPane().requireMessage("Make sure time is formatted 0-23");

        window.cleanUp();
    }

    /**
     * This test case asserts that the invalid arrival time notification window pops up.
     */
    @Test
    @Tag("ui")
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
        window.dialog().requireModal().optionPane().requireMessage("Make sure time is formatted 0-23");

        window.cleanUp();
    }

    /**
     * This test asserts that the invalid flight charge notification window pops up.
     */
    @Test
    @Tag("ui")
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
        window.dialog().requireModal().optionPane().requireMessage("Make sure Cost is formatted 123.21");
        window.cleanUp();

    }

    /**
     * This test case asserts that an exception is thrown if there is a failed flight update.
     * @throws SQLException
     */
    @Test
    @Tag("ui")
    public void catchesExceptionOnFailedUpdate() throws SQLException {
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        Mockito.when(database.getNextFlightId()).thenReturn("FO001");
        Mockito.doThrow(SQLException.class).when(database).saveFlight(Mockito.any());

        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);

        window.show();
        window.menuItem("flightRootMenu").click();
        window.menuItem("addFlightMenuItem").click();
        window.panel("addFlightDateInput").textBox().setText("Apr 22, 2021");
        window.textBox("addFlightNameInput").setText("Name");
        window.textBox("addDepartureInput").setText("12:00");
        window.textBox("addArrivalInput").setText("2:34");
        window.textBox("addFlightChargeInput").setText("123");

        window.button("addFlightButton").click();
        window.cleanUp();
        Mockito.verify(database).saveFlight(Mockito.any());
    }
}
