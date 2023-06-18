package live_clock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//add "requires java.desktop;" inside module-info.java to import javax.swing; library
import javax.swing.*;

/**In Java, the split() method takes a regular expression as its parameter. 
 * However, the period character (".") has a special meaning in regular expressions, 
 * representing any character. To split the string on a period character, 
 * you need to escape it with a backslash
**/

public class Main {
    static JLabel date_label;
    static JLabel time_label;
    
	public static void main(String[] args) {
		String filename="digital-7.ttf";//this is for testing normally we would store the font file in our app (knows as an embedded resource), see this for help on that http://stackoverflow.com/questions/13796331/jar-embedded-resources-nullpointerexception/13797070#13797070
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			font = font.deriveFont(Font.BOLD,45);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			font = new Font("Arial", Font.BOLD, 45);
		}
		
		//frame
	    JFrame frame;
	    JPanel panel;
	    
    	frame = new JFrame("LIVE CLOCK");
        panel = new JPanel();          
        panel.setLayout(null); 
        
        date_label = new JLabel(current_date());
        date_label.setBounds(100, 50, 250, 40);						//x, y, width, height
        date_label.setFont(font);
        date_label.setForeground(new Color(6, 214, 160));
        
        time_label = new JLabel(current_time());
        time_label.setBounds(135, 100, 250, 40);
        time_label.setFont(font);
        time_label.setForeground(new Color(6, 214, 160));
        
        panel.add(time_label);
        panel.add(date_label);
        panel.setBackground(new Color(0,0,0));

        frame.add(panel);
        frame.setSize(420, 250);                                   //width, height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

		while (true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
		        ex.printStackTrace();
		    }
			update_date_time();
		}
	}
	
	static void update_date_time() {
        time_label.setText(current_time());
        if (current_time().equals("00:00:00")) {
    		date_label.setText(current_date());
        }
	}
	
	static String current_date() {
		LocalDate date = LocalDate.now();
		String date_string = date.toString();
		String[] date_array = date_string.split("-");
		date_string = date_array[2] + "-" + date_array[1] + "-" + date_array[0];
		return date_string;
	}
	
	static String current_time() {
		LocalTime time = LocalTime.now();
		String time_string = time.toString();
		String[] time_array = time_string.split("\\.");
		return time_array[0];
	}
}