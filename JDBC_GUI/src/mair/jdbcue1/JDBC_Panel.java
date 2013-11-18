package mair.jdbcue1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JDBC_Panel extends JPanel {
	
	private JTextField serverT = new JTextField(15);
	private JTextField datenbankT = new JTextField(15);
	private JTextField userT = new JTextField(15);
	private JPasswordField passT = new JPasswordField(10);
	private JTextField befehlT = new JTextField(15);
	private JButton verbindenB = new JButton("Verbinden"), befehlB = new JButton("Go!");
	
	
	
	private JLabel serverL = new JLabel();
	private JLabel datenbankL = new JLabel();
	private JLabel userL = new JLabel();
	private JLabel passL = new JLabel();
	private JLabel befehlL = new JLabel();
	
	public JDBC_Panel(String server, String user, String pass, String datenbank){
	
		JPanel panel, buttonsP;
		panel = new JPanel(new GridLayout(10,1));
		buttonsP  = new JPanel(new FlowLayout());
		
		serverL.setText("Server:");
		datenbankL.setText("Datenbank:");
		userL.setText("User:");
		passL.setText("Passwort:");
		befehlL.setText("SQL-Befehl:");
		
		serverT.setText(server);
		datenbankT.setText(datenbank);
		userT.setText(user);
		passT.setText(pass);
		
		ActionHandler al = new ActionHandler();
		
		verbindenB.addActionListener(al);
		befehlB.addActionListener(al);
		
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
		
		buttonsP.add(verbindenB);
		buttonsP.add(befehlB);
		
		
		
		this.add(panel);
		this.add(buttonsP, BorderLayout.LINE_END);
		
	}
	
	
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getSource() == befehlB){
				
			}
			if(a.getSource() == verbindenB){
				
			}
		}
	}
	
	
	
	
	public String getServerT() {
		return serverT.getText();
	}

	public void setServerT(String serverT) {
		this.serverT.setText(serverT);
	}

	public String getDatenbankT() {
		return datenbankT.getText();
	}

	public void setDatenbankT(String datenbankT) {
		this.datenbankT.setText(datenbankT);
	}

	public String getUserT() {
		return userT.getText();
	}

	public void setUserT(String userT) {
		this.userT.setText(userT);
	}

	public char[] getPassT() {
		return passT.getPassword();
	}

	public void setPassT(String passT) {
		this.passT.setText(passT);
	}

	public String getBefehlT() {
		return befehlT.getText();
	}

	public void setBefehlT(String befehlT) {
		this.befehlT.setText(befehlT);
	}
	
	
}
