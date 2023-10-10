import javax.swing.*;
import java.awt.*;

public class SeatNumberingUI {

    private JFrame frame;
    private JButton[][] seatButtons;
    private int numRows;
    private int numCols;

    public SeatNumberingUI(int numRows, int numCols) {
        if(numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Number of rows and columns should be positive.");
        }

        this.numRows = numRows;
        this.numCols = numCols;

        createAndShowUI();
    }

    private void createAndShowUI() {
        frame = new JFrame("Seat Numbering");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(numRows, numCols));
        frame.setResizable(false);

        createSeatButtons();

        frame.pack();
        frame.setVisible(true);
    }

    private void createSeatButtons() {
        seatButtons = new JButton[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                seatButtons[row][col] = new JButton((row * numCols + col + 1) + "");
                // Add action listeners or styling here if needed.
                frame.add(seatButtons[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeatNumberingUI(5, 5));
    }
}



//
//    The SQLiteConnection class you provided establishes a connection to an SQLite database using the JDBC driver for SQLite. Here are some things to consider and improvements you might make:
//
//        Hardcoded Database Path: The DATABASE_PATH constant currently contains a placeholder ("path_to_your_database.db"). Remember to replace this with the actual path to your SQLite database file.
//
//        Exception Handling:
//
//        Currently, you're catching exceptions and printing messages to the standard output. Depending on your application's requirements, you might want to handle these exceptions differently, like logging them or re-throwing them to be handled by the calling code.
//        For better debugging, consider also printing the stack trace for SQLException.
//        Connection Closure: It's crucial to close the database connection once you're done using it to free up resources. You can provide a disconnect method to close the connection. Alternatively, ensure that the calling code closes the connection when done.
//
//        Singleton Pattern: If you want to ensure that only one database connection exists throughout the lifetime of your application, you can implement this class using the Singleton pattern.
//
//        Parameterize Database Path: Instead of hardcoding the database path, consider accepting it as a parameter, which provides flexibility in specifying different database paths.
//
//        Configurable Error Handling: Instead of printing exceptions to the console, you might want to use a logging framework (like SLF4J with Logback or Log4j) to log these errors.
//
//        Here's an improved version of the SQLiteConnection class:
//
//        java
//        Copy code
//        import java.sql.Connection;
//        import java.sql.DriverManager;
//        import java.sql.SQLException;
//
//public class SQLiteConnection {
//
//    private static final String SQLITE_JDBC_PREFIX = "jdbc:sqlite:";
//
//    // Making it an instance variable allows flexibility in specifying the database path.
//    private String databasePath;
//
//    // Singleton instance
//    private static SQLiteConnection instance;
//
//    private Connection conn;
//
//    private SQLiteConnection(String databasePath) {
//        this.databasePath = databasePath;
//    }
//
//    public static synchronized SQLiteConnection getInstance(String databasePath) {
//        if (instance == null) {
//            instance = new SQLiteConnection(databasePath);
//        }
//        return instance;
//    }
//
//    public Connection connect() {
//        if (conn != null) {
//            return conn;
//        }
//
//        try {
//            // Register the driver
//            Class.forName("org.sqlite.JDBC");
//
//            // Create a connection to the database
//            conn = DriverManager.getConnection(SQLITE_JDBC_PREFIX + databasePath);
//
//        } catch (SQLException e) {
//            // Replace with logging or custom error handling
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            // Replace with logging or custom error handling
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    public void disconnect() {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                // Replace with logging or custom error handling
//                e.printStackTrace();
//            }
//        }
//    }
//}
//Note: If you decide to use the Singleton pattern, always be cautious. Singletons can make unit testing difficult, and in some scenarios, you might need multiple database connections (like in a multi-threaded environment). Always evaluate your needs before choosing a design pattern.