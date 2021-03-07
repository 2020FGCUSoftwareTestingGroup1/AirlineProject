import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This AddCustomerTest class contains the tests to ensure a valid customer is added to the
 * database. This test class implements equivalence class testing. The subsequent classes are as follows
 */

public class AddCustomerTest {
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

}
