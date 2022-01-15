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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import java.util.Set;
import java.util.TooManyListenersException;

/**
 *
 * @author JPV
 */
public class CommPortManager implements SerialPortDataListener {

    //private final CommPortCallback callback;
    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private SerialPort selectedPortIdentifier = null;
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
    
    public String undefinedPort = "--";
    String selectedPort = undefinedPort;
    String currentPort = undefinedPort;
    int baud_rate = 0;
    boolean disconnection_demand = false;

    public void set_com_parameters(String port, int baud_rate_0 ) {
        selectedPort = port;
        baud_rate = baud_rate_0;
    }
    
    public String get_current_port() {
        return currentPort;
    }

    public ArrayList<String> search_ports() {
        //Set<String> ports_list = new TreeSet<String>( String.CASE_INSENSITIVE_ORDER );
        ArrayList<String> ports_list = new ArrayList<String>();
        ports_list.add( undefinedPort );
        SerialPort[] port_array = SerialPort.getCommPorts();
        if ( port_array != null && port_array.length > 0 ) {
            for ( SerialPort port : port_array ) {
                ports_list.add( port.getSystemPortName() );
                System.out.println( port.getSystemPortName() );
            }
        }
        return (ArrayList<String>) ports_list;
    }
    
    public boolean connect() {
        serialPort = SerialPort.getCommPort( selectedPort );
        serialPort.setBaudRate( baud_rate );
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setNumDataBits(8);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, TIMEOUT, TIMEOUT );
        
        if ( serialPort.openPort() ) {
            System.out.println( "Opened successfully." );
            return true;
        }
        else {
            System.out.println( selectedPort + " is in use." );
        }
        return false;
    }
    
     public boolean initIOStream() {
        boolean successful = false;
        input = serialPort.getInputStream();
        output = serialPort.getOutputStream();
        currentPort = selectedPort;
        successful = true;
        return successful;
        /*try {
            //
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            currentPort = selectedPort;
            successful = true;
            return successful;
        }
        catch (IOException e) {
            System.out.println( "I/O Streams failed to open. (" + e.toString() );
            return successful;
        }*/
    }
     
    public void initListener() {
        serialPort.addDataListener(this);
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(com.fazecast.jSerialComm.SerialPortEvent evt) {
        if ( evt.getEventType() == SerialPortEvent.DATA_AVAILABLE ) {
            try
            {   
                while( evt.getEventType() == SerialPortEvent.DATA_AVAILABLE && !disconnection_demand ) {
                    byte singleData = (byte)input.read();
                    string_textarea += (char)singleData;

                    if (singleData == NEW_LINE_ASCII || singleData > 128) {
                        //callback.callback(string_textarea);
                        System.out.println( string_textarea );
                        string_textarea = "";
                    }
                }
            }
            catch (Exception e)
            {
                logText = "Failed to read data. (" + e.toString() + ")";
                System.out.println( logText );
            }
        }
        
    }

     





    
    public void disconnect() {
        disconnection_demand = true;
        try {
            //serialPort.removeEventListener();
            serialPort.closePort();
            input.close();
            output.close();
            currentPort = undefinedPort;
            System.out.println( "Disconnected" );
        }
        catch (Exception e) {
            System.out.println( "Failed to close " );
        }
    }

}
