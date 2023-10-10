import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseInitializer {
    private static final Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());

    public static void createNewTables() {
        // SQL statement for creating a new table for movies
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	duration integer,\n"  // changed from text to integer
                + "	release_date text,\n"  // added a release_date field
                + "	classification text,\n"
                + "	synopsis text\n"
                + ");";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to create new tables.", e);
        }
    }
}
