/** \file 
 * Breve descripción de es.deusto.spq.ventanas.Login.java. July 1, 2021
 */
package es.deusto.spq.ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import es.deusto.spq.bd.UsuariosBD;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.jdo.annotations.PersistenceCapable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 
 * Clase base de login
 *
 */
@PersistenceCapable
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget usersTarget = appTarget.path("usuarios");

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		this.setTitle("Inicio de sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblTitulo = new JLabel("Inicio de sesión");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblTitulo.setBounds(228, 30, 307, 61);
		contentPane.add(lblTitulo);

		textField = new JTextField();
		textField.setBounds(251, 157, 265, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(248, 118, 155, 27);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(251, 233, 85, 27);
		contentPane.add(lblContrasea);

		JButton btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.setBounds(308, 390, 148, 27);
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NuevoUsuario().setVisible(true);
				Login.this.setVisible(false);

			}
		});
		contentPane.add(btnNuevoUsuario);

		final JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(281, 332, 200, 37);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceder(textField, passwordField);
			}
		});
		contentPane.add(btnEntrar);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.doClick();
				}
			}
		});
		passwordField.setBounds(251, 273, 265, 45);
		contentPane.add(passwordField);

	}
	/**
	 * 
	 * Metodo Para acceder
	 *
	 */
	public void acceder(JTextField nombre, JPasswordField contra) {
		if (nombre.getText().equals("") || contra.getText().equals("")) {
			//JOptionPane.showMessageDialog(null, "Debe rellenar todos los datos");
		}
		if (nombre.getText().equals("admin")) {
			BuscadorAdmin ba = new BuscadorAdmin();
			ba.setVisible(true);
			dispose();
		} else {
			UsuariosBD ubd = new UsuariosBD();
			ubd.comprobarUsuarios(nombre.getText(), contra.getText().toString());
			Buscador b = new Buscador();
			b.setVisible(true);
			dispose();
		}
	}
}
