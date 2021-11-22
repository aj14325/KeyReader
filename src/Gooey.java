
/*import java.io.*;
import java.util.ArrayList;


public class Teacher {
    String last_name;
    String first_name;
    String ID_Number;
    String teach_class;
    ArrayList<Class> Classes  = new  ArrayList<Class>();
    //Teachers have a unique class "Teacher"
    //Teachers all need to be stored inside of
    //Time- an array or exact time or both, nad will it be in the teacher string
    //Something to store all the classes they teach
    Teacher(String ID, String first, String last, String teach) throws IOException {
        this.first_name = first;
        this.last_name = last;
        this. teach_class = teach;
        this.ID_Number = ID;
        FileWriter saveFile = null;

        saveFile = new FileWriter("JavaKeyReader.txt");

        if(saveFile != null) {
            saveFile.write("Teacher");
        }
    }
}
*/
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gooey {

    private Label b3;

    public static void main(String[] arguments) {

        JFrame frame = new JFrame("Keycard Reader - Math Success Center");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Container content = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);

        JButton checkOut = new JButton("Check Out");
        checkOut.setPreferredSize(new Dimension(150, 50));
        JButton checkIn = new JButton("Check In");
        checkIn.setPreferredSize(new Dimension(150, 50));
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 50));
        JLabel label = new JLabel("Welcome to the Math Success Center. Please enter your student ID. Did you know that the first person convicted of speeding was going eight mph?");

        frame.add(checkOut);
        frame.add(checkIn);
        frame.add(textField);
        frame.add(label);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkOut, 250, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkOut, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkIn, -250, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkIn, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textField, 0, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, 0, SpringLayout.VERTICAL_CENTER, content);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, content);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, -100, SpringLayout.VERTICAL_CENTER, content);

        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });

        frame.setVisible(true);


    }

    }
