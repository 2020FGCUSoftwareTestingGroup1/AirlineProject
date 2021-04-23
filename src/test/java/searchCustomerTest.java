import database.Database;
import database.IDatabase;
import model.Customer;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.Main;

import java.nio.file.Path;
import java.sql.SQLException;

/**
 * Class for each testing method in the "Search Customer" section of the Airline Project.
 */
class searchCustomerTest {

    FrameFixture window;

    @Mock
    IDatabase mockDatabase;

    /**
     * The setup method is ran before each test is executed. It opens a mock of the test class,
     * sets the database to the mock database, and executes and opens a window frame which shows
     * the main screen of the project.
     */
    @BeforeEach
    public void setup() {
        // Opens a Mockito mock of the Test Class.
        MockitoAnnotations.openMocks(this);
        // Sets Database to the Mock Database.
        Database.setDatabase(mockDatabase);
        // Executes and Opens a Window Frame of the Project.
        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
        window.show();
    }

    /**
     * An integration testing method which finds a valid customer.
     */
    @Test
    @Tag("ui")
    public void findValidCustomer() {
        // Customer Model
        Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
                "AJ24", "123 First Street", "1990-01-01", "Male",
                200, new byte[1024]);

        // Mockito Test to Find a valid Customer.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

        // Mockito - Click on Customer Menu
        window.menuItem("customerRootMenu").click();

        // Mockito - Click on "Search Customer"
        window.menuItem("searchCustomer").click();

        // Mockito - Type "CS001" in the find customer ID input.
        window.textBox("customerIdInput").setText("CS001");

        // Mockito - Click the "Find" button in the search customer screen.
        window.button("findButton").click();

        // Mockito - Verify that a customer was found from input.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    @Test
    @Tag("ui")
    public void catchesExceptionOnFailedUpdate() throws SQLException {
        // Customer Model
        Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
                "AJ24", "123 First Street", "1990-01-01", "Male",
                200, new byte[1024]);

        // Mockito Test to Find a valid Customer.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

        // Throws an SQLException through Mockito when the test fails to update.
        Mockito.doThrow(SQLException.class).when(mockDatabase).updateCustomer(Mockito.any());

        // Executes the Mockito test.
        window.menuItem("customerRootMenu").click();
        window.menuItem("searchCustomer").click();
        window.textBox("customerIdInput").setText("CS001");
        window.button("findButton").click();
        window.button("updateButton").click();

        // Verifies that a customer was found through Mockito.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());
        // Verifies that a customer was updated through Mockito.
        Mockito.verify(mockDatabase).updateCustomer(Mockito.any());
    }

    /**
     * An integration testing method which finds a customer that doesn't exist.
     */
    @Test
    @Tag("ui")
    public void findNullCustomer() {
        // Mockito Test to find an invalid customer and return null as a result.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(null);

        // Executes the Mockito test.
        window.menuItem("customerRootMenu").click();
        window.menuItem("searchCustomer").click();
        window.textBox("customerIdInput").setText("CS001");
        window.button("findButton").click();
        window.dialog().optionPane().requireMessage("Record not Found");

        // Verifies that a customer was found through Mockito.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    /**
     * An integration test method which finds and updates an existing customer. This test is updating
     * the customer's first name.
     */
    @Test
    @Tag("ui")
    public void updateCustomer() {
        // Customer Model.
        Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
                "AJ24", "123 First Street", "1990-01-01", "Male",
                200, null);

        // Mockito test that returns an existing customer.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

        // Executes the Mockito test - Finding a Customer
        window.menuItem("customerRootMenu").click();
        window.menuItem("searchCustomer").click();
        window.textBox("customerIdInput").setText("CS001");
        window.button("findButton").click();

        //Executes the Mockito test - Updating the Customer
        window.textBox("firstNameInput").setText("Joseph");
        window.button("updateButton").click();

        // Verifies that a customer was found through Mockito.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());

    }

    /**
     * An integration testing method which finds the customer, and updates the customer's gender.
     */
    @Test
    @Tag("ui")
    public void updateCustomerGender() {
        // Customer Model
        Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
                "AJ24", "123 First Street", "1990-01-01", "Male",
                200, null);

        // Mockito test that returns an existing customer.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

        // Executes the Mockito Test - Finding a Customer
        window.menuItem("customerRootMenu").click();
        window.menuItem("searchCustomer").click();
        window.textBox("customerIdInput").setText("CS001");
        window.button("findButton").click();

        // Executes the Mockito Test - Updating the Customer's Gender
        window.radioButton("femaleRadioButton").click();
        window.button("updateButton").click();

        // Verifies that a customer was found through Mockito.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    /**
     * An integration testing method which finds a customer and updates their photo.
     */
    @Test
    @Tag("ui")
    public void updateCustomerPhoto() {
      /*
      Customer Model - Note that photo is "null" which means that the customer does not have an
      Existing Photo on file.
       */
        Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
                "AJ24", "123 First Street", "1990-01-01", "Male",
                200, null);

        // Mockito test that returns an existing customer.
        Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

        // Executes the Mockito Test - Finding a Customer.
        window.menuItem("customerRootMenu").click();
        window.menuItem("searchCustomer").click();
        window.textBox("customerIdInput").setText("CS001");
        window.button("findButton").click();

        // Executes the Mockito Test - Browse for a photo
        window.button("browseButton").click();
        var root = Path.of("").toAbsolutePath();
        var testFilePath = root.resolve(Path.of("src", "test", "resources", "CustomerExampleImage.jpg"));
        var testFile = testFilePath.toFile();

        // Executes the Mockito Test - Select a Photo, Approve, and Update the Customer.
        window.fileChooser("picchooser").selectFile(testFile);
        window.fileChooser("picchooser").approve();

        window.button("updateButton").click();

        // Verifies that a customer was found through Mockito.
        Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    /**
     * The cleanup method is a method that is ran after each test is executed. It cleans up the window
     * that is currently running through the test to prepare for the next test to be properly
     * executed.
     */
    @AfterEach
    void cleanup() {
        window.cleanUp();
    }

}