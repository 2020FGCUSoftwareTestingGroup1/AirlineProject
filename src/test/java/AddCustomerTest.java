import database.Database;
import database.IDatabase;
import model.Customer;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Main;
import view.addCustomer;

import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This AddCustomerTest test case contains the tests to ensure a valid customer is added to the
 * database. This test class implements equivalence class testing. The subsequent classes are as follows
 *
 * Equivalence classes:
 * - Only letters.
 * - Not only letters.
 *
 * The following methods
 * @see #canSubmitNameReturnsTrue()
 * tests for valid inputs, and
 * @see #canSubmitNameReturnsFalse()
 * handles all of the invalid inputs for the first name and last name inputs in order to create a new customer.
 */

@Tag("unittest")
public class AddCustomerTest {

    @BeforeEach
    void setup() {
        Database.setDatabase(Mockito.mock(IDatabase.class));
    }

    /**
     * This method checks to see if the first and last name contain only letters for the new customer
     * submission. We are doing this by making sure that no spaces, special characters, and numbers exists.
     *
     * Equivalence classes:
     *
     * - Only letters.
     *
     * @Returns Bool
     */
    @Test
    void canSubmitNameReturnsTrue() {

        addCustomer customerScreen = new addCustomer();

        // Just letters.
        boolean isValidAlphaCase = customerScreen.canSubmitName("bob");

        Assertions.assertTrue(isValidAlphaCase);
    }


    /**
     * The canSubmitNameReturnsFalse method handles all of the invalid inputs that an end user can input
     * when inputting their first and last name while creating a new customer.
     *
     * Equivalence Classes:
     * - Just letters with numbers.
     * - Just special characters.
     * - Just letters with special characters.
     * - Just numbers with special characters.
     * - Empty string.
     * - Just letters with spaces.
     */
    @Test
    void canSubmitNameReturnsFalse() {
        addCustomer customerScreen = new addCustomer();

        // Just Letters with numbers.
        boolean isInvalidNumberCase = customerScreen.canSubmitName("bob1");

        // Just special characters.
        boolean isInvalidSpecialCharaCase = customerScreen.canSubmitName("-@#$%^&*");

        // Just Letters with special Characters.
        boolean isInvalidSpecialCharaCaseAndAlpha = customerScreen.canSubmitName("bob!@#$");

        // Just Numbers with special characters.
        boolean isInvalidSpecialCharaAndNumberCase = customerScreen.canSubmitName("123%^&*");

        // Empty String.
        boolean isInvalidEmptyStringCase = customerScreen.canSubmitName("");

        // Just letters with spaces.
        boolean isInvalidAlphaWithSpaceCase = customerScreen.canSubmitName("bo  b");

        Assertions.assertFalse(isInvalidNumberCase);

        Assertions.assertFalse(isInvalidSpecialCharaCaseAndAlpha);
        Assertions.assertFalse(isInvalidSpecialCharaCase);

        Assertions.assertFalse(isInvalidEmptyStringCase);
        Assertions.assertFalse(isInvalidSpecialCharaAndNumberCase);
        Assertions.assertFalse(isInvalidAlphaWithSpaceCase);
    }


    @Test
    void canSubmitPassportIDReturnsTrue() {
        addCustomer customerScreen = new addCustomer();

        boolean validPassportIDLowerBVA = customerScreen.canSubmitPassportID("abcefg1");
        boolean validPassportIDHigherBVA = customerScreen.canSubmitPassportID("abc123de");

        Assertions.assertTrue(validPassportIDLowerBVA);
        Assertions.assertTrue(validPassportIDHigherBVA);
    }

    @Test
    void canSubmitPassportIDReturnsFalse() {
        addCustomer customerScreen = new addCustomer();

        boolean inValidPassportIDLowerBVA = customerScreen.canSubmitPassportID("abc22");
        boolean inValidPassportIDHigherBVA = customerScreen.canSubmitPassportID("123abc4567");

        Assertions.assertFalse(inValidPassportIDLowerBVA);
        Assertions.assertFalse(inValidPassportIDHigherBVA);
    }

    @Test
    void saveCustomerIsCalledGivenValidCustomer() throws SQLException {
        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        // Mock customer ID.
        Mockito.when(database.getNextCustomerId()).thenReturn("CS001");

        // Navigate to Add Customer screen.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        // Enter test data in screen.
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerLastNameInput").setText("Smith");
        window.textBox("addCustomerNicInput").setText("11111");
        window.textBox("addCustomerPassportInput").setText("aaaaa123");
        window.textBox("addCustomerContactInput").setText("55555555");
        window.textBox("addCustomerAddressInput").setText("USA");

        window.radioButton("addCustomerMaleGenderLabel").click();

        window.button("addCustomerBrowseButton").click();

        // Select test customer image.
        var root = Path.of("").toAbsolutePath();
        var testFilePath = root.resolve(Path.of("src", "test", "resources", "CustomerExampleImage.jpg"));
        var testFile = testFilePath.toFile();

        window.fileChooser("addCustomerPicChooser").selectFile(testFile);
        window.fileChooser("addCustomerPicChooser").approve();

        window.panel("addCustomerDateInput").textBox().setText("Apr 22, 2021");

        // Submit customer.
        window.button("addCustomerAddButton").click();

        Mockito.verify(database).saveCustomer(Mockito.any());

        window.cleanUp();
    }

    private void enterCustomerInfo(FrameFixture window, Customer customer) throws ParseException {
        // Enter test data in screen.
        window.textBox("addCustomerFirstNameInput").setText(customer.getFirstName());
        window.textBox("addCustomerLastNameInput").setText(customer.getLastName());
        window.textBox("addCustomerNicInput").setText(customer.getNicNo());
        window.textBox("addCustomerPassportInput").setText(customer.getPassportId());
        window.textBox("addCustomerContactInput").setText(String.valueOf(customer.getContactNumber()));
        window.textBox("addCustomerAddressInput").setText(customer.getAddress());

        var genderLabel = customer.getGender().equals("Female") ? "addCustomerFemaleGenderLabel" : "addCustomerMaleGenderLabel";
        window.radioButton(genderLabel).click();

        window.button("addCustomerBrowseButton").click();

        // Select test customer image.
        var root = Path.of("").toAbsolutePath();
        var testFilePath = root.resolve(Path.of("src", "test", "resources", "CustomerExampleImage.jpg"));
        var testFile = testFilePath.toFile();

        window.fileChooser("addCustomerPicChooser").selectFile(testFile);
        window.fileChooser("addCustomerPicChooser").approve();

        window.panel("addCustomerDateInput").textBox().setText("Apr 01, 2020");
    }

    @Test
    void saveCustomerIsCalledGivenValidFemaleCustomer() throws SQLException, ParseException {
        // Create and set mock database.
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        // Create window for UI testing.
        Main frame = GuiActionRunner.execute(() -> new Main());
        var window = new FrameFixture(frame);
        window.show();

        // Mock customer ID.
        Mockito.when(database.getNextCustomerId()).thenReturn("CS001");

        // Navigate to Add Customer screen.
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        var customer = new Customer(
                "CS001",
                "John",
                "Smith",
                "11111",
                "aaaaa123",
                "USA",
                "2021-04-22",
                "Female",
                55555555,
                null
        );

        enterCustomerInfo(window, customer);

        // Submit customer.
        window.button("addCustomerAddButton").click();

        Mockito.verify(database).saveCustomer(Mockito.any());

        window.cleanUp();
    }
}


