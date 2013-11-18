package mair.jdbcue1;

import javax.swing.*;

/**
 * Die Graphic User Interface 
 * 
 * @author Wolfgang Mair
 * @version 2012-09-19
 */
public class JDBC_GUI extends JFrame{


	private JDBC_Panel panel;
	
	public JDBC_GUI(String server, String user, String pass, String datenbank){
		//gibt dem Fenster einen Namen
		super("JDBC Oh yeah!");
		panel = new JDBC_Panel(server,user,pass,datenbank);
		this.add(panel);
	}
}
