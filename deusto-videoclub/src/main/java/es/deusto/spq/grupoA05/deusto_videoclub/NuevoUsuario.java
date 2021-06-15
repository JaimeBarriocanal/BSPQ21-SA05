package es.deusto.spq.grupoA05.deusto_videoclub;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class NuevoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public final JTextField textField;
	public final JPasswordField passwordField;
	public final JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoUsuario frame = new NuevoUsuario();
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
	public NuevoUsuario() {
		this.setTitle("Nuevo usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblTitulo = new JLabel("Nuevo usuario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblTitulo.setBounds(225, 29, 315, 61);
		contentPane.add(lblTitulo);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(251, 157, 265, 45);
		contentPane.add(textField);

		JLabel label_1 = new JLabel("Usuario:");
		label_1.setBounds(248, 118, 155, 27);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Contrase\u00F1a:");
		label_2.setBounds(251, 215, 85, 27);
		contentPane.add(label_2);

		JButton btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.setBounds(291, 401, 176, 27);
		/*btnCrearNuevoUsuario.addActionListener(new ActionListener() {			-Configurar BD y logger
			public void actionPerformed(ActionEvent e) {
				
				GestorBD g = new GestorBD();
				Usuario user = new Usuario();
				String usuario = new String(textField.getText());
				String pass = new String(passwordField.getPassword());
				String passCon = new String(passwordField_1.getPassword());

				if (usuario.equals("") || pass.equals("") || passCon.equals("")) {

					JOptionPane.showMessageDialog(null, "Rellene todos los campos");

				} else {

					if (pass.equals(passCon)) {

						user.setUsuario(textField.getText());
						user.setPassword(pass);

						try {
							g.conectar();
							g.guardar(user);
							g.desconectar();

							JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
							LoggerDeusto.log( Level.INFO, "Usuario registrado", null );
							
							new Login().setVisible(true);
							NuevoUsuario.this.setVisible(false);

						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error en el registro" + e1);
							LoggerDeusto.log( Level.INFO, "Error al registrar usuario", e1 );
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error en el registro" + e1);
							LoggerDeusto.log( Level.INFO, "Error al registrar usuario", e1 );
						}

					} else {

						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
				}

			}
		});*/
		contentPane.add(btnCrearNuevoUsuario);

		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setBounds(12, 406, 97, 25);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				NuevoUsuario.this.setVisible(false);
			}
		});

		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(251, 308, 127, 27);
		contentPane.add(lblRepetirContrasea);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(0, 0, 781, 444);
		contentPane.add(label_3);

		passwordField = new JPasswordField();
		passwordField.setBounds(251, 255, 265, 45);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnCrearNuevoUsuario.doClick();
				}
			}
		});
		passwordField_1.setBounds(251, 348, 265, 45);
		contentPane.add(passwordField_1);
		
		/*Imagen de fondo
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon iid = new ImageIcon("");
        Image cuerpo = iid.getImage();
        lblNewLabel_1.setIcon(new ImageIcon(cuerpo));
		lblNewLabel_1.setBounds(0, 0, 793, 456);
		contentPane.add(lblNewLabel_1);*/
	}
}