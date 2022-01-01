/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import gnu.io.*;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;


/**
 *
 * @author JPV
 */
public class CommPortManager implements SerialPortEventListener {
    
    private final CommPortCallback callback;
    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String logText = "";
    String string_textarea = "";

    //search for all the serial ports
    //pre style="font-size: 11px;": none
    //post: adds all the found ports to a combo box on the GUI
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                //window.cboxPorts.addItem(curPort.getName());
                System.out.println(curPort.getName());
                portMap.put(curPort.getName(), curPort);
            }
        }
    }

     public void connect() {
        String selectedPort = "COM3";
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("TigerControlPanel", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;;

            //logging
            logText = selectedPort + " opened successfully.";
            System.out.println(logText + "n");

        }
        catch (PortInUseException e)
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";
            System.out.println(logText + "n");
        }
        catch (Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
            System.out.println(logText + "n");
        }
    }

    //open the input and output streams
    //pre style="font-size: 11px;": an open port
    //post: initialized input and output streams for use to communicate data
    public boolean initIOStream() {
        //return value for whether opening the streams is successful or not
        boolean successful = false;

        try {
            //
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            //writeData(0, 0);

            successful = true;
            return successful;
        }
        catch (IOException e) {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            System.out.println(logText + "n");
            return successful;
        }
    }

    //starts the event listener that knows whenever data is available to be read
    //pre style="font-size: 11px;": an open serial port
    //post: an event listener for the serial port that knows when data is received
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
            System.out.println(logText + "n");
        }
        try {
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
        }
        catch (UnsupportedCommOperationException e) {
        }
    }

    //what happens when data is received
    //pre style="font-size: 11px;": serial event is triggered
    //post: processing on the data it reads
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try
            {
                byte singleData = (byte)input.read();
                /*if (singleData != NEW_LINE_ASCII)
                {
                    logText = new String(new byte[] {singleData});
                    System.out.println(logText+"888");
                }
                else
                {
                    System.out.println("n");
                }*//*
                char data = (char)singleData;
                callback.callback(data);*/
                string_textarea += (char)singleData;
                if (singleData == NEW_LINE_ASCII) {
                    callback.callback(string_textarea);
                    string_textarea = "";
                }

            }
            catch (Exception e)
            {
                logText = "Failed to read data. (" + e.toString() + ")";
                System.out.println(logText + "n");
            }
        }
    }
    
    //method that can be called to send data
    //pre style="font-size: 11px;": open serial port
    //post: data sent to the other device
    public void writeData(int leftThrottle, int rightThrottle) {
        try
        {
            output.write(leftThrottle);
            output.flush();
            //this is a delimiter for the data
            output.write(DASH_ASCII);
            output.flush();

            output.write(rightThrottle);
            output.flush();
            //will be read as a byte so it is a space key
            output.write(SPACE_ASCII);
            output.flush();
        }
        catch (Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";
            System.out.println(logText + "n");
        }
    }

    //disconnect the serial port
    //pre style="font-size: 11px;": an open serial port
    //post: closed serial port
    public void disconnect() {
        //close the serial port
        try {
            //writeData(0, 0);

            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();

            logText = "Disconnected.";
            System.out.println(logText + "n");
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getName()
                              + "(" + e.toString() + ")";
            System.out.println(logText + "n");
        }
    }
    
    public CommPortManager(CommPortCallback callback) {

        this.callback = callback;
    }
}
