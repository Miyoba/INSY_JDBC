package mair.jdbcue1;

import org.apache.commons.cli2.*;
import org.apache.commons.cli2.builder.*;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.commandline.Parser;

public class CLI {

	private String server = "", user = "", pass = "", datenbank = "", temp;

	public CLI(String[] args) throws OptionException {

		DefaultOptionBuilder dob = new DefaultOptionBuilder();
		ArgumentBuilder ab = new ArgumentBuilder();
		GroupBuilder gb = new GroupBuilder();

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
		
		Group options = gb.withName("options").withOption(server_option).withOption(user_option).
				withOption(pass_option).withOption(datenbank_option).create();

		Parser parser = new Parser();
		parser.setGroup(options);
		
		CommandLine cl = parser.parse(args);
		
		temp = (String)cl.getValue(server_option);
		server = temp;
		
		temp = (String)cl.getValue(user_option);
		user = temp;
		
		temp = (String)cl.getValue(pass_option);
		pass = temp;
		
		temp = (String)cl.getValue(datenbank_option);
		datenbank = temp;
	}
	
	
	
	public String getServer() {
		return server;
	}
	
	
	public String getUser() {
		return user;
	}
	
	
	public String getPass() {
		return pass;
	}
	
	
	public String getDatenbank() {
		return datenbank;
	}
}
