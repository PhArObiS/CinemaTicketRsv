import java.time.LocalDateTime;
import java.time.Duration;

public class Screening {

    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String theater;
    private int totalSeats;
    private int bookedSeats;

    private Screening(ScreeningBuilder builder) {
        this.movie = builder.movie;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.theater = builder.theater;
        this.totalSeats = builder.totalSeats;
        this.bookedSeats = builder.bookedSeats;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public boolean isScreeningOngoing() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startTime) && now.isBefore(endTime);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public synchronized void bookSeats(int numberOfSeats) throws BookingException {
        if (this.getAvailableSeats() >= numberOfSeats) {
            this.bookedSeats += numberOfSeats;
        } else {
            throw new BookingException("Not enough seats available.");
        }
    }

    public synchronized void releaseSeats(int numberOfSeats) {
        this.bookedSeats -= numberOfSeats;
        if (this.bookedSeats < 0) {
            this.bookedSeats = 0;
        }
    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getTheater() {
        return this.theater;
    }

    @Override
    public String toString() {
        return "Screening of " + movie.getTitle() + " at " + theater + " from " + startTime + " to " + endTime;
    }

    public static class ScreeningBuilder {
        private Movie movie;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String theater;
        private int totalSeats;
        private int bookedSeats;

        public ScreeningBuilder setMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public ScreeningBuilder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ScreeningBuilder setEndTime(LocalDateTime endTime) {
            if (startTime != null && endTime.isBefore(startTime)) {
                throw new IllegalArgumentException("End time cannot be before start time");
            }
            this.endTime = endTime;
            return this;
        }

        public ScreeningBuilder setTheater(String theater) {
            this.theater = theater;
            return this;
        }

        public ScreeningBuilder setTotalSeats(int totalSeats) {
            if (totalSeats <= 0) {
                throw new IllegalArgumentException("Total seats must be greater than 0.");
            }
            this.totalSeats = totalSeats;
            return this;
        }

        public ScreeningBuilder setBookedSeats(int bookedSeats) {
            if (bookedSeats < 0) {
                throw new IllegalArgumentException("Booked seats cannot be negative.");
            }
            this.bookedSeats = bookedSeats;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }
    }
}
