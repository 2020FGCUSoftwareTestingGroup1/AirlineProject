package database;

import model.Customer;
import model.Flight;
import model.Ticket;
import model.User;
import view.addCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public void saveTicket(Ticket ticket) throws SQLException {
        var pst = con.prepareStatement("insert into ticket(id,flightid,custid,class,price,seats,date)values(?,?,?,?,?,?,?)");

        pst.setString(1, ticket.getId());
        pst.setString(2, ticket.getFlightId());
        pst.setString(3, ticket.getCustomerId());
        pst.setString(4, ticket.getFlightClass());
        pst.setInt(5, ticket.getPrice());
        pst.setInt(6, ticket.getSeats());
        pst.setString(7, ticket.getDate());


        pst.executeUpdate();
    }

    @Override
    public Customer getCustomer(String customerId) {
        try {
            var pst = con.prepareStatement("select * from customer where id = ?");
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String passportId = rs.getString("passport");
                String nic = rs.getString("nic");
                String address = rs.getString("address");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                int contact = rs.getInt("contact");
                byte[] photo = rs.getBytes("photo");

                return new Customer(customerId, fname, lname, nic, passportId, address, dob, gender, contact, photo);
            } else {
                return null;
            }

        } catch (SQLException e) {
            Logger.getLogger(RealDatabase.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public String getNextTicketId() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from ticket");

            if (rs.next()) {
                var highestId = rs.getString("MAX(id)");
                long idNumber = Long.parseLong(highestId.substring(2));
                idNumber++;

                return "TO" + String.format("%03d", idNumber);
            } else {
                return "TO001";
            }

        } catch (SQLException e) {
            Logger.getLogger(RealDatabase.class.getName()).log(Level.SEVERE, null, e);
            return "TO001";
        }
    }

    @Override
    public String getNextCustomerId() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from customer");
            rs.next();
            rs.getString("MAX(id)");
            if(rs.getString("MAX(id)") == null) {
                return "CS001";
            } else {
                long id = Long.parseLong(rs.getString("MAX(id)").substring(2));
                id++;
                return "CS" + String.format("%03d", id);
            }
        } catch (SQLException e) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    @Override
    public String getNextUserId() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from user");
            rs.next();
            rs.getString("MAX(id)");

            if(rs.getString("MAX(id)") == null) {
                return "UO001";
            } else {
                long id = Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                return "UO" + String.format("%03d", id);
            }
        } catch (SQLException e) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    @Override
    public String getNextFlightId() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from flight");
            rs.next();

            if (rs.getString("MAX(id)") == null) {
                return ("FO001");
            } else {
                long id = Long.parseLong(rs.getString("MAX(id)").substring(2));
                id++;
                return ("FO" + String.format("%03d", id));
            }
        } catch (SQLException e) {
            return "FO001";
        }
    }

    @Override
    public List<Flight> searchFlightsBySourceAndDestination(String source, String depart) {
        ArrayList<Flight> flights = new ArrayList<>();

        try {
            var pst = con.prepareStatement("SELECT * from flight WHERE source = ? and depart = ?");
            pst.setString(1, source);
            pst.setString(2, depart);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                var id = rs.getString("id");
                var flightname = rs.getString("flightname");
                var flightSource = rs.getString("source");
                var flightDepart = rs.getString("depart");
                var date = rs.getString("date");
                var deptime = rs.getString("deptime");
                var arrtime = rs.getString("arrtime");
                var flightcharge = rs.getString("flightcharge");

                var flight = new Flight(
                        id, flightname, flightSource, flightDepart,
                        date, deptime, arrtime, flightcharge);

                flights.add(flight);
            }

        } catch (SQLException e) {
            Logger.getLogger(RealDatabase.class.getName()).log(Level.SEVERE, null, e);
        }

        return flights;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        var pst = con.prepareStatement("update customer set firstname = ?,lastname = ?,nic = ?,passport = ?,address= ?,dob = ?,gender = ?,contact = ?,photo = ? where id = ?");


        pst.setString(1, customer.getFirstName());
        pst.setString(2, customer.getLastName());
        pst.setString(3, customer.getNicNo());
        pst.setString(4, customer.getPassportId());
        pst.setString(5, customer.getAddress());
        pst.setString(6, customer.getDob());
        pst.setString(7, customer.getGender());
        pst.setInt(8, customer.getContactNumber());
        pst.setBytes(9, customer.getPhoto());
        pst.setString(10, customer.getId());
        pst.executeUpdate();
    }

    @Override
    public void saveFlight(Flight flight) throws SQLException {
        var pst = con.prepareStatement("insert into flight(id,flightname,source,depart,date,deptime,arrtime,flightcharge)values(?,?,?,?,?,?,?,?)");

        pst.setString(1, flight.getId());
        pst.setString(2, flight.getFlightName());
        pst.setString(3, flight.getSource());
        pst.setString(4, flight.getDepart());
        pst.setString(5, flight.getDate());
        pst.setString(6, flight.getDepartTime());
        pst.setString(7, flight.getArriveTime());
        pst.setString(8, flight.getFlightCharge());

        pst.executeUpdate();
    }

    @Override
    public void saveUser(User user) throws SQLException {
        var pst = con.prepareStatement("insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)");

        pst.setString(1, user.getId());
        pst.setString(2, user.getFirstName());
        pst.setString(3, user.getLastName());
        pst.setString(4, user.getUsername());
        pst.setString(5, user.getPassword());
        pst.executeUpdate();
    }

    @Override
    public void saveCustomer(Customer customer) throws SQLException {
        var pst = con.prepareStatement("insert into customer(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");

        pst.setString(1, customer.getId());
        pst.setString(2, customer.getFirstName());
        pst.setString(3, customer.getLastName());
        pst.setString(4, customer.getNicNo());
        pst.setString(5, customer.getPassportId());
        pst.setString(6, customer.getAddress());
        pst.setString(7, customer.getDob());
        pst.setString(8, customer.getGender());
        pst.setInt(9, customer.getContactNumber());
        pst.setBytes(10, customer.getPhoto());
        pst.executeUpdate();
    }
}
