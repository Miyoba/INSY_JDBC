package mair.jdbcue1;

import javax.swing.*;

/**
 * Die Graphic User Interface 
 * 
 * @author Wolfgang Mair
 * @version 2013-11-20
 */
public class JDBC_GUI extends JFrame{
	//Attribute
	private JDBC_Panel panel;
	
	/**
	 * Ein Konstruktor der ein Panel aufruft mittels parametern
	 * 
	 * @param server Der Server
	 * @param user Der User
	 * @param pass Das Passwort
	 * @param datenbank Die Datenbank
	 */
	public JDBC_GUI(String server, String user, String pass, String datenbank){
		//gibt dem Fenster einen Namen
		super("JDBC Oh yeah!");
		//aufrufen des Panels
		panel = new JDBC_Panel(server,user,pass,datenbank);
		this.add(panel);
	}
}
