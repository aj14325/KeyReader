import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Driver {

    private Label b3;
    private static final ArrayList<Student> students = new ArrayList<Student>();
    private static final ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private static final ArrayList<Course> courses = new ArrayList<Course>();
    static int state = 0;
    static String ID = "";
    static JLabel statey;
    static String fname;
    static String lname;
    static String confirmstr;
    static Timer timer;
    static JTextField text;
    static JLabel label;
    static JButton checkIn;
    static JButton checkOut;
    static JLabel confirm;

    public static void main(String[] arguments) {
        timer = new Timer(15000, new ActionListener() {
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
        statey.setFont(new Font("Comic Sans MS", Font.PLAIN, 0));
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

                if(state > 0) {
                    retreat();
                }else {
                    advance();
                }
                timer.restart();
                updateState();
            }

        });

        checkOut.addActionListener(new ActionListener() { // this is also the next button
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state > 0) {
                    advance();
                }else{
                    retreat();
                }
                updateState();
                timer.restart();
            }
        });
        //text.addActionListener();

        text.addKeyListener(new CustomKeyListener()) ;

        frame.setVisible(true);

    }

    public static void updateState() {
        statey.setText("State: " + state);
    }

    public static void retreat(){ // The Rabbit is coming!
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
        }
    }

    public static Student find(String ID){

        for(Student s : students){
            if(s.getIdentification() == "ID"){
                return s;
            }
        }
        return null;
    }


    public static void Check(Student s){

        //TODO if they need to be checked in, check them in, if they need to be checked out, check them out



    }

    public static void Checkin(){

    }
    public static void Checkout(){

    }

    public static void GenerateReports(){

    }

    public static void advance() {
        if (state == 0) {
            if (text.getText().length() != 9) {
                label.setText("Please enter a valid ID.");

            } else {
                Boolean b = true;
                ID = text.getText();
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getIdentification() == ID) {
                        label.setText("You entered your ID.");
                        text.setText("");
                        b = false;
                    }

                    //actually check the user in (this should probably be a function)

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
                label.setText("Please confirm that your information is correct. If it is, click next. If it isn't, go back and fix any mistakes.");
                text.setText("");
                confirm.setText("ID: " + ID + "      First Name: " + fname + "      Last Name: " + lname);
            }
        } else if (state == 4) {
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
        }
    }
    static class CustomKeyListener implements KeyListener{

        public void keyTyped(KeyEvent e) {
        }
        public void keyPressed(KeyEvent e) {

        }
        public void keyReleased(KeyEvent e) {
            timer.restart();
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                advance();
            }
        }
    }





}


