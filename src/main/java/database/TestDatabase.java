package database;

import model.Customer;
import model.Flight;
import model.Ticket;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase implements IDatabase {
    @Override
    public boolean loginUser(String username, String password) {
        return username.equals("username") && password.equals("password");
    }

    @Override
    public List<Ticket> getTickets() {
        var list = new ArrayList<Ticket>();

        list.add(new Ticket("1", "CL_1" , "BOB", "CLASS",1, 1, "2020-01-20"));

        return list;
    }

    private List<Ticket> tickets = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    @Override
    public void saveTicket(Ticket ticket) throws SQLException {
        tickets.add(ticket);
    }

    @Override
    public Customer getCustomer(String userId) {
        return customers
                .stream()
                .filter(customer -> customer.getId().equals(userId)).findFirst()
                .orElse(null);
    }

    @Override
    public String getNextTicketId() {
        return null;
    }

    @Override
    public List<Flight> searchFlightsBySourceAndDestination(String source, String depart) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {

    }

    @Override
    public void saveFlight(Flight flight) throws SQLException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void saveUser(User user) throws SQLException {

    }
}
