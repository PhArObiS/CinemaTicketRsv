import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

// Represents a cinema system with movies, screenings, and bookings.
public class CinemaSystem implements Searchable, Bookable {
    // List of movies in the cinema system
    private final LinkedList<Movie> movies;

    // Map of movies to their screenings
    private final HashMap<Movie, List<Screening>> screenings;

    // Queue for holding reservation requests
    private final Queue<TicketReservation> reservationRequests;

    // Stack for storing current bookings
    private final Stack<Booking> currentBookings;

    // Constructor initializes the properties
    public CinemaSystem() {
        this.movies = new LinkedList<>();
        this.screenings = new HashMap<>();
        this.reservationRequests = new LinkedList<>();
        this.currentBookings = new Stack<>();
    }

    // Method to add a movie to the cinema system
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    // Method to add a screening for a specific movie
    public void addScreening(Movie movie, Screening screening) {
        screenings.computeIfAbsent(movie, k -> new LinkedList<>()).add(screening);
    }

    // Search for movies by their title
    @Override
    public List<Movie> searchByTitle(String title) throws MovieNotFoundException {
        List<Movie> matchedMovies = movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        if (matchedMovies.isEmpty()) {
            throw new MovieNotFoundException("No movies found with the title: " + title);
        }
        return matchedMovies;
    }

    // This method seems incomplete, as it just returns null.
    // Might need implementation later.
    @Override
    public List<Screening> searchByTime(LocalDateTime time) throws ScreeningNotFoundException {
        return null;
    }

    // Search for screenings within a specified time range
    @Override
    public List<Screening> searchByTime(LocalDateTime fromTime, LocalDateTime toTime) throws ScreeningNotFoundException {
        List<Screening> matchedScreenings = screenings.values()
                .stream()
                .flatMap(List::stream)
                .filter(screening -> (screening.getStartTime().isEqual(fromTime) || screening.getStartTime().isAfter(fromTime))
                        && screening.getStartTime().isBefore(toTime))
                .collect(Collectors.toList());
        if (matchedScreenings.isEmpty()) {
            throw new ScreeningNotFoundException("No screenings found within the specified time range.");
        }
        return matchedScreenings;
    }

    // Book a ticket based on a reservation
    @Override
    public boolean bookTicket(TicketReservation reservation) throws BookingException {
        try {
            // Assuming TicketReservation has a reference to Screening,
            // check if the desired number of seats is available in that screening.
            Screening screening = reservation.getScreening();
            if(screening.getAvailableSeats() >= reservation.getNumberOfTickets()) {
                screening.bookSeats(reservation.getNumberOfTickets());
                reservationRequests.add(reservation);
                return true;
            } else {
                throw new BookingException("Not enough seats available.");
            }
        } catch (Exception e) {
            throw new BookingException("Failed to book ticket.", e);
        }
    }

    // Confirm a previously booked ticket
    @Override
    public boolean confirmBooking(Booking booking) throws BookingException {
        if (currentBookings.contains(booking)) {
            currentBookings.remove(booking);
            return true;
        } else {
            throw new BookingException("Booking details don't match or booking not found.");
        }
    }

    // Cancel a previously made booking
    public void cancelBooking(Booking booking) throws BookingException {
        if (currentBookings.contains(booking)) {
            // Assuming Booking has a reference to Screening,
            // return the booked seats to available ones.
            Screening screening = booking.getScreening();
            screening.releaseSeats(booking.getNumberOfTickets());
            currentBookings.remove(booking);
        } else {
            throw new BookingException("Booking not found.");
        }
    }

    // Get a list of all movies
    public List<Movie> getAllMovies() {
        return new LinkedList<>(movies);
    }

    // Get all screenings for a specific movie
    public List<Screening> getAllScreeningsForMovie(Movie movie) {
        return screenings.getOrDefault(movie, new LinkedList<>());
    }
}
