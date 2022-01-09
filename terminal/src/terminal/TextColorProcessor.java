/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.Color;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author JPV
 */


public class TextColorProcessor {

    static Color ok = new Color(40,255,40);
    static Color error = new Color(255,80,80);
    static Color warning = new Color(255,200,40);

    static String[] words = { "OK", "ERROR", "CONNECTING", "CONNECTED", "CLOSED" }; 
    static Color[] colors = { ok, error, warning, ok, error };
            
    public int get_match_word_index (String string ) {
        for ( int i = 0; i < words.length; i++ ) {
            if ( string.contains( words[i] ) ) {
                return i;
            }
        }
        return -1;
    }

    public String get_init_substring ( String string, int index ) {
        int end_substring = string.indexOf(words[index]);
        return string.substring(0, end_substring);
    }

    public String get_match_substring ( String string, int index ) {
        int init_substring = string.indexOf(words[index]);
        int end_substring = string.indexOf(words[index]) + words[index].length();
        return string.substring( init_substring, end_substring );
    }

    public String get_end_substring ( String string, int index ) {
        int init_substring = string.indexOf(words[index]) + words[index].length();
        return string.substring( init_substring, string.length() );
    }

    public Style style_match_substring ( int index ) {
        StyleContext context = new StyleContext();
        Style style = context.addStyle( "", null );
        StyleConstants.setForeground( style, colors[index] );
        return style;
    }

}
