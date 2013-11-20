package mair.jdbcue1;

import org.apache.commons.cli2.*;
import org.apache.commons.cli2.builder.*;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.commandline.Parser;

/**
 * Eine Klasse die das öffnen des Programmes mittels Anfangsparameter ermoeglicht
 * 
 * @author Wolfgang Mair
 * @version 2013-11-20
 */
public class CLI {
	//Vorbereiten der Attribute
	private String server = "", user = "", pass = "", datenbank = "";

	/**
	 * Ein Konstruktor der Mittels eines String arrays Das Programm anfangswerte übermittlet
	 * 
	 * @param args Das String array mit den Informationen
	 * @throws OptionException Eine Exception die bei Optionen auftreten kann
	 */
	public CLI(String[] args) throws OptionException {

		//Die Builder
		DefaultOptionBuilder dob = new DefaultOptionBuilder();
		ArgumentBuilder ab = new ArgumentBuilder();
		GroupBuilder gb = new GroupBuilder();

		//erstellen der Defaultoptions
		DefaultOption server_option = dob.withLongName("host").withShortName("h").withRequired(false).
				withDescription("Der Hostname")
				.withArgument(ab.withName("host").withMinimum(0).withMaximum(1).create()).create();
		
		DefaultOption user_option = dob.withLongName("user").withShortName("u").withRequired(false).
				withDescription("Der Username")
				.withArgument(ab.withName("user").withMinimum(0).withMaximum(1).create()).create();
		
		DefaultOption pass_option = dob.withLongName("password").withShortName("p").withRequired(false).
				withDescription("Das Passwort")
				.withArgument(ab.withName("password").withMinimum(0).withMaximum(1).create()).create();
		
		DefaultOption datenbank_option = dob.withLongName("dbname").withShortName("d").withRequired(false).
				withDescription("Die Datenbank")
				.withArgument(ab.withName("dbname").withMinimum(0).withMaximum(1).create()).create();
		
		//Groupen der Options
		Group options = gb.withName("options").withOption(server_option).withOption(user_option).
				withOption(pass_option).withOption(datenbank_option).create();

		//erstellen des Parsers und setzen der Gruppe
		Parser parser = new Parser();
		parser.setGroup(options);
		
		CommandLine cl = parser.parse(args);
		
		//Befuellen der Variablen
		server = (String)cl.getValue(server_option);
		
		user = (String)cl.getValue(user_option);
		
		pass = (String)cl.getValue(pass_option);
		
		datenbank = (String)cl.getValue(datenbank_option);
	}
	
	
	/**
	 * Eine Getter Methode die den server Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Server
	 */
	public String getServer() {
		return server;
	}
	
	/**
	 * Eine Getter Methode die den User Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei User
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Eine Getter Methode die den Passwort Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Passwort
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * Eine Getter Methode die den Datenbank Text zurueckgibt
	 * 
	 * @return    Der eingegebene Text bei Datenbank
	 */
	public String getDatenbank() {
		return datenbank;
	}
}
