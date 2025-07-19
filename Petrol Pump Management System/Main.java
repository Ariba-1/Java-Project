import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Start with the Login/Signup Interface
        new LoginSignupInterface();

    }
}

// Login/Signup Interface
class LoginSignupInterface {

        public LoginSignupInterface() {
            JFrame frame = new JFrame("Login / Signup");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);



            JLabel label1= new JLabel("LOGIN");
            label1.setFont(new Font("Cambria", Font.BOLD, 60));
            label1.setBounds(500, 150, 200, 60);
            label1.setForeground(Color.white);
            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setBounds(500, 250, 100, 30);
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
            JTextField usernameField = new JTextField();
            usernameField.setBounds(600, 250, 200, 25);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setBounds(500, 300, 100, 30);
            passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
            passwordLabel.setForeground(Color.WHITE);
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(600, 300, 200, 25);

            JButton loginButton = new JButton("Login");
            loginButton.setBounds(550, 380, 100, 30);
            loginButton.setBackground(Color.WHITE);
            loginButton.setFont(new Font("Cambria", Font.PLAIN, 14));
            JButton signupButton = new JButton("Signup");
            signupButton.setBounds(690, 380, 100, 30);
            signupButton.setFont(new Font("Cambria", Font.PLAIN, 14));
            signupButton.setBackground(Color.WHITE);

            frame.add(usernameLabel);
            frame.add(usernameField);
            frame.add(passwordLabel);
            frame.add(passwordField);
            frame.add(loginButton);
            frame.add(signupButton);
            frame.add(label1);


            loginButton.addActionListener(e -> {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (validateLogin(username, password)) {
                    frame.dispose();
                    new WelcomeInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });


            signupButton.addActionListener(e -> {
                frame.dispose();
                new SignupForm();
            });

            // Adding an Image
            ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("back9.jpg"));
            Image img222 = img111.getImage().getScaledInstance(1366,768,Image.SCALE_DEFAULT);
            ImageIcon img333 = new ImageIcon(img222);
            JLabel image112 =new JLabel(img333);
            image112.setBounds(0,0,1366,768);
            frame.add(image112);

