package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    @Test
    @Tag("unit")
    @DisplayName("Throws exception when given a negative price")
    void throwsForNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket("", "", "", "", -1, 0, "");
        }, "Ticket price cannot be below 0.");
    }

    @Test
    @Tag("unit")
    @DisplayName("Throws exception when given a negative number of seats")
    void throwsForNegativeSeats() {
        var id = "";
        var flightId = "";
        var customerId = "";
        var flightClass = "";
        var price = 0;
        var seats = -1;
        var date = "";

        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(id, flightId, customerId, flightClass, price, seats, date);
        }, "Number of seats cannot be below 0.");
    }

    @Test
    @Tag("unit")
    @DisplayName("Creates a valid Ticket without an error.")
    void successfullyCreatesValidTicket() {
        assertDoesNotThrow(() -> new Ticket("", "", "", "", 0, 0, "2020-01-22"));
    }

    @Test
    @Tag("unit")
    void throwsOnError() {
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Ticket sample = new Ticket("123", "abcd", "ovgarcia", "First",  333, 23, "Today lol");
                sample.setDate("lol");
            }
        });
    }
}