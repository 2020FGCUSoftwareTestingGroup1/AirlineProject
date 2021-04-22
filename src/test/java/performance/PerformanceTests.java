package performance;

import com.jupiter.tools.stress.test.concurrency.ExecutionMode;
import com.jupiter.tools.stress.test.concurrency.StressTestRunner;
import database.Database;
import database.IDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.BookTicket;
import view.addCustomer;
import view.addflight;
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
    void fetchesCustomerId() {
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
                    Assertions.assertNotNull(userCreation.getNextCustomerId());
                });

        Mockito.verify(mockDb, Mockito.times(200)).getNextUserId();
    }

    /**
     * Ensures next flight ID can be fetched at least 1000 teams in 8 seconds.
     */
    @Test
    @Tag("perf")
    void fetchesFlightId() {
        IDatabase mockDb = Mockito.mock(IDatabase.class);
        Database.setDatabase(mockDb);
        Mockito.when(mockDb.getNextFlightId()).thenReturn("FO001");

        StressTestRunner.test()
                .mode(ExecutionMode.EXECUTOR_MODE)
                .timeout(8, TimeUnit.SECONDS)
                .threads(4)
                .iterations(1000)
                .run(() -> {
                    var flight = new addflight();
                    Assertions.assertNotNull(flight.getFlightId());
                });

        Mockito.verify(mockDb, Mockito.times(1000)).getNextFlightId();
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

    /**
     * Test to see if we can validate a ticket request given a random set of input parameters within 10 seconds.
     */
    @Test
    @Tag("perf")
    void canValidateNewTicketProperties() {
        IDatabase database = Mockito.mock(IDatabase.class);
        Database.setDatabase(database);

        StressTestRunner.test()
                .mode(ExecutionMode.EXECUTOR_MODE)
                .timeout(10, TimeUnit.SECONDS)
                .threads(4)
                .iterations(500)
                .run(() -> {
                    BookTicket bookTicket = new BookTicket();
                    bookTicket.isFormValid(
                            pick("123", ""),
                            pick("FO001", ""),
                            pick("CS001", ""),
                            pick("Business", "Economy", ""),
                            pick("123.00", "", "12."),
                            pick("10", "0", "", "-5"),
                            pick("", "Apr 15, 2020", null, "2020-04-15")
                    );
                });
    }

    private <T> T pick(T... items) {
        return items[new Random().nextInt(items.length)];
    }
}
