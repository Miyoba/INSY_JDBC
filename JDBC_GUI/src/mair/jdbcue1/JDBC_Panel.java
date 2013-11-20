package mair.jdbcue1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 * Eine Klasse die Buttons und Textfelder zur verfuegung stellen
 * 
 * @author Wolfgang Mair
 * @version 2013-11-20
 */
public class JDBC_Panel extends JPanel {

	//Deklarieren der Textfelder und Buttons etc.
	private JTextField serverT = new JTextField(15);
	private JTextField datenbankT = new JTextField(15);
	private JTextField userT = new JTextField(15);
	private JPasswordField passT = new JPasswordField(10);
	private JTextField befehlT = new JTextField(25);
	private JButton verbindenB = new JButton("Verbinden"), befehlB = new JButton("Go!"); 
	private JButton	trennenB = new JButton("Trennen");
	private JTable table = new JTable();
	private JScrollPane scroll;

	private JDBC_Connection con = new JDBC_Connection(this);

	private JLabel serverL = new JLabel();
	private JLabel datenbankL = new JLabel();
	private JLabel userL = new JLabel();
	private JLabel passL = new JLabel();
	private JLabel befehlL = new JLabel();
	private JLabel erfolgL = new JLabel();

	public JDBC_Panel(String server, String user, String pass, String datenbank){

		//setzen der Layouts
		this.setLayout(new BorderLayout());

		JPanel panel, buttonsP;
		panel = new JPanel(new GridLayout(11,1));
		buttonsP  = new JPanel(new FlowLayout());

		//Setzen der Texte
		serverL.setText("Server:");
		datenbankL.setText("Datenbank:");
		userL.setText("User:");
		passL.setText("Passwort:");
		befehlL.setText("SQL-Befehl:");
		erfolgL.setText("Erfolgreich verbunden!");
		erfolgL.setVisible(false);

		serverT.setText(server);
		datenbankT.setText(datenbank);
		userT.setText(user);
		passT.setText(pass);

		//Einstellungen fuer die Tabelle
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);

		//ermoeglicht das scrollen der Tabelle
		scroll = new JScrollPane(table);

		befehlB.setEnabled(false);

		//hinzufuegen und einbinden der Actionlistener
		ActionHandler al = new ActionHandler();

		verbindenB.addActionListener(al);
		befehlB.addActionListener(al);
		trennenB.addActionListener(al);

		//hinzufuegen in die Panels
		panel.add(serverL);
		panel.add(serverT);
		panel.add(datenbankL);
		panel.add(datenbankT);
		panel.add(userL);
		panel.add(userT);
		panel.add(passL);
		panel.add(passT);
		panel.add(befehlL);
		panel.add(befehlT);
		panel.add(erfolgL);

		buttonsP.add(verbindenB);
		buttonsP.add(befehlB);
		buttonsP.add(trennenB);


		this.add(panel, BorderLayout.NORTH);
		this.add(buttonsP, BorderLayout.SOUTH);
		this.add(scroll, BorderLayout.CENTER);

	}


	/**
	 * Eine Innere Klasse die die Funktionen der Buttons bestimmt
	 * 
	 * @author Wolfgang Mair
	 * @version 2013-11-20
	 */
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a) {
			//Wenn der SQL Befehl abgefragt werden soll
			if(a.getSource() == befehlB){

				String anfang;

				if(befehlT.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Leerer Befehl!");
				}
				else{
					if(befehlT.getText().indexOf(";") != (befehlT.getText().length()-1)){
						JOptionPane.showMessageDialog(null, "Zu Viele \";\"\nNur 1 ; ist erlaubt pro Befehl!");
					}
					else{
						anfang = befehlT.getText().toLowerCase();
						if(! anfang.startsWith("select") &&! anfang.startsWith("desc") &&! 
								anfang.startsWith("describe")&& !anfang.startsWith("show")){
							JOptionPane.showMessageDialog(null, "Nur SELECT, DESCRIBE oder SHOW Befehle!");
						}
						else{

							try {
								con.generateModel(con.befehlAbfrage(befehlT.getText()));
							} catch (SQLException e) {
								e.printStackTrace();

							}
						}
					}
				}

			}

			//Wenn es verbunden werden soll
			if(a.getSource() == verbindenB){
				con.connectTo(serverT.getText(), userT.getText(), String.valueOf(passT.getPassword()),datenbankT.getText());
				befehlB.setEnabled(true);
				trennenB.setEnabled(true);
				repaint();
			}

			//Wenn die Verbindung getrennt werden soll
			if(a.getSource() == trennenB){
				con.trennen();
				befehlB.setEnabled(false);
				trennenB.setEnabled(false);
				repaint();
			}
		}
	}



	/**
	 * Ein Setter der den Text vom Textfeld Server aendert
	 * 
	 * @param serverT Der zu speichernde Text
	 */
	public void setServerT(String serverT) {
		this.serverT.setText(serverT);
	}

	/**
	 * Ein Setter der den Text vom Textfeld Datenbank aendert
	 * 
	 * @param datenbankT Der zu speichernde Text
	 */
	public void setDatenbankT(String datenbankT) {
		this.datenbankT.setText(datenbankT);
	}

	/**
	 * Ein Setter der den Text vom Textfeld User aendert
	 * 
	 * @param userT Der zu speichernde Text
	 */
	public void setUserT(String userT) {
		this.userT.setText(userT);
	}

	/**
	 * Ein Setter der den Text vom Textfeld Passwort aendert
	 * 
	 * @param passT Der zu speichernde Text
	 */
	public void setPassT(String passT) {
		this.passT.setText(passT);
	}

	/**
	 * Macht ein Label Sichtbar oder nicht
	 * 
	 * @param stat Ob es sichtbar sein soll oder nicht
	 */
	public void setErfolgL(boolean stat) {
		this.erfolgL.setVisible(stat);
	}

	/**
	 * Ein Setter der das Model der Tabelle veraendert
	 * 
	 * @param tm Das neue TabelModel
	 */
	public void setModel(TableModel tm){
		table.setModel(tm);
	}

}
