import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private static final String SQLITE_JDBC_PREFIX = "jdbc:sqlite:";
    private static final String DATABASE_PATH = "D:\\SQLite\\ticketdb.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            // Registering the driver is optional for some JDBC versions and JVMs,
            // but adding this for wide compatibility
            Class.forName("org.sqlite.JDBC");

            // create a connection to the database
            conn = DriverManager.getConnection(SQLITE_JDBC_PREFIX + DATABASE_PATH);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found. Please include it in your library path!");
            e.printStackTrace();
        }
        return conn;
    }
}
