package database;

import model.Ticket;

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
}
