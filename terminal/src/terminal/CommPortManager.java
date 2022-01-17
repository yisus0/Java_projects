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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPV
 */
public class CommPortManager implements SerialPortDataListener {

    private final CommPortCallback callback;
    //private SerialPort selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    //private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String string_textarea = "";
    
    public String undefinedPort = "--";
    String selectedPort = undefinedPort;
    String currentPort = undefinedPort;
    int baud_rate = 0;

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
            }
        }
        return (ArrayList<String>) ports_list;
    }
    
    public boolean connect() {
        serialPort = SerialPort.getCommPort( selectedPort );
        
        if ( serialPort.openPort() ) {
            System.out.println( "Opened successfully." );
            return true;
        }
        else {
            System.out.println( selectedPort + " is in use." );
        }
        return false;
    }
    
     public void initIOStream() {
        input = serialPort.getInputStream();
        output = serialPort.getOutputStream();
        currentPort = selectedPort;
    }
     
    public void initListener() {
        serialPort.addDataListener(this);
        serialPort.setBaudRate( baud_rate );
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setNumDataBits(8);
        serialPort.setRTS();
        serialPort.setComPortTimeouts( SerialPort.TIMEOUT_READ_BLOCKING, TIMEOUT, 0 );
    }

    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    public void serialEvent(com.fazecast.jSerialComm.SerialPortEvent evt) {
        if ( evt.getEventType() == SerialPortEvent.DATA_AVAILABLE ) {
            int end = serialPort.bytesAvailable();
            try {
                for ( int i = 0; i < end; i++ ) {
                    byte singleData = (byte)input.read();
                    string_textarea += (char)singleData;
                }
                callback.callback(string_textarea);
                string_textarea = "";    
            }
            catch (Exception e) {
                System.out.println( "Failed to read data" );
            }
        }
    }
    
    public void writeData( String data ) {
        byte[] data_array = data.getBytes();
        try {
            output.write( data_array );
            output.flush();
        }
        catch (Exception e) {
            System.out.println( "Failed to write data." );
        }
    }

    public void disconnect() {
        try {
            serialPort.removeDataListener();
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
    
    public CommPortManager( CommPortCallback callback ) {
        this.callback = callback;
    }

}
