package database;

import model.Ticket;

import java.util.List;

public interface IDatabase {
    boolean loginUser(String username, String password);
    List<Ticket> getTickets();
}
