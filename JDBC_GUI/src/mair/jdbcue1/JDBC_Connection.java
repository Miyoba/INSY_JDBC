package mair.jdbcue1;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.sql.Statement;
import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Eine Klasse die eine Verbindung mit einer Datenbank ermoeglicht und SQL abfragen abarbeitet
 * 
 * @author Wolfgang Mair
 * @version 2013-11-20
 */
public class JDBC_Connection {

	//Attribute Deklarieren
	private boolean connected;
	private MysqlDataSource ds;
	private Connection con;
	private Statement st;
	private ResultSet rs = null;

	private JDBC_Panel pan;

	/**
	 * Ein Konstruktor um den Attributen einen Wert zu geben
	 * 
	 * @param p Das zu uebergebende Panel
	 */
	public JDBC_Connection(JDBC_Panel p){
		connected = false;
		ds = new MysqlDataSource();
		pan = p;
	}

	/**
	 * Eine Methode die es ermöglicht mit einer Datenbank zu verbinden
	 * 
	 * @param server Der Servername
	 * @param user Der Username
	 * @param pass Das Passwort
	 * @param datenbank Der Datenbankname
	 */
	public void connectTo(String server, String user, String pass, String datenbank){
		try {
			//wenn noch nicht verbunden dann erstellt es eine neue Verbindung
			if(connected == false){
				//setzen der relevanten Informationen
				ds.setServerName(server);
				ds.setUser(user);
				ds.setPassword(pass);
				ds.setDatabaseName(datenbank);

				//Verbindung aufbauen
				con = ds.getConnection();
				st = con.createStatement();

				connected = true;
				pan.setErfolgL(true);
			}
			//Wenn schon verbunden dann derzeitige Verbindung beenden und eine neue erstellen
			else{
				//schliessen der derzeitige verbindung
				rs.close();
				st.close();
				con.close();

				//setzen der relevanten Informationen
				ds.setServerName(server);
				ds.setUser(user);
				ds.setPassword(pass);
				ds.setDatabaseName(datenbank);

				//Verbindung aufbauen
				con = ds.getConnection();
				st = con.createStatement();

				pan.setErfolgL(true);
			}
		}

		catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Error: "+sqle.getMessage());
		}
	}


	/**
	 * generiert die neue Tabelle
	 * @param rs das ResultSet
	 * @return die neue Tabelle
	 * @throws SQLException
	 */
	public void generateModel(ResultSet rs) throws SQLException {
		if(rs == null){
			return;
		}
		else{
			//Deklarieren von Attributen
			DefaultTableModel newTable = new DefaultTableModel();
			int colCount;
			String[] colName;
			String[] row;

			//ResultSetMetaData Objekt erstellen um deren Methoden zu benutzen
			ResultSetMetaData md = rs.getMetaData();
			//Spalten anzahl
			colCount = md.getColumnCount();
			colName = new String[colCount];

			//befuellen des Namenarrays fuer spalten
			for (int i = 1; i <= colCount; i++) {
				colName[i-1] = md.getColumnName(i);
			}

			//Die namen der Tabellenmodel uebergeben
			newTable.setColumnIdentifiers(colName);

			//Den Inhalt der Tabellenmodel speichern
			while (rs.next()) {
				row = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					row[i - 1] = rs.getString(i);
				}
				//Den Inhalt der Tabellenmodel ubergeben
				newTable.addRow(row);
			}

			//Aufrufen der ModelMethode im Panel
			pan.setModel(newTable);
		}	
	}


	/**
	 * Eine Methode die eine SQL abfrage bearbeitet und ein passendes Resultset zurueckgibt
	 * 
	 * @param befehl Die SQL abfrage
	 * @return   Das dadurch entstandene Resultset
	 */
	public ResultSet befehlAbfrage(String befehl){
		//Abarbeiten des Befehles
		try{
			return rs = st.executeQuery(befehl);
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(null, "Error: "+sqle.getMessage());
			return null;
		}
	}


	/**
	 * Eine Methode die die derzeitige Verbindung trennt
	 */
	public void trennen(){
		try{
			if(rs != null)
				rs.close();
			st.close();
			con.close();
			connected = false;
			pan.setErfolgL(false);
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(null, sqle.getMessage());
		}
	}

}
