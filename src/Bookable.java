/**
 * Represents entities that can handle booking operations.
 */
interface Bookable {

    /**
     * Attempts to book a ticket based on the given reservation.
     *
     * @param reservation the ticket reservation details
     * @return true if booking succeeds, false otherwise
     * @throws BookingException if there's an issue with booking
     */
    boolean bookTicket(TicketReservation reservation) throws BookingException;

    /**
     * Confirms a given booking.
     *
     * @param booking the booking details
     * @return true if confirmation succeeds, false otherwise
     * @throws BookingException if there's an issue with confirmation
     */
    boolean confirmBooking(Booking booking) throws BookingException;
}