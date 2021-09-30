import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.magtek.windows.scra.usb.*;

public class Driver {

    private static ArrayList<String> userInput = new ArrayList<String>();

    private static MagTekUSCRA m_MTSCRA = null;
    private static MagTekUSCRAEvent mMagTekUSCRAEvent = null;

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please connect the keyreader, once connected press enter to continue");
        s.nextLine();
        mMagTekUSCRAEvent= new MagTekUSCRAEventHandler();


        int tmp = 0;
        while (true) {
            tmp++;
            try {
                if (m_MTSCRA == null) {
                    m_MTSCRA = new MagTekUSCRA();
//			  m_MTSCRA.setParameters("magtek.msr.Run64BitJNI=true");
//			  m_MTSCRA.setParameters("magtek.msr.Run32bitJNIOn64bitPlatform=true");
                    m_MTSCRA.init(mMagTekUSCRAEvent);
                }
                if (!m_MTSCRA.isDeviceConnected()) {
                    m_MTSCRA.openDevice();
                    break;
                }
            } catch (Exception e) {

            }
            if(tmp > 5){
                System.out.println("Error connecting to keyreader, please ensure keyreader is connected to the computer");
                System.out.println("Exiting");
                return;
            }
        }

        while(true){
            if(!userInput.isEmpty()){
                System.out.println(userInput.get(0));
                userInput.remove(0);
            }
        }

    }

    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MagTekUSCRAEventHandler implements MagTekUSCRAEvent {
        public MagTekUSCRAEventHandler() {
        }
        //-----------------------------------------
        //
        public void onDataReceived(String data) {
            try {
                userInput.add(data);
            } catch (Exception ex) {

            }
        }

        public void onError(int errorCode) {
            try {
                System.out.println("onError:" + errorCode);

            } catch (Exception ex) {

            }
        }

        public void onDeviceConnectionStateChanged(int stateCode) {

            try {
                if (stateCode == 0) {
                    System.out.println("Device State:Not Connected");
                    m_MTSCRA.closeDevice();
                } else if (stateCode == 1) {
                    System.out.println("Device State:Connected");
                } else {
                    System.out.println("Device State:Error");
                }

            } catch (Exception ex) {

            }
        }

    }
}
