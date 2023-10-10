public class TicketReservation {
    private final User user;
    private final Screening screening;
    private final int numberOfTickets;

    public TicketReservation(User user, Screening screening, int numberOfTickets) throws BookingException {
        if (user == null || screening == null) {
            throw new IllegalArgumentException("User or Screening cannot be null.");
        }
        int availableSeats = screening.getTotalSeats() - screening.getBookedSeats();
        if (numberOfTickets <= 0 || numberOfTickets > availableSeats) {
            throw new IllegalArgumentException("Invalid number of tickets requested. Available seats: " +
                    availableSeats + ", Requested: " + numberOfTickets);
        }
        this.user = user;
        this.screening = screening;
        this.numberOfTickets = numberOfTickets;
        this.screening.bookSeats(numberOfTickets);  // Marking the seats as booked.
    }

    public void cancelReservation() {
        this.screening.releaseSeats(this.numberOfTickets);
    }

    // getters (no setters for final fields)
    public User getUser() {
        return this.user;
    }

    public int getNumberOfTickets() {
        return this.numberOfTickets;
    }

    public Screening getScreening() {
        return this.screening;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "User: " + this.user.getName() + " " + this.user.getLastName() + "\n" +
                "Movie: " + screening.getMovie().getTitle() + "\n" +
                "Screening Time: " + screening.getStartTime() + "\n" +
                "Theater: " + screening.getTheater() + "\n" +
                "Number of Tickets: " + this.numberOfTickets;
    }
}
