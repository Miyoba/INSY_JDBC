package mair.jdbcue1;

import javax.swing.JFrame;

import org.apache.commons.cli2.OptionException;

/**
 * Eine Startklasse fuer ein Programm das eine GUI fuer eine Datenbank angibt
 * 
 * @author Wolfgang Mair
 * @version 2013-11-20
 */
public class StartJDBC{
	public static void main(String[] args) throws OptionException{

		CLI cl = new CLI(args);
		
		//erstellen des Fensters
		JDBC_GUI gui = new JDBC_GUI(cl.getServer(),cl.getUser(),cl.getPass(),cl.getDatenbank());
		gui.setSize(300,400);
		gui.setLocation(300,100);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
	}
}
