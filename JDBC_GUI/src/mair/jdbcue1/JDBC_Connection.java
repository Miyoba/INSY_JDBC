package mair.jdbcue1;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import java.sql.Statement;
import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JDBC_Connection {

	private boolean connected;
	private MysqlDataSource ds;
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public JDBC_Connection(){
		connected = false;
		ds = new MysqlDataSource();
	}

	public void connectTo(String server, String user, String pass){
		try {
			if(connected == false){
				ds.setServerName(server);
				ds.setUser(user);
				ds.setPassword(pass);

				con = ds.getConnection();
				st = con.createStatement();
				connected = true;
			}	
			else{
				rs.close();
				st.close();
				con.close();

				ds.setServerName(server);
				ds.setUser(user);
				ds.setPassword(pass);
				con = ds.getConnection();
				st = con.createStatement();
			}
		}

		catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: "+sqle.getMessage());
		}
	}

	public ResultSet befehlAbfrage(String befehl){
		try{
			if(connected)
				return rs = st.executeQuery(befehl);

			else
				throw new IllegalArgumentException("Fehler: Nicht Vebrunden!");
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(null, "Error: "+sqle.getMessage());
			return null;
		}
		catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(null, iae.getMessage());
			return null;
		}
	}

	public void trennen(){
		try{
			if(connected == true){
				rs.close();
				st.close();
				con.close();
			}
			else
				throw new IllegalArgumentException("Fehler: Keine Aktive verbindung!");
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(null, sqle.getMessage());
		}
		catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(null, iae.getMessage());
		}
	}
}
