import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This AddCustomerTest class contains the tests to ensure a valid customer is added to the
 * database.
 */

public class AddCustomerTest {
    /**
     * This method checks to see if the first and last name contain only letters for the new customer
     * submission. We are doing this by making sure that no spaces, special characters, and numbers
     * are included into the first or last name. We are implementing equivalence class testing.
     *
     * Returns Bool
     */
    @Test
    void canSubmitNameReturnsTrue() {
        addCustomer customerScreen = new addCustomer();

        // Just Letters with numbers.
        boolean isInvalidNumberCase = customerScreen.canSubmitName("bob1");

        // Just letters.
        boolean isValidAlphaCase = customerScreen.canSubmitName("bob");

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

        Assertions.assertTrue(isValidAlphaCase);

        Assertions.assertFalse(isInvalidNumberCase);

        Assertions.assertFalse(isInvalidSpecialCharaCaseAndAlpha);
        Assertions.assertFalse(isInvalidSpecialCharaCase);

        Assertions.assertFalse(isInvalidEmptyStringCase);
        Assertions.assertFalse(isInvalidSpecialCharaAndNumberCase);
        Assertions.assertFalse(isInvalidAlphaWithSpaceCase);
    }
}
