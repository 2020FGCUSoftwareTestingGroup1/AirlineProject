package database;

import model.Customer;
import model.Flight;
import model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    boolean loginUser(String username, String password);
    List<Ticket> getTickets();
    void saveTicket(Ticket ticket) throws SQLException;


    Customer getCustomer(String userId);
    String getNextTicketId();

    List<Flight> searchFlightsBySourceAndDestination(String source, String depart);
    void updateCustomer(Customer customer) throws SQLException;
}
