import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    public DatabaseManager(String dbPath) throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        initDatabase();
    }

    // Initialize database and create tables if they don't exist
    private void initDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            // Creating movies table
            stmt.execute("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY, title TEXT NOT NULL, director TEXT, releaseYear INTEGER, duration INTEGER DEFAULT 0, classification TEXT DEFAULT '', synopsis TEXT DEFAULT '')");

            // Creating theaters table
            stmt.execute("CREATE TABLE IF NOT EXISTS theaters (id INTEGER PRIMARY KEY, name TEXT NOT NULL)");
        }
    }

    public void closeConnection() throws Exception {
        connection.close();
    }

    // Movie Operations

    public void addMovie(Movie newMovie) {
        String sql = "INSERT INTO movies(title, director, releaseYear, duration, classification, synopsis) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newMovie.getTitle());
            pstmt.setString(2, newMovie.getDirector());
            pstmt.setInt(3, newMovie.getReleaseYear());
            pstmt.setInt(4, newMovie.getDuration());
            pstmt.setString(5, newMovie.getClassification());
            pstmt.setString(6, newMovie.getSynopsis());

            pstmt.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add a new movie.", e);
        }
    }

    public Movie getMovie(int i) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("releaseYear"),
                        rs.getInt("duration"),
                        rs.getString("classification"),
                        rs.getString("synopsis")
                );
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to get the movie with ID: " + i, e);
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("releaseYear"),
                        rs.getInt("duration"),
                        rs.getString("classification"),
                        rs.getString("synopsis")
                ));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to get all movies.", e);
        }
        return movies;
    }

    public String fetchAllMoviesAsText() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Movie> movies = this.getAllMovies();
            for (Movie movie : movies) {
                sb.append(movie.toString()).append("\n\n");
            }
        } catch (Exception e) {
            sb.append("Failed to fetch movies.");
            logger.log(Level.SEVERE, "Failed to fetch movies.", e);
        }
        return sb.toString();
    }

    // Theater Operations

    public List<String> getAllTheaters() {
        List<String> theaters = new ArrayList<>();
        String sql = "SELECT name FROM theaters";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                theaters.add(rs.getString("name"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to get all theaters.", e);
        }
        return theaters;
    }
}