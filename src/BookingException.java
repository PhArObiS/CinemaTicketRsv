/**
 * Represents a general exception that occurs during booking operations.
 */
public class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }
}