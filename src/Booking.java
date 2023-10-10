/**
 * Represents a booking made by a user for a specific screening.
 */
public class Booking {

    private final String bookingID;
    private final User user;
    private final Screening screening;
    private final int numberOfTickets;

    /**
     * Constructs a new Booking instance.
     *
     * @param bookingID unique identifier for the booking. Must match the pattern "B[0-9]{5}".
     * @param user the user associated with the booking.
     * @param screening the screening details associated with the booking.
     * @param numberOfTickets the number of tickets associated with the booking.
     * @throws InvalidBookingDetailsException if the bookingID doesn't match the expected pattern.
     */
    public Booking(String bookingID, User user, Screening screening, int numberOfTickets) throws InvalidBookingDetailsException {
        if (!bookingID.matches("B[0-9]{5}")) {
            throw new InvalidBookingDetailsException("Invalid booking ID format.");
        }
        this.bookingID = bookingID;
        this.user = user;
        this.screening = screening;
        this.numberOfTickets = numberOfTickets;
    }

    /**
     * @return the booking's unique identifier.
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * @return the user associated with the booking.
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the screening details associated with the booking.
     */
    public Screening getScreening() {
        return screening;
    }

    /**
     * @return the number of tickets associated with the booking.
     */
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    /**
     * @return a string representation of the booking details.
     */
    @Override
    public String toString() {
        return "Booking ID: " + bookingID +
                "\nUser: " + user.getName() + " " + user.getLastName() +
                "\nScreening: " + screening.toString() +
                "\nNumber of Tickets: " + numberOfTickets;
    }
}
