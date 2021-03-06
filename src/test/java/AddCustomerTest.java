import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddCustomerTest {
    @Test
    void canSubmitNameReturnsTrue() {
        addCustomer customerScreen = new addCustomer();

        boolean isInvalid = customerScreen.canSubmitName("bob1");
        boolean isValid = customerScreen.canSubmitName("bob");

        Assertions.assertFalse(isInvalid);
        Assertions.assertTrue(isValid);
    }
}
