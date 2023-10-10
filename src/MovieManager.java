import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieManager {
    private LinkedList<Movie> movieList = new LinkedList<>();
    private DatabaseManager dbManager;

    public MovieManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.movieList = new LinkedList<>(dbManager.getAllMovies());
    }

    public List<Movie> getAllMovies() {
        return movieList;
    }

    public void addMovie(Movie movie) {
        dbManager.addMovie(movie); // Add movie to the database
        movieList.add(movie); // Add movie to the local list
    }

    public Movie findMovieByTitle(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        throw new MovieNotFoundException("Movie with title '" + title + "' not found.");
    }

    // Method to fetch all movie titles
    public List<String> fetchAllMoviesAsText() {
        List<String> movieTitles = new ArrayList<>();
        for (Movie movie : movieList) {
            movieTitles.add(movie.getTitle());
        }
        return movieTitles;
    }

    // ... other methods to manage movies ...
}
