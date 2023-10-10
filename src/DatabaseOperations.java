import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {

    public static void insertMovie(String title, String duration, String classification, String synopsis) {
        String sql = "INSERT INTO movies(title, duration, classification, synopsis) VALUES(?,?,?,?)";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, duration);
            pstmt.setString(3, classification);
            pstmt.setString(4, synopsis);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAllMovies() {
        String sql = "SELECT id, title, duration, classification, synopsis FROM movies";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("title") + "\t" +
                        rs.getString("duration") + "\t" +
                        rs.getString("classification") + "\t" +
                        rs.getString("synopsis"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMovieSynopsis(int movieId, String newSynopsis) {
        String sql = "UPDATE movies SET synopsis = ? WHERE id = ?";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newSynopsis);
            pstmt.setInt(2, movieId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMovie(int movieId) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, movieId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
