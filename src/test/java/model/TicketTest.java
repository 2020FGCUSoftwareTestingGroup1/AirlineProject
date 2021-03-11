package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    @Test
    @DisplayName("Throws exception when given a negative price")
    void throwsForNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket("", "", "", "", -1, 0, "");
        }, "Ticket price cannot be below 0.");
    }

    @Test
    @DisplayName("Throws exception when given a negative number of seats")
    void throwsForNegativeSeats() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket("", "", "", "", 0, -1, "");
        }, "Number of seats cannot be below 0.");
    }

    @Test
    @DisplayName("Creates a valid Ticket without an error.")
    void successfullyCreatesValidTicket() {
        assertDoesNotThrow(() -> new Ticket("", "", "", "", 0, 0, "2020-01-22"));
    }
}