package performance;

import com.jupiter.tools.stress.test.concurrency.ExecutionMode;
import com.jupiter.tools.stress.test.concurrency.StressTestRunner;
import database.Database;
import database.IDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.addCustomer;
import view.userCreation;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PerformanceTests {
    /**
     * Ensures UserCreation screen can be created and receive the next user ID at least 200 times in 4 seconds.
     */
    @Test
    @Tag("perf")
    void fetchesFakeCustomerId() {
        IDatabase mockDb = Mockito.mock(IDatabase.class);
        Database.setDatabase(mockDb);
        Mockito.when(mockDb.getNextUserId()).thenReturn("UO001");

        StressTestRunner.test()
                .mode(ExecutionMode.EXECUTOR_MODE)
                .timeout(4, TimeUnit.SECONDS)
                .threads(4)
                .iterations(200)
                .run(() -> {
                    var userCreation = new userCreation();
                });

        Mockito.verify(mockDb, Mockito.times(200)).getNextUserId();
    }

    /**
     * Test to see if we can validate a potential customer given a random set of input parameters.
     */
    @Test
    @Tag("perf")
    void canValidateNewCustomerProperties() {
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        StressTestRunner.test()
                .mode(ExecutionMode.EXECUTOR_MODE)
                .timeout(10, TimeUnit.SECONDS)
                .threads(4)
                .iterations(500)
                .run(() -> {
                    addCustomer customerScreen = new addCustomer();
                    customerScreen.isValid(
                            pick("Bob", ""),
                            pick("Evans", ""),
                            pick("11111", ""),
                            pick("111111", ""),
                            pick("1111111", ""),
                            pick(true, false),
                            pick(true, false),
                            pick(new Date(), null),
                            pick(new byte[0], new byte[100], null)
                    );
                });
    }

    private <T> T pick(T... items) {
        return items[new Random().nextInt(items.length)];
    }
}
