import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MainScreen extends JFrame {

        // Panel1: User Registration
        JPanel userPanel;
        JTextField subName;
        JTextField subLastName;
        JTextField subAge;
        JTextField subEmail;

        JLabel nameLBL;
        JLabel lastLBL;
        JLabel ageLBL;
        JLabel emailLBL;

        // Panel2: Movie Selection
        JPanel moviePanel;
        JComboBox movieList;
        JButton selectMovie;

        // Panel3: Theater Selection
        JPanel theaterPanel;
        JComboBox theaterList;
        JButton selectTheater;

        // Panel4: Screening Selection
        JPanel screeningPanel;
        JComboBox screeningList;
        JButton selectScreening;

        // Panel5: Seat Selection
        JPanel seatPanel;
        JButton[][] seatList;
        JButton selectSeat;

        // Panel6: Ticket
        JPanel ticketPanel;
        JButton printTicket;

        // Panel7: Payment
        JPanel paymentPanel;
        JButton payButton;

        // Panel8: Cancel
        JPanel cancelPanel;
        JButton cancelButton;

        // Panel9: Display
        JPanel displayPanel;
        JTextArea displayArea;

        // Panel10: MovieDisplay
        JPanel movieDisplayPanel;
        JTextArea movieDisplayArea;

        JPanel reservationPanel;
        JLabel seatsReservedLabel;
        JLabel totalPriceLabel;
        int seatsReserved = 0; // to keep track of the number of seats reserved



        private boolean[][] seatsBooked = new boolean[6][8];

        // Constructor
        public MainScreen() {
            // Set layout of the main frame
            setLayout(new BorderLayout());

            // Initialize panels
            initUserPanel();
            initMoviePanel();
            initTheaterPanel();
            initScreeningPanel();
            initSeatPanel();
            initPaymentPanel();
            initTicketPanel();
            initCancelPanel();
            initDisplayPanel();
            initMovieDisplayPanel();

            // Attach action listeners to components
            addActionListeners();

            // Add main panels to the frame
            add(createMainPanel(), BorderLayout.CENTER);
            JPanel eastPanel = new JPanel();
            eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
            eastPanel.add(displayPanel);
            eastPanel.add(movieDisplayPanel);
            add(eastPanel, BorderLayout.EAST);
        }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(userPanel);
        mainPanel.add(moviePanel);
        mainPanel.add(theaterPanel);
        mainPanel.add(screeningPanel);
        mainPanel.add(seatPanel);
        mainPanel.add(ticketPanel);
        mainPanel.add(paymentPanel);
        mainPanel.add(cancelPanel);

        return mainPanel;
    }


    private void initDisplayPanel() {
        displayPanel = new JPanel();
        displayPanel.setBorder(BorderFactory.createTitledBorder("Ticket Details"));
        displayPanel.setLayout(new BorderLayout());

        displayArea = new JTextArea(10, 30);  // Reduce the number of rows to half
        JScrollPane scrollPane = new JScrollPane(displayArea);
        displayArea.setEditable(false);
        displayArea.setWrapStyleWord(true);
        displayArea.setLineWrap(true);

        displayPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void initMovieDisplayPanel() {
        movieDisplayPanel = new JPanel();
        movieDisplayPanel.setBorder(BorderFactory.createTitledBorder("Movies"));
        movieDisplayPanel.setLayout(new BorderLayout());

        movieDisplayArea = new JTextArea(20, 30); // You can adjust this size if needed
        JScrollPane movieScrollPane = new JScrollPane(movieDisplayArea);
        movieDisplayArea.setEditable(false);
        movieDisplayArea.setWrapStyleWord(true);
        movieDisplayArea.setLineWrap(true);

        movieDisplayPanel.add(movieScrollPane, BorderLayout.CENTER);
    }



    private void addActionListeners() {
        selectMovie.addActionListener(e -> {
            System.out.println("Selected Movie: " + movieList.getSelectedItem());
            movieDisplayArea.setText("Selected Movie: " + movieList.getSelectedItem()); // Display the selected movie
        });

            selectTheater.addActionListener(e -> {
                System.out.println("Selected Theater: " + theaterList.getSelectedItem());
                // For example: fetchScreeningsForTheater(theaterList.getSelectedItem());
            });

            selectScreening.addActionListener(e -> {
                System.out.println("Selected Screening: " + screeningList.getSelectedItem());
            });

            // Seat selection logic
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 8; j++) {
                    int finalI = i;
                    int finalJ = j;
                    seatList[i][j].addActionListener(e -> {
                        if (!seatsBooked[finalI][finalJ]) {
                            seatList[finalI][finalJ].setBackground(Color.RED);
                            seatsBooked[finalI][finalJ] = true;
                        } else {
                            seatList[finalI][finalJ].setBackground(null);
                            seatsBooked[finalI][finalJ] = false;
                        }
                    });
                }
            }


            cancelButton.addActionListener(e -> {
                // Placeholder logic to release a booked seat
                // resetAllSeats(); // For example
                System.out.println("Booking canceled");
            });

            printTicket.addActionListener(e -> {
                if (isValidEmail(subEmail.getText()) && isValidAge(subAge.getText())) {
                    String details = "Name: " + subName.getText() + " " + subLastName.getText() +
                            "\nAge: " + subAge.getText() +
                            "\nEmail: " + subEmail.getText() +
                            "\nMovie: " + movieList.getSelectedItem() +
                            "\nTheater: " + theaterList.getSelectedItem() +
                            "\nScreening: " + screeningList.getSelectedItem();
                    for (int i = 0; i < 6; i++) {  // Adjusted loop condition
                        for (int j = 0; j < 8; j++) {  // Adjusted loop condition
                            if (seatsBooked[i][j]) {
                                details += "\nSeat: " + (i + 1) + "-" + (j + 1);
                            }
                        }
                    }
                    displayArea.setText(details);
                    System.out.println("Ticket Printed Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or age!");
                }
            });
        }

        private void initUserPanel() {
            userPanel = new JPanel();
            Border userTitle = BorderFactory.createTitledBorder("User Registration");
            userPanel.setBorder(userTitle);
            userPanel.setLayout(new GridLayout(4, 2));

            // JLabels
            nameLBL = new JLabel("Name:");
            lastLBL = new JLabel("Last Name:");
            ageLBL = new JLabel("Age:");
            emailLBL = new JLabel("Email:");

            // JTextFields
            subName = new JTextField();
            subLastName = new JTextField();
            subAge = new JTextField();
            subEmail = new JTextField();

            // Add to panel
            userPanel.add(nameLBL);
            userPanel.add(subName);
            userPanel.add(lastLBL);
            userPanel.add(subLastName);
            userPanel.add(ageLBL);
            userPanel.add(subAge);
            userPanel.add(emailLBL);
            userPanel.add(subEmail);

            // Add to frame
            add(userPanel);

        }

    private void initMoviePanel() {
        moviePanel = new JPanel();
        Border movieTitle = BorderFactory.createTitledBorder("Movie Selection");
        moviePanel.setBorder(movieTitle);
        moviePanel.setLayout(new GridLayout(2, 1));

        // JComboBox
        movieList = new JComboBox<>();

        // Populate the JComboBox with movie titles from the database
        try {
            populateMovieList();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching movie list.");
        }

        // JButton
        selectMovie = new JButton("Select Movie");

        // Add to panel
        moviePanel.add(movieList);
        moviePanel.add(selectMovie);

        // Add to frame
        add(moviePanel);
    }

    private void populateMovieList() throws Exception {
        MovieManager movieManager = new MovieManager(new DatabaseManager("D:\\SQLite\\ticketdb.db"));
        List<String> movies = movieManager.fetchAllMoviesAsText();

        for (String movieTitle : movies) {
            movieList.addItem(movieTitle);
        }
    }


    private void initTheaterPanel() {
        theaterPanel = new JPanel();
        Border theaterTitle = BorderFactory.createTitledBorder("Theater Selection");
        theaterPanel.setBorder(theaterTitle);
        theaterPanel.setLayout(new GridLayout(2, 1));

        // JComboBox
        theaterList = new JComboBox<>();

        // Populate the JComboBox with theaters from the database
        try {
            populateTheaterList();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching theater list.");
        }

        // JButton
        selectTheater = new JButton("Select Theater");

        // Add to panel
        theaterPanel.add(theaterList);
        theaterPanel.add(selectTheater);

        add(theaterPanel);
    }

    private void populateTheaterList() throws Exception {
        TheaterManager theaterManager = new TheaterManager(new DatabaseManager("D:\\SQLite\\ticketdb.db"));
        List<String> theaters = theaterManager.fetchAllTheaters();

        for (String theaterName : theaters) {
            theaterList.addItem(theaterName);
        }
    }



    private void initScreeningPanel() {
        screeningPanel = new JPanel();
        Border screeningTitle = BorderFactory.createTitledBorder("Screening Selection");
        screeningPanel.setBorder(screeningTitle);
        screeningPanel.setLayout(new GridLayout(2, 1));

        // JComboBox for screenings
        screeningList = new JComboBox();

        // Populate the JComboBox with screenings from the database (or other source)
        populateScreeningList();

        // JButton
        selectScreening = new JButton("Select Screening");

        // Add to panel
        screeningPanel.add(screeningList);
        screeningPanel.add(selectScreening);

        // Add to frame
        add(screeningPanel);
    }

    private void populateScreeningList() {
        // Here, fetch your list of screenings and add them to the screeningList JComboBox.
        // For example:
        // List<Screening> screenings = screeningManager.getAllScreenings();
        // for (Screening screening : screenings) {
        //     screeningList.addItem(screening.getScreeningInfo()); // Assuming you have a method in Screening class that returns a String description
        // }
    }


    private void initSeatPanel() {
        seatPanel = new JPanel();
        Border seatTitle = BorderFactory.createTitledBorder("Seat Selection");
        seatPanel.setBorder(seatTitle);
        seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.Y_AXIS));

        JPanel grid = new JPanel(new GridLayout(6, 8));  // 6 rows and 8 columns

        seatList = new JButton[6][8];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                int seatNumber = (i * 8 + j + 1); // Calculates seat number from 1 to 48
                seatList[i][j] = new JButton(Integer.toString(seatNumber));
                grid.add(seatList[i][j]);
            }
        }
        seatPanel.add(grid);

        // JButton for seat selection (if needed for further functionality)
        selectSeat = new JButton("Select Seat");
        seatPanel.add(selectSeat);

        add(seatPanel);
    }


    private void resetAllSeats() {
            for (int i = 0; i < 6; i++) {  // Adjusted loop condition
                for (int j = 0; j < 8; j++) {  // Adjusted loop condition
                    seatList[i][j].setBackground(null);  // Reset color
                    seatsBooked[i][j] = false; // Update data structure
                }
            }
        }


        private void initPaymentPanel() {
            paymentPanel = new JPanel();
            Border paymentTitle = BorderFactory.createTitledBorder("Payment");
            paymentPanel.setBorder(paymentTitle);
            paymentPanel.setLayout(new GridLayout(2, 1));

            // JButton
            payButton = new JButton("Pay");

            // Add to panel
            paymentPanel.add(payButton);

            add(paymentPanel);
        }

        private void initTicketPanel() {
            ticketPanel = new JPanel();
            Border ticketTitle = BorderFactory.createTitledBorder("Ticket");
            ticketPanel.setBorder(ticketTitle);
            ticketPanel.setLayout(new GridLayout(2, 1));

            // JButton
            printTicket = new JButton("Print Ticket");

            // Add to panel
            ticketPanel.add(printTicket);

            add(ticketPanel);
        }

        private void initCancelPanel() {
            cancelPanel = new JPanel();
            Border cancelTitle = BorderFactory.createTitledBorder("Cancel");
            cancelPanel.setBorder(cancelTitle);
            cancelPanel.setLayout(new GridLayout(2, 1));

            // JButton
            cancelButton = new JButton("Cancel");

            // Add to panel
            cancelPanel.add(cancelButton);

            add(cancelPanel);
        }

        private boolean isValidAge(String ageStr) {
            try {
                int age = Integer.parseInt(ageStr);
                return age >= 0 && age <= 120;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isValidEmail(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            return email.matches(regex);
        }

        // Main
        public static void main(String[] args) {
            try {
                // Create the necessary tables
                DatabaseInitializer.createNewTables();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Initialize and display the GUI
            SwingUtilities.invokeLater(() -> {
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                mainScreen.pack();
                mainScreen.setLocationRelativeTo(null);
                mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainScreen.setMinimumSize(new Dimension(400, 700));
                mainScreen.setResizable(false);
            });
        }
}
