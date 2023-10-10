/**
 * Represents an exception related to invalid details provided during booking.
 */
public class InvalidBookingDetailsException extends BookingException {
    public InvalidBookingDetailsException(String message) {
        super(message);
    }

    public InvalidBookingDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}