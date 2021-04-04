package database;

import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RealDatabase implements IDatabase {
    private Connection con;

    public RealDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginUser(String username, String password)  {
        try {
            var pst = con.prepareStatement("select * from user where username = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Ticket> getTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            var pst = con.prepareStatement("SELECT * from ticket");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData rsm = rs.getMetaData();
            int c = rsm.getColumnCount();


            while(rs.next()) {
                var id = rs.getString("id");
                var flightid = rs.getString("flightid");
                var custid = rs.getString("custid");
                var className = rs.getString("class");
                var price = rs.getInt("price");
                var seats = rs.getInt("seats");
                var date = rs.getString("date");

                tickets.add(new Ticket(id, flightid, custid, className, price, seats, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tickets;
    }
}
