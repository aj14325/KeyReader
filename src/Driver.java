import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Driver {

    private Label b3;
    private static final ArrayList<Student> students = new ArrayList<Student>();
    private static final ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private static final ArrayList<Course> courses = new ArrayList<Course>();
    static int state = 0;
    static Boolean keycard = false;
    static String ID = "";
    static JLabel statey;
    static String fname;
    static String lname;
    static String studentCourse;
    static String confirmstr;
    static Timer timer;
    static JTextField text;
    static JLabel label;
    static JButton checkIn;
    static JButton checkOut;
    static JLabel confirm;
    static JMenuBar Menu;

    public static void main(String[] arguments) {
        timer = new Timer(150000000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 0;
                label.setText("Welcome to the Math Success Center. Please enter your student ID (Be sure to put it in correctly or you will break everything).");
                confirm.setText("");
                checkIn.setText("Check In");
                checkOut.setText("Check Out");
                fname = ("");
                lname = ("");
                ID = ("");
                text.setText("");
                updateState();
            }
        });

        JFrame frame = new JFrame("Keycard Reader - Math Success Center");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Container content = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);

        confirm = new JLabel("");
        confirm.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        statey = new JLabel("State 0");
        statey.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        checkOut = new JButton("Check Out");
        checkOut.setPreferredSize(new Dimension(150, 50));
        checkOut.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        checkIn = new JButton("Check In");
        checkIn.setPreferredSize(new Dimension(150, 50));
        checkIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        text = new JTextField();
        text.setPreferredSize(new Dimension(200, 50));
        label = new JLabel("Welcome to the Math Success Center. Please enter your student ID (Be sure to put it in correctly or you will break everything).");
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));

        frame.add(checkOut);
        frame.add(checkIn);
        frame.add(text);
        frame.add(label);
        frame.add(statey);
        frame.add(confirm);


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkOut, 250, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkOut, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkIn, -250, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkIn, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text, 0, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, text, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, -100, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, statey, 450, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, statey, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirm, 0, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, confirm, 100, SpringLayout.VERTICAL_CENTER, content);

        checkIn.addActionListener(new ActionListener() { //this is also the back button
            @Override
            public void actionPerformed(ActionEvent e) {

                if (state > 0) {
                    retreat();
                } else {
                    advance();
                }
                timer.restart();
                updateState();
            }

        });

        checkOut.addActionListener(new ActionListener() { // this is also the next button
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state > 0) {
                    advance();
                } else if (state == 9) {
                    //TODO GenerateReports!
                } else {
                    retreat();
                }
                updateState();
                timer.restart();
            }
        });
        //text.addActionListener();


        text.addKeyListener(new CustomKeyListener());

        frame.setVisible(true);

    }

    public static void updateState() {
        statey.setText("State: " + state);
    } // Check in/back button

    public static void retreat() { // Check in/back button
        if (state == 0) {


        } else if (state == 1) {
            ID = "";
            text.setText("");
            checkIn.setText("Check In");
            checkOut.setText("Check Out");
            label.setText("Welcome to the Math Success Center. Please enter your student ID (Be sure to put it in correctly or you will break everything).");
            state--;
        } else if (state == 2) {
            text.setText("");
            label.setText("ID not found, create a new profile. Enter your ID again.");
            state--;
        } else if (state == 3) {
            text.setText("");
            label.setText("Enter your first name.");
            fname = ("");
            state--;
        } else if (state == 4) {
            text.setText("");
            label.setText("Enter your last name.");
            lname = ("");
            confirm.setText("");
            state--;
        } else if (state == 5) {
            text.setText("");
            label.setText("Enter your math class.");
            lname = ("");
            confirm.setText("");
            state--;
        } else if (state == 9) {
            label.setText("Welcome to the Math Success Center. Please enter your student ID (Be sure to put it in correctly or you will break everything).");
            confirm.setText("");
            checkIn.setText("Check In");
            checkOut.setText("Check Out");
            ID = ("");
            text.setText("");
            state = 0;
        }

    }


    public static void Check(String ID, String fName, String lName, String course) {

        //TODO if they need to be checked in, check them in, if they need to be checked out, check them out


    }

    public static void CheckIn(String ID, String fName, String lName, String course) {

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getIdentification().compareTo(ID) == 0) {
                if (s.getLastCheckoutTime().isBefore(LocalDateTime.now())) {
                    s.setCheckIn();
                    return;
                }else{
                    //TODO NOTIFICATION!
                    return;
                }
            }
        }

        Student s = new Student(fName,lName,ID);
        Course c = new Course("course");
        ArrayList<Course> cl = new ArrayList<Course>();
        c.add(s);
        cl.add(c);
        s.setCourses(cl);
        students.add(s);
        courses.add(c);

    }

    public static void Checkout(String ID) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getIdentification().compareTo(ID) == 0) {
                if (s.getLastCheckoutTime().isAfter(LocalDateTime.now())) {
                    s.setCheckOut();
                    return;
                }else{
                    //TODO NOTIFICATION!
                    return;
                }
            }
        }
    }

    public static void advance() {

        if (text.getText().compareTo("admin") == 0) {
            state = 9;
            label.setText("Enter the admin password.");
            checkIn.setText("Back");
            checkOut.setText("Enter");

            return;

        }

        if (state == 0) {


            if (text.getText().length() != 9) {
                label.setText("Please enter a valid ID.");


            } else {
                Boolean b = true;
                ID = text.getText();
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getIdentification() == ID) {
                        label.setText("Welcome " + fname + ".");
                        text.setText("");
                        CheckIn(ID,fname,lname,studentCourse);
                        b = false;
                    }
                }

                if (b) {
                    state++;
                    label.setText("ID not found, create a new profile. Enter your ID again.");
                    text.setText("");
                    checkIn.setText("Back");
                    checkOut.setText("Next");
                }
            }
        } else if (state == 1) {
            if (text.getText().length() != 9) {
                text.setText("");
                label.setText("Please enter your ID again.");

            } else {
                if (text.getText().compareTo(ID) != 0) {
                    label.setText("This is different than your original ID.");
                    text.setText("");
                } else {
                    label.setText("Enter your first name.");
                    text.setText("");
                    state++;
                }
            }
        } else if (state == 2) {
            if (text.getText().length() == 0) {
                label.setText("Please enter your first name.");
            } else {
                fname = text.getText();
                label.setText("Enter your last name.");
                text.setText("");
                state++;
            }
        } else if (state == 3) {
            if (text.getText().length() == 0) {
                label.setText("Please enter your last name.");
            } else {
                state++;
                lname = text.getText();
                label.setText("Enter your math class.");
                text.setText("");
            }
        } else if (state == 4) {
            if (text.getText().length() == 0) {
                label.setText("Please enter your math class.");
            } else {
                state++;
                studentCourse = text.getText();
                label.setText("Please confirm that your information is correct. If it is, click next. If it isn't, go back and fix any mistakes.");
                text.setText("");
                confirm.setText("ID: " + ID + "      First Name: " + fname + "      Last Name: " + lname + "      Class: " + studentCourse);
            }
        } else if (state == 5) {
            state = 0;
            label.setText("Welcome to the Math Success Center. Please enter your student ID (Be sure to put it in correctly or you will break everything).");
            confirm.setText("");
            checkIn.setText("Check In");
            checkOut.setText("Check Out");
            fname = ("");
            lname = ("");
            ID = ("");
            text.setText("");
            //actaully check this person in and then clear
        } else if (state == 9) {
            if (text.getText().compareTo("Admin1") == 0) {
                label.setText("You're in admin mode.");
                checkIn.setText("Back");
                checkOut.setText("Generate Reports");
                state++;
                return;
            } else {
                label.setText("Incorrect password; Enter the admin password");
                state = 9;

            }
        }else if(state == 10){
            String path = LocalDateTime.now().toString() + "_Report.csv";
            FileIO.printReports(students, teachers, courses, path);
        }

    }

    static class CustomKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {


        }

        public void keyPressed(KeyEvent e) {

        }

        public void keyReleased(KeyEvent e) {
            timer.restart();
            if (text.getText().contains(";")) {
                text.setText(text.getText().replace(";", " ").trim());
                keycard = true;
            }

            if (text.getText().contains("?")) {
                if (keycard) {
                    text.setText(text.getText().replace("?", " ").substring(0, 9).trim());
                    keycard = false;
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                advance();
                updateState();

            }
        }
    }
}