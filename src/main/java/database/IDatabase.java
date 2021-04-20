package database;

import model.Customer;
import model.Flight;
import model.Ticket;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    boolean loginUser(String username, String password);
    List<Ticket> getTickets();
    void saveTicket(Ticket ticket) throws SQLException;
    void saveFlight(Flight flight) throws SQLException;
    void saveUser(User user) throws SQLException;
    void saveCustomer(Customer customer) throws SQLException;


    Customer getCustomer(String userId);
    String getNextTicketId();
    String getNextCustomerId();
    String getNextUserId();

    List<Flight> searchFlightsBySourceAndDestination(String source, String depart);
    void updateCustomer(Customer customer) throws SQLException;
}