            frame.setVisible(true);
        }

        private boolean validateLogin(String username, String password) {
            String selectSQL = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection connection = WelcomeInterface.MainMenu.DBConnection.getConnection()) {
                var stmt = connection.prepareStatement(selectSQL);
                stmt.setString(1, username);
                stmt.setString(2, password);
                var rs = stmt.executeQuery();
                return rs.next(); // Returns true if a record is found
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

 class SignupForm {
    public SignupForm() {
        JFrame signupFrame = new JFrame("Signup Form");
        signupFrame.setLayout(null);
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        signupFrame.getContentPane().setBackground(Color.decode("#ADD8E6"));
        JLabel label1= new JLabel("SIGNUP");
        label1.setFont(new Font("Cambria", Font.BOLD, 60));
        label1.setBounds(138, 120, 300, 60);


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 200, 100, 25);
        usernameLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 200, 180, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 250, 100, 25);
        passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 250, 180, 25);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(60, 300, 130, 25);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 180, 25);
       confirmLabel.setFont(new Font("Cambria", Font.PLAIN, 14));

        JButton signupBtn = new JButton("Signup");
        signupBtn.setBounds(190, 350, 100, 30);
        signupBtn.setBackground(Color.WHITE);
        signupBtn.setFont(new Font("Cambria", Font.PLAIN, 14));
        JButton Returnbtn = new JButton("Return");
        Returnbtn.setBounds(320, 350, 100, 30);
        Returnbtn.setFont(new Font("Cambria", Font.PLAIN, 14));
        Returnbtn.setBackground(Color.white);
        Returnbtn.addActionListener(e-> new LoginSignupInterface());


        signupFrame.add(usernameLabel);
        signupFrame.add(usernameField);
        signupFrame.add(passwordLabel);
        signupFrame.add(passwordField);
        signupFrame.add(confirmLabel);
        signupFrame.add(confirmPasswordField);
        signupFrame.add(signupBtn);
        signupFrame.add(Returnbtn);
        signupFrame.add(label1);

        signupBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(signupFrame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(signupFrame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save to database
            if (saveUser(username, password)) {
                JOptionPane.showMessageDialog(signupFrame, "Signup successful!");
                signupFrame.dispose();
                new LoginSignupInterface();
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Signup failed. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Adding an Image
        ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("side2.jpg"));
        Image img222 = img111.getImage().getScaledInstance(760,660,Image.SCALE_DEFAULT);
        ImageIcon img333 = new ImageIcon(img222);
        JLabel image112 =new JLabel(img333);
        image112.setBounds(560, 0, 760, 660);
        signupFrame.add(image112);

        signupFrame.setVisible(true);
    }

    private boolean saveUser(String username, String password) {
        String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = WelcomeInterface.MainMenu.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


// Welcome Interface
class WelcomeInterface {

    public WelcomeInterface() {
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use absolute layout
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Adding an Image
        ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("image.png"));
        Image img222 = img111.getImage().getScaledInstance(1000, 550, Image.SCALE_SMOOTH);
        ImageIcon img333 = new ImageIcon(img222);
        JLabel image112 = new JLabel(img333);
        image112.setBounds(135, 50, 1000, 550);
        image112.setLayout(null);

        // 1. Set background color panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(10, 120, 140)); // Bluish shade
        backgroundPanel.setBounds(0, 0, 1366, 768);
        backgroundPanel.setLayout(null);


        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Algerian", Font.PLAIN, 35));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(180, 120, 200, 60);

        JLabel introLabel = new JLabel(
                "<html><center>Welcome to your <br>all-in-one Petrol Pump Management System! <br>" +
                        "From fuel to car wash, manage everything <br>smoothly in one place." +
                        "Track staff,<br> sales, and services with a system<br> that's smart, simple, and efficient.</center></html>",
                SwingConstants.CENTER
        );
        introLabel.setFont(new Font("Cambria", Font.PLAIN, 13));
        introLabel.setForeground(Color.BLACK);
        introLabel.setBounds(20, 150, 500, 150);


        // Proceed button
        JButton proceedButton = new JButton("Main Menu");
        proceedButton.setFont(new Font("Algerian", Font.PLAIN, 15));
        proceedButton.setBackground(Color.WHITE);
        proceedButton.setForeground(Color.BLACK);
        proceedButton.setBounds(200, 300, 150, 30);

        // Add components to image
        image112.add(welcomeLabel);
        image112.add(introLabel);
        image112.add(proceedButton);

        // Add image to frame
        frame.add(image112);
        frame.add(backgroundPanel);

        proceedButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        frame.setVisible(true);
    }


    // Main Menu
    class MainMenu {
        public MainMenu() {
            JFrame frame = new JFrame("Petrol Station");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // Load and scale background image
            ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("back10.jpg"));
            Image img222 = img111.getImage().getScaledInstance(1322, 786, Image.SCALE_SMOOTH);
            ImageIcon img333 = new ImageIcon(img222);
            JLabel background = new JLabel(img333);
            background.setBounds(0, 0, 1322, 786);
            background.setLayout(null);

            JLabel title = new JLabel("MAIN MENU");
            title.setFont(new Font("Algerian", Font.PLAIN, 50));
            title.setForeground(Color.BLACK);
            title.setBounds(523, 140, 300, 60);

            // Panel to hold buttons (transparent)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false); // Transparent so image shows
            buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
            buttonPanel.setBounds(550, 250, 200, 200); // Adjust to center on image

            String[] labels = {"Manager", "Worker", "Car Wash", "Tuckshop", "Fuel"};
            JButton[] buttons = new JButton[labels.length];

            for (int i = 0; i < labels.length; i++) {
                buttons[i] = new JButton(labels[i]);
                buttons[i].setFont(new Font("Algerian", Font.PLAIN, 14));
                buttons[i].setBackground(Color.white);
                buttons[i].setForeground(Color.black);
                buttonPanel.add(buttons[i]);
            }

            // Add button panel to background image
            background.add(buttonPanel);
            background.add(title);

            // Add background image to frame
            frame.add(background);

            buttons[0].addActionListener(e -> new ManagerLoginInterface());
            buttons[1].addActionListener(e -> new WorkerLoginInterface());
            buttons[2].addActionListener(e -> new CarWashInterface());
            buttons[3].addActionListener(e -> new TuckshopInterface());
            buttons[4].addActionListener(e -> new FuelInterface());

            frame.setVisible(true);
        }

        public class ManagerLoginInterface {
            public ManagerLoginInterface() {

                JFrame frame = new JFrame("Login");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.getContentPane().setBackground(new Color(230, 230, 250));


                JLabel label1 = new JLabel("LOGIN");
                label1.setFont(new Font("Cambria", Font.BOLD, 60));
                label1.setBounds(800, 150, 200, 60);
                label1.setForeground(Color.white);
                JLabel idLabel = new JLabel("ID:");
                idLabel.setBounds(780, 250, 100, 30);
                idLabel.setForeground(Color.WHITE);
                idLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
                JTextField idField = new JTextField();
                idField.setBounds(880, 250, 200, 25);

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setBounds(780, 300, 100, 30);
                passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
                passwordLabel.setForeground(Color.WHITE);
                JPasswordField passwordField = new JPasswordField();
                passwordField.setBounds(880, 300, 200, 25);

                JButton loginButton = new JButton("Login");
                loginButton.setBounds(830, 380, 100, 30);
                loginButton.setBackground(Color.WHITE);
                loginButton.setFont(new Font("Cambria", Font.PLAIN, 14));
                JButton returnButton = new JButton("Return");
                returnButton.setBounds(970, 380, 100, 30);
                returnButton.setFont(new Font("Cambria", Font.PLAIN, 14));
                returnButton.setBackground(Color.WHITE);


                frame.add(idLabel);
                frame.add(idField);
                frame.add(passwordLabel);
                frame.add(passwordField);
                frame.add(loginButton);
                frame.add(returnButton);
                frame.add(label1);

                loginButton.addActionListener(e -> {
                    try {
                        int loginId = Integer.parseInt(idField.getText().trim());
                        String password = new String(passwordField.getPassword()).trim();

                        if (loginId == 12 && password.equals("12345")) {
                            frame.dispose();
                            new ManagerInterface();
                        } else {
                            throw new IllegalArgumentException("Invalid Login ID or Password.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Login ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                returnButton.addActionListener(e -> frame.dispose());

                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("back7.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }

        // Manager Interface
        public class ManagerInterface {
            public ManagerInterface() {
                JFrame frame = new JFrame("Manager Interface");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                // Load background image
                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("back10.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 786, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel background = new JLabel(img333);
                background.setBounds(0, 0, 1322, 786);
                background.setLayout(null);

                JLabel title = new JLabel("Manage Worker");
                title.setFont(new Font("Algerian", Font.PLAIN, 50));
                title.setForeground(Color.BLACK);
                title.setBounds(450, 140, 500, 60);

                // Panel to hold buttons (transparent)
                JPanel buttonPanel = new JPanel();
                buttonPanel.setOpaque(false); // Transparent so image shows
                buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
                buttonPanel.setBounds(550, 250, 200, 200);

                String[] labels = {"Add Worker Task", "Show Worker Tasks", "Return"};
                JButton[] buttons = new JButton[labels.length];

                for (int i = 0; i < labels.length; i++) {
                    buttons[i] = new JButton(labels[i]);
                    buttons[i].setFont(new Font("Algerian", Font.PLAIN, 14));
                    buttons[i].setBackground(Color.GRAY);
                    buttons[i].setForeground(Color.black);
                    buttonPanel.add(buttons[i]);
                }

                background.add(buttonPanel);
                background.add(title);

                frame.add(background);

                buttons[0].addActionListener(e -> new AddWorkerInterface());
                buttons[1].addActionListener(e -> new WorkerData());
                buttons[2].addActionListener(e -> frame.dispose());

                frame.setVisible(true);
            }
        }

        public class WorkerLoginInterface {
            public WorkerLoginInterface() {
                JFrame frame = new JFrame("Login");
                frame.setSize(1920, 1080);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


                frame.setLocationRelativeTo(null);
                frame.getContentPane().setBackground(new Color(230, 230, 250));

                JLabel label1 = new JLabel("LOGIN");
                label1.setFont(new Font("Cambria", Font.BOLD, 60));
                label1.setBounds(800, 150, 200, 60);
                label1.setForeground(Color.white);
                JLabel usernameLabel = new JLabel("ID:");
                usernameLabel.setBounds(780, 250, 100, 30);
                usernameLabel.setForeground(Color.WHITE);
                usernameLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
                JTextField usernameField = new JTextField();
                usernameField.setBounds(880, 250, 200, 25);

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setBounds(780, 300, 100, 30);
                passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
                passwordLabel.setForeground(Color.WHITE);
                JPasswordField passwordField = new JPasswordField();
                passwordField.setBounds(880, 300, 200, 25);

                JButton loginButton = new JButton("Login");
                loginButton.setBounds(830, 380, 100, 30);
                loginButton.setBackground(Color.WHITE);
                loginButton.setFont(new Font("Cambria", Font.PLAIN, 14));
                JButton returnButton = new JButton("Return");
                returnButton.setBounds(970, 380, 100, 30);
                returnButton.setFont(new Font("Cambria", Font.PLAIN, 14));
                returnButton.setBackground(Color.WHITE);


                frame.add(usernameLabel);
                frame.add(usernameField);
                frame.add(passwordLabel);
                frame.add(passwordField);
                frame.add(loginButton);
                frame.add(returnButton);
                frame.add(label1);

                loginButton.addActionListener(e -> {
                    try {
                        int loginId = Integer.parseInt(usernameField.getText().trim());
                        String password = new String(passwordField.getPassword()).trim();


                        if (loginId == 1 && password.equals("12345")) {
                            frame.dispose();
                            new WorkerData();

                        } else {
                            throw new IllegalArgumentException("Invalid Login ID or Password.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Login ID.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                returnButton.addActionListener(e -> frame.dispose());

                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("back7.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }

        public class WorkerData {
            public WorkerData() {
                JFrame frame = new JFrame("Worker Data");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.getContentPane().setBackground(new Color(30, 30, 30)); // Dark gray
                frame.setLayout(new BorderLayout());

                String fetchSQL = "SELECT * FROM worker";

                try (Connection connection = DBConnection.getConnection()) {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(fetchSQL);

                    StringBuilder data = new StringBuilder("<html><pre style='font-size:16px'>");
                    data.append(String.format("   %-15s %-15s %-15s %-15s%n", "Name", "Duty", "Hours Worked", "Hourly Rate"));
                    data.append("--------------------------------------------------------------------------<br>");

                    while (rs.next()) {
                        String name = rs.getString("name");
                        String duty = rs.getString("duty");
                        int hoursWorked = rs.getInt("hours_worked");
                        double hourlyRate = rs.getDouble("hourlyrate");

                        data.append(String.format("   %-15s %-15s %-15d %-15.2f%n", name, duty, hoursWorked, hourlyRate));
                    }

                    data.append("</pre></html>");

                    JLabel dataLabel = new JLabel(data.toString());
                    JScrollPane scrollPane = new JScrollPane(dataLabel);
                    dataLabel.setOpaque(true);
                    dataLabel.setBackground(Color.GRAY);
                    dataLabel.setForeground(Color.WHITE);

                    frame.add(scrollPane, BorderLayout.CENTER);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Error fetching worker data: " + ex.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                frame.setVisible(true);
            }
        }


        public class AddWorkerInterface {
            public AddWorkerInterface() {
                JFrame frame = new JFrame("Worker");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLayout(null);

                Font font = new Font("Algerian", Font.PLAIN, 14);

                JLabel label1 = new JLabel("Add Worker Task");
                label1.setFont(new Font("Algerian", Font.PLAIN, 60));
                label1.setBounds(430, 100, 800, 60);
                label1.setForeground(Color.black);
                frame.add(label1);

                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                nameLabel.setBounds(520, 200, 120, 30);
                frame.add(nameLabel);

                JTextField nameField = new JTextField();
                nameField.setBounds(670, 200, 200, 30);
                frame.add(nameField);

                JLabel dutyLabel = new JLabel("Duty:");
                dutyLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                dutyLabel.setBounds(520, 250, 120, 30);
                frame.add(dutyLabel);

                JComboBox<String> dutyComboBox = new JComboBox<>(new String[]{"Carwash", "Petrol Refill"});
                dutyComboBox.setFont(font);
                dutyComboBox.setBounds(670, 250, 200, 30);
                frame.add(dutyComboBox);

                JLabel hoursLabel = new JLabel("Hours Worked:");
                hoursLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                hoursLabel.setBounds(520, 300, 120, 30);
                frame.add(hoursLabel);

                JTextField hoursField = new JTextField();
                hoursField.setBounds(670, 300, 200, 30);
                frame.add(hoursField);

                JLabel rateLabel = new JLabel("Hourly Rate:");
                rateLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                rateLabel.setBounds(520, 350, 120, 30);
                frame.add(rateLabel);

                JTextField rateField = new JTextField();
                rateField.setBounds(670, 350, 200, 30);
                frame.add(rateField);

                JButton submitButton = new JButton("Submit");
                submitButton.setFont(font);
                submitButton.setBounds(520, 420, 150, 40);
                submitButton.setBackground(Color.white);
                submitButton.setForeground(Color.black);
                frame.add(submitButton);

                JButton clearButton = new JButton("Clear");
                clearButton.setFont(font);
                clearButton.setBounds(720, 420, 150, 40);
                clearButton.setBackground(Color.white);
                clearButton.setForeground(Color.black);
                frame.add(clearButton);


                submitButton.addActionListener(e -> {
                    try {
                        String name = nameField.getText().trim();

                        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                            JOptionPane.showMessageDialog(frame,
                                    "Please enter a valid name (Alphabets and spaces only).",
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String duty = (String) dutyComboBox.getSelectedItem();
                        int hoursWorked = Integer.parseInt(hoursField.getText().trim());
                        double hourlyRate = Double.parseDouble(rateField.getText().trim());

                        String insertSQL = "INSERT INTO worker (name, duty, hours_worked, hourlyrate) VALUES (?, ?, ?, ?)";

                        try (Connection connection = DBConnection.getConnection()) {
                            var stmt = connection.prepareStatement(insertSQL);
                            stmt.setString(1, name);
                            stmt.setString(2, duty);
                            stmt.setInt(3, hoursWorked);
                            stmt.setDouble(4, hourlyRate);

                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(frame, "Worker data saved successfully!");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Error saving worker data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter valid numeric values for Hours Worked and Hourly Rate.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                clearButton.addActionListener(e -> {
                    nameField.setText("");
                    hoursField.setText("");
                    rateField.setText("");
                    dutyComboBox.setSelectedIndex(0);
                });

                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("image1.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }


        // Car Wash Interface
        public class CarWashInterface {
            public CarWashInterface() {
                JFrame frame = new JFrame("Car Wash");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLayout(null);

                Font font = new Font("Algerian", Font.PLAIN, 14);

                JLabel label1 = new JLabel("Car Wash");
                label1.setFont(new Font("Algerian", Font.PLAIN, 60));
                label1.setBounds(525, 120, 800, 60);
                label1.setForeground(Color.black);
                frame.add(label1);

                JLabel carNameLabel = new JLabel("Car Name:");
                carNameLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                carNameLabel.setBounds(510, 200, 120, 30);
                frame.add(carNameLabel);

                JTextField carNameField = new JTextField();
                carNameField.setBounds(660, 200, 200, 30);
                frame.add(carNameField);

                JLabel washTypeLabel = new JLabel("Wash Type:");
                washTypeLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                washTypeLabel.setBounds(510, 250, 120, 30);
                frame.add(washTypeLabel);

                JComboBox<String> washTypeComboBox = new JComboBox<>(new String[]{"Full", "Inner", "Outer"});
                washTypeComboBox.setFont(font);
                washTypeComboBox.setBounds(660, 250, 200, 30);
                frame.add(washTypeComboBox);

                JLabel carModelLabel = new JLabel("Car Model (1800–2024):");
                carModelLabel.setBounds(510, 300, 200, 30);
                frame.add(carModelLabel);

                JTextField carModelField = new JTextField();
                carModelField.setBounds(660, 300, 200, 30);
                frame.add(carModelField);

                JButton submitButton = new JButton("Submit");
                submitButton.setFont(font);
                submitButton.setBounds(510, 370, 150, 40);
                submitButton.setBackground(Color.white);
                submitButton.setForeground(Color.black);
                frame.add(submitButton);

                JButton clearButton = new JButton("Clear");
                clearButton.setFont(font);
                clearButton.setBounds(710, 370, 150, 40);
                clearButton.setBackground(Color.white);
                clearButton.setForeground(Color.black);
                frame.add(clearButton);

                JButton washDetailButton = new JButton("Show Save Data");
                washDetailButton.setFont(font);
                washDetailButton.setBounds(600, 430, 180, 40);
                washDetailButton.setBackground(Color.white);
                washDetailButton.setForeground(Color.black);
                frame.add(washDetailButton);

                submitButton.addActionListener(e -> {
                    try {
                        String carName = carNameField.getText().trim();
                        String washType = (String) washTypeComboBox.getSelectedItem();
                        int carModel = Integer.parseInt(carModelField.getText().trim());

                        if (carModel < 1800 || carModel > 2024) {
                            JOptionPane.showMessageDialog(frame,
                                    "Car Model must be between 1800 and 2024.",
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further execution
                        }


                        // Calculate price based on wash type
                        int price = 0;
                        if ("full".equals(washType)) {
                            price = 2000;
                        } else if ("inner".equals(washType)) {
                            price = 1200;
                        } else if ("outer".equals(washType)) {
                            price = 1000;
                        }

                        // Insert car wash data into the database
                        String insertSQL = "INSERT INTO car_wash (car_name, wash_type, car_model, price) VALUES (?, ?, ?, ?)";

                        try (Connection connection = DBConnection.getConnection()) {
                            var stmt = connection.prepareStatement(insertSQL);
                            stmt.setString(1, carName);
                            stmt.setString(2, washType);
                            stmt.setInt(3, carModel);
                            stmt.setInt(4, price);

                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(frame, "Car wash details saved successfully!");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Error saving car wash details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter a valid numeric value for Car Model.",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                clearButton.addActionListener(e -> {
                    carNameField.setText("");
                    carModelField.setText("");
                    washTypeComboBox.setSelectedIndex(0);
                });

                washDetailButton.addActionListener(e -> {
                    String fetchSQL = "SELECT * FROM car_wash";

                    try (Connection connection = DBConnection.getConnection()) {
                        var stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(fetchSQL);

                        StringBuilder data = new StringBuilder("Car Wash Details:\n\n");
                        while (rs.next()) {
                            String carName = rs.getString("car_name");
                            String washType = rs.getString("wash_type");
                            int carModel = rs.getInt("car_model");
                            int price = rs.getInt("price");

                            data.append("Car Name: ").append(carName)
                                    .append("\nWash Type: ").append(washType)
                                    .append("\nCar Model: ").append(carModel)
                                    .append("\nPrice: ").append(price)
                                    .append("\n\n");
                        }

                        if (data.length() > "Car Wash Details:\n\n".length()) {
                            JOptionPane.showMessageDialog(frame, data.toString());
                        } else {
                            JOptionPane.showMessageDialog(frame, "No car wash data found.");
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error fetching car wash data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("image1.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }




        // Tuckshop Interface
        class TuckshopInterface {
            public TuckshopInterface(){
            JFrame frame = new JFrame("Tuckshop");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
            Font font = new Font("Algerian", Font.PLAIN, 14);

                JLabel label1 = new JLabel("Tuck Shop");
                label1.setFont(new Font("Algerian", Font.PLAIN, 60));
                label1.setBounds(520, 120, 800, 60);
                label1.setForeground(Color.black);
                frame.add(label1);


            JLabel itemsLabel = new JLabel("Select Item:");
                itemsLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                itemsLabel.setBounds(510, 200, 120, 30);
        frame.add(itemsLabel);

            JComboBox<String> itemsComboBox = new JComboBox<>(new String[]{"Biscuit", "Bubble", "Cold Drink", "Chocolate"});
        itemsComboBox.setFont(font);
        itemsComboBox.setBounds(650, 200, 200, 30);
        itemsComboBox.setBackground(Color.white);
        itemsComboBox.setForeground(Color.black);
        frame.add(itemsComboBox);

            JLabel quantityLabel = new JLabel("Quantity:");
            quantityLabel.setFont(new Font("Cambria", Font.BOLD, 13));
            quantityLabel.setBounds(510, 250, 150, 30);
            frame.add(quantityLabel);

            JTextField quantityField = new JTextField();
        quantityField.setBounds(650, 250, 200, 30);
        frame.add(quantityField);

            JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculateButton.setBounds(580, 320, 180, 40);
        calculateButton.setBackground(Color.white);
        calculateButton.setForeground(Color.black);
        frame.add(calculateButton);

            JLabel resultLabel = new JLabel("Total Price: PKR 0.00", JLabel.CENTER);
        resultLabel.setBounds(550, 370, 350, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.BLACK);
        frame.add(resultLabel);
                calculateButton.addActionListener(e -> {
                    try {
                        String item = (String) itemsComboBox.getSelectedItem();
                        int quantity = Integer.parseInt(quantityField.getText());

                        double pricePerItem = switch (item) {
                            case "Biscuit" -> 60.0;
                            case "Bubble" -> 10.0;
                            case "Cold Drink" -> 120.0;
                            case "Chocolate" -> 250.0;
                            default -> 0.0;
                        };

                        double totalPrice = quantity * pricePerItem;
                        resultLabel.setText("Total Price: PKR " + String.format("%.2f", totalPrice));  //For keeping 2 digits after the decimal point.
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("image1.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }

        class FuelInterface {
            public FuelInterface() {

                JFrame frame = new JFrame("Fuel Price Calculator");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLayout(null);

                Font font = new Font("Algerian", Font.PLAIN, 14);


                JLabel label1 = new JLabel("Fuel");
                label1.setFont(new Font("Algerian", Font.PLAIN, 60));
                label1.setBounds(590, 120, 800, 60);
                label1.setForeground(Color.black);
                frame.add(label1);

                JLabel quantityLabel = new JLabel("Enter Quantity (liters):");
                quantityLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                quantityLabel.setBounds(480, 210, 150, 30);
                frame.add(quantityLabel);

                JTextField quantityField = new JTextField();
                quantityField.setBounds(670, 210, 200, 30);
                frame.add(quantityField);

                JLabel fuelLabel = new JLabel("Select Fuel Type:");
                fuelLabel.setFont(new Font("Cambria", Font.BOLD, 13));
                fuelLabel.setBounds(480, 260, 200, 30);
                frame.add(fuelLabel);

                JComboBox<String> fuelComboBox = new JComboBox<>(new String[]{"Petrol", "Diesel"});
                fuelComboBox.setFont(font);
                fuelComboBox.setBounds(670, 260, 200, 30);
                fuelComboBox.setBackground(Color.white);
                fuelComboBox.setForeground(Color.black);
                frame.add(fuelComboBox);

                JButton calculateButton = new JButton("Calculate");
                calculateButton.setFont(font);
                calculateButton.setBounds(600, 320, 180, 40);
                calculateButton.setBackground(Color.white);
                calculateButton.setForeground(Color.black);
                frame.add(calculateButton);

                JLabel resultLabel = new JLabel("Total Price: PKR 0.00", JLabel.CENTER);
                resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
                resultLabel.setBounds(590, 380, 300, 30);
                frame.add(resultLabel);

                calculateButton.addActionListener(e -> {
                    try {
                        double quantity = Double.parseDouble(quantityField.getText());
                        String selectedFuel = (String) fuelComboBox.getSelectedItem();

                        Fuel fuel;
                        if ("Petrol".equals(selectedFuel)) {
                            fuel = new Petrol();
                        } else {
                            fuel = new Diesel();
                        }


                        double price = fuel.calculatePrice(quantity);

                        resultLabel.setText("Total Price: PKR " + String.format("%.2f", price)); // upto 2 decimal points
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter a valid quantity!",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                ImageIcon img111 = new ImageIcon(ClassLoader.getSystemResource("image1.jpg"));
                Image img222 = img111.getImage().getScaledInstance(1322, 768, Image.SCALE_SMOOTH);
                ImageIcon img333 = new ImageIcon(img222);
                JLabel image112 = new JLabel(img333);
                image112.setBounds(0, 0, 1322, 768);
                image112.setLayout(null);
                frame.add(image112);

                frame.setVisible(true);
            }
        }

        public class DBConnection {
            public static Connection getConnection() throws SQLException {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    return DriverManager.getConnection("jdbc:mysql://localhost:3306/aree_", "root", "");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    throw new SQLException("Database connection failed.");
                }
            }
        }


    }
}