/*import  java.awt.*;
import  java.awt.event.*;
import  java.io.*;
import  javax.swing.*;
import  com.magtek.windows.scra.usb.*;
import  javax.swing.event.*;


import  com.magtek.msr;



 @author <a href="mailto:djacobs@modelobjects.com">Dan Jacobs</a> of
           <a href="http://www.modelobjects.com">ModelObjects Group</a>,

public class Driver extends JFrame
        implements ActionListener, Runnable
{


    private static final long serialVersionUID = 1L;
    public static void main(String[] args)
    {
        Driver test1 = new Driver();
        test1.setBounds(20, 60, 700, 500);
        test1.setVisible(true);
    }

    public Driver()
    {
        super("Swipe Reader Control Panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lTotalReadCount=0;
        setupUI();
        try {
            setupService();
        }
        catch (Exception e) {
            appendMessage("Exception: " + e);
        }
        mMagTekUSCRAEvent		= new MagTekUSCRAEventHandler();
        updateTimer = new Timer(50, this);
        updateTimer.setRepeats(false);
    }

    void setupService()
    {
        try
        {
            m_MTSCRA = null;
            enableControls();
        }
        catch(Exception ex)
        {
        }
    }

    void setupUI()
    {
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("MonoSpaced", Font.PLAIN, 12));

        checkinButton    = new JButton("Checkin");
        closeButton   = new JButton("Close");
        clearButton   = new JButton("Clear");

        openButton.addActionListener(this);
        closeButton.addActionListener(this);
        clearButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel checkboxPanel = new JPanel(new BorderLayout());
        JPanel checkboxTopPanel = new JPanel(new FlowLayout());
        JPanel checkboxBottomPanel = new JPanel(new FlowLayout());
        checkboxPanel.add(checkboxTopPanel, BorderLayout.NORTH);
        checkboxPanel.add(checkboxBottomPanel, BorderLayout.SOUTH);

        buttonPanel.add(openButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(clearButton);

        JPanel bottomPanel = new JPanel(new BorderLayout(4, 4));
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(checkboxPanel, BorderLayout.SOUTH);

        JPanel outerPanel = new JPanel(new BorderLayout(4, 4));
        outerPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.getContentPane().add(outerPanel, BorderLayout.CENTER);

    }
    public void closeDevice()
    {
        m_MTSCRA.closeDevice();
        if (!m_MTSCRA.isDeviceConnected())
        {
            openButton.setEnabled(true);
            closeButton.setEnabled(false);
        }

    }
    public void actionPerformed(ActionEvent event)
    {
        Object b = event.getSource();

        try {
            if (b == clearButton)
            {
                textArea.setText("");
                lTotalReadCount=0;
            }
            else if (b == openButton)
            {
                if (m_MTSCRA == null)
                {
                    m_MTSCRA = new MagTekUSCRA();
//			  m_MTSCRA.setParameters("magtek.msr.Run64BitJNI=true");
//			  m_MTSCRA.setParameters("magtek.msr.Run32bitJNIOn64bitPlatform=true");
                    m_MTSCRA.setParameters("magtek.msr.CustomJNIPath=C:\\Windows\\System32\\");
                    m_MTSCRA.init(mMagTekUSCRAEvent);
                }
                if(!m_MTSCRA.isDeviceConnected())
                {
                    m_MTSCRA.openDevice();
                }

            }
            else if (b == closeButton)
            {
                closeDevice();
            }
            if (b == updateTimer) {
                enableControls();
            }
            else {
                updateTimer.start();
            }
        }
        catch (Exception e) {
            reportException(e);
        }
    }
    void printData()
            throws Exception
    {

        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        out.println("#####  Data parsed from card  #####");
        out.println("  sdk version   = [" + new String(m_MTSCRA.getSDKVersion()) + "]");
        out.println("  encode type    = [" + new String(m_MTSCRA.getEncodeType()) + "]");
        out.println("  track1 dcd sts    = [" + new String(m_MTSCRA.getTrack1DecodeStatus()) + "]");
        out.println("  track2 dcd sts    = [" + new String(m_MTSCRA.getTrack2DecodeStatus()) + "]");
        out.println("  track3 dcd sts    = [" + new String(m_MTSCRA.getTrack3DecodeStatus()) + "]");
        out.println("  track1 data    = [" + new String(m_MTSCRA.getTrack1()) + "]");
        out.println("  track2 data    = [" + new String(m_MTSCRA.getTrack2()) + "]");
        out.println("  track3 data    = [" + new String(m_MTSCRA.getTrack3()) + "]");
        out.println("  masked track1 data    = [" + new String(m_MTSCRA.getTrack1Masked()) + "]");
        out.println("  masked track2 data    = [" + new String(m_MTSCRA.getTrack2Masked()) + "]");
        out.println("  masked track3 data    = [" + new String(m_MTSCRA.getTrack3Masked()) + "]");
        out.println("  masked track data    = [" + new String(m_MTSCRA.getMaskedTracks()) + "]");
        out.println("  mp data    = [" + new String(m_MTSCRA.getMagnePrint()) + "]");
        out.println("  mp status    = [" + new String(m_MTSCRA.getMagnePrintStatus()) + "]");
        out.println("  ksn    = [" + new String(m_MTSCRA.getKSN()) + "]");
        out.println("  device serial    = [" + new String(m_MTSCRA.getDeviceSerial()) + "]");
        out.println("  session id    = [" + new String(m_MTSCRA.getSessionID()) + "]");
        out.println("  pan  = [" + new String(m_MTSCRA.getPAN()) + "]");
        out.println("  exp date   = [" + new String(m_MTSCRA.getExpDate()) + "]");
        out.println("  last name   = [" + new String(m_MTSCRA.getLastName()) + "]");
        out.println("  suffix name   = [" + new String(m_MTSCRA.getMiddleName()) + "]");
        out.println("  first name   = [" + new String(m_MTSCRA.getFirstName()) + "]");
        m_MTSCRA.clearBuffer();
        appendMessage(stringWriter.toString());
    }

    void enableControls()
            throws Exception
    {

        if (m_MTSCRA != null)
        {
            if (m_MTSCRA.isDeviceConnected())
            {
                openButton.setEnabled(false);
                closeButton.setEnabled(true);
            }
            else
            {
                openButton.setEnabled(true);
                closeButton.setEnabled(false);

            }

        }
        else
        {
            openButton.setEnabled(true);
            closeButton.setEnabled(true);
        }
    }

    void reportException(Exception e)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        e.printStackTrace(out);
        appendMessage(stringWriter.toString());
    }

    void appendMessage(String message)
    {
        textArea.setText
                (textArea.getText() + message +
                        "\n-----------------------------------------------------------------\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    void clearMessage()
    {
        textArea.setText("");
    }
    private class MagTekUSCRAEventHandler implements MagTekUSCRAEvent
    {
        public MagTekUSCRAEventHandler()
        {
        }


        //-----------------------------------------
        //
        public void onDataReceived(String data)
        {
            try
            {
                printData();
            }
            catch (Exception ex)
            {

            }
        }


        public void onError(int errorCode)
        {
            StringWriter stringWriter = new StringWriter();
            PrintWriter out = new PrintWriter(stringWriter);
            try
            {
                out.println("onError:" + errorCode);
                appendMessage(stringWriter.toString());

            }
            catch (Exception ex)
            {

            }
        }

        public void onDeviceConnectionStateChanged(int stateCode)
        {

            StringWriter stringWriter = new StringWriter();
            PrintWriter out = new PrintWriter(stringWriter);
            try
            {
                if(stateCode==0)
                {
                    out.println("Device State:Not Connected");
                    closeDevice();
                }
                else if(stateCode==1)
                {
                    out.println("Device State:Connected");
                }
                else
                {
                    out.println("Device State:Error");
                }
                appendMessage(stringWriter.toString());
            }
            catch (Exception ex)
            {

            }
        }

    }


    public void run()
    {
        try {
            enableControls();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    MagTekUSCRA    m_MTSCRA;

    JTextArea     textArea;

    JButton       checkinButton;
    JButton       closeButton;
    JButton       clearButton;
    Timer         updateTimer;
    long           lTotalReadCount;
    private MagTekUSCRAEvent	mMagTekUSCRAEvent;

}

//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import com.magtek.windows.scra.usb.*;
//
//public class Driver {
//
//    private static ArrayList<String> userInput = new ArrayList<String>();
//
//    private static MagTekUSCRA m_MTSCRA = null;
//    private static MagTekUSCRAEvent mMagTekUSCRAEvent = null;
//
//    public static void main(String args[]) {
//
//        //System.loadLibrary("MTUSCRAJ.DLL");
//        //System.loadLibrary("MTUSCRA.DLL");
//
//        Scanner s = new Scanner(System.in);
//        System.out.println("Please connect the keyreader, once connected press enter to continue");
//       // s.nextLine();
//        mMagTekUSCRAEvent= new MagTekUSCRAEventHandler();
//
//
//        int tmp = 0;
//        while (true) {
//
//            tmp++;
//            try {
//                if (m_MTSCRA == null) {
//                    m_MTSCRA = new MagTekUSCRA();
////			  m_MTSCRA.setParameters("magtek.msr.Run64BitJNI=true");
////			  m_MTSCRA.setParameters("magtek.msr.Run32bitJNIOn64bitPlatform=true");
//                    m_MTSCRA.setParameters("magtek.msr.CustomJNIPath=C:\\Windows\\SysWOW64\\");
//                    m_MTSCRA.init(mMagTekUSCRAEvent);
//
//                }
//                if (!m_MTSCRA.isDeviceConnected()) {
//                    m_MTSCRA.openDevice();
//                    break;
//                }
//            } catch (Exception e) {
//
//            }
//            if(tmp > 5){
//                System.out.println("Error connecting to keyreader, please ensure keyreader is connected to the computer");
//                System.out.println("Exiting");
//                return;
//            }
//        }
//
//        while(true){
//            if(!userInput.isEmpty()){
//                System.out.println(userInput.get(0));
//                userInput.remove(0);
//            }
//        }
//
//    }
//
//    public void run() {
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static class MagTekUSCRAEventHandler implements MagTekUSCRAEvent {
//        public MagTekUSCRAEventHandler() {
//        }
//        //-----------------------------------------
//        //
//        public void onDataReceived(String data) {
//            try {
//                userInput.add(data);
//            } catch (Exception ex) {
//
//            }
//        }
//
//        public void onError(int errorCode) {
//            try {
//                System.out.println("onError:" + errorCode);
//
//            } catch (Exception ex) {
//
//            }
//        }
//
//        public void onDeviceConnectionStateChanged(int stateCode) {
//
//            try {
//                if (stateCode == 0) {
//                    System.out.println("Device State:Not Connected");
//                    m_MTSCRA.closeDevice();
//                } else if (stateCode == 1) {
//                    System.out.println("Device State:Connected");
//                } else {
//                    System.out.println("Device State:Error");
//                }
//
//            } catch (Exception ex) {
//
//            }
//        }
//
//    }
//} */
