package es.deusto.spq.grupoA05.deusto_videoclub;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaLogin extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public VentanaLogin() {
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(165, 89, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 138, 96, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(73, 92, 49, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 141, 49, 14);
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(107, 183, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(242, 183, 89, 23);
		getContentPane().add(btnRegistrarse);
	}
}
