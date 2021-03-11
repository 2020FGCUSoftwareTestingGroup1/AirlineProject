package model;

public class Ticket {
    String id;
    String flightId;
    String customerId;
    String flightClass;
    int price;
    int seats;
    String date;

    public Ticket(String id, String flightId, String customerId,
                  String flightClass, int price, int seats, String date) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
        this.flightClass = flightClass;
        setPrice(price);
        setSeats(seats);
        setDate(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        if (price < 0) {
            throw new IllegalArgumentException("Ticket price cannot be below 0.");
        }
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
        if (seats < 0) {
            throw new IllegalArgumentException("Number of seats cannot be below 0.");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Date did not match yyyy-MM-dd");
        }

        this.date = date;

    }

    public static boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
}
