package mair.jdbcue1;

import javax.swing.JFrame;

import org.apache.commons.cli2.OptionException;

public class StartJDBC{
	public static void main(String[] args) throws OptionException{

		CLI cl = new CLI(args);
		
		JDBC_GUI gui = new JDBC_GUI(cl.getServer(),cl.getUser(),cl.getPass(),cl.getDatenbank());
		gui.setSize(300,300);
		gui.setLocation(300,300);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
	}
}
