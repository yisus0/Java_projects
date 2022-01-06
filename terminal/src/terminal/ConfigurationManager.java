/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;
import java.io.FileNotFoundException;  // Import this class to handle errors

/**
 *
 * @author JPV
 */
public class ConfigurationManager {

    String file_name = "Jterminal.config";
    String user_location = "Desktop";

    private FileWriter get_writer_file() {
        FileWriter file = null;
        String user_directory = System.getProperty("user.home");
        String absolute_path = user_directory + File.separator + user_location + File.separator + file_name;
        try {
            file = new FileWriter( absolute_path );
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return file;
    }
    
    public void save_config( char autoscroll, char show_time_enabled, char dark_theme, String input_ending, String baud_rate) {
        FileWriter file = get_writer_file();
        String data = autoscroll + "," + show_time_enabled + "," + dark_theme + "," + input_ending + "," + baud_rate;

        if ( file == null ) {
            return;
        }
        try {
            file.write( data );
            file.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    private File get_reader_file() {
        File file = null;
        String user_directory = System.getProperty("user.home");
        String absolute_path = user_directory + File.separator + user_location + File.separator + file_name;
        file = new File( absolute_path );
        return file;
    }
    
    public String load_config() {
        File file = get_reader_file();
        String result = "";

        if ( file == null ) {
            return result;
        }
        try {
            Scanner data_from_file = new Scanner(file);
            result = data_from_file.nextLine();
            data_from_file.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return result;
    }
}
