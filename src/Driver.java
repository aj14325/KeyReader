/*----------------------------------------------------------------------------*
 *                                                                            *
 *  Copyright (c) 2002, 2003 MagTek, Inc.  All Rights Reserved.               *
 *                                                                            *
 *----------------------------------------------------------------------------*/


import  java.awt.*;
import  java.awt.event.*;
import  java.io.*;
import  javax.swing.*;
import  com.magtek.windows.scra.usb.*;
//import  javax.swing.event.*;


//import  com.magtek.msr;

/**
 *  SwipeReaderControlPanel is a more sophisticated test program that
 *  uses a simple Swing GUI to test various event-control settings and
 *  see the output of parsed data and errors.
 *
 *  @author <a href="mailto:djacobs@modelobjects.com">Dan Jacobs</a> of
 *          <a href="http://www.modelobjects.com">ModelObjects Group</a>,
 */
public class Driver extends JFrame
        implements ActionListener, Runnable
{
    /**
     *
     */
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

        openButton    = new JButton("Open");
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

    JButton       openButton;
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
//}
