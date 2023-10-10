import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScreeningManager {

    // In-memory storage for screenings
    private final List<Screening> screenings = new ArrayList<>();

    // Adds a new screening to the list
    public void addScreening(Screening screening) {
        screenings.add(screening);
    }

    // Returns all screenings
    public List<Screening> getAllScreenings() {
        return screenings;
    }

    // Returns screenings for a specific movie
    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screenings.stream()
                .filter(screening -> screening.getMovie().equals(movie))
                .collect(Collectors.toList());
    }

    // Book seats for a specific screening
    public void bookSeatsForScreening(Screening screening, int numberOfSeats) throws BookingException {
        if (!screenings.contains(screening)) {
            throw new IllegalArgumentException("Screening does not exist!");
        }
        screening.bookSeats(numberOfSeats);
    }

    // Release seats for a specific screening
    public void releaseSeatsForScreening(Screening screening, int numberOfSeats) {
        if (!screenings.contains(screening)) {
            throw new IllegalArgumentException("Screening does not exist!");
        }
        screening.releaseSeats(numberOfSeats);
    }
}
