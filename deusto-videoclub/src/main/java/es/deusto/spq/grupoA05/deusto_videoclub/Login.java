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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
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

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(281, 332, 200, 37);
		/*btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestorBD g = new GestorBD();
				Usuario user = new Usuario();

				String usuario = new String(textField.getText());
				String passwd = new String(passwordField.getPassword());

				if (usuario.isEmpty() && passwd.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Debe rellenar todos los datos");

				} else {

					user.setUsuario(usuario);
					user.setPassword(passwd);
					
					try {
						g.conectar();
						
					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

					try {
						if(g.inciarSesion(user) == true) {
							
							g.inciarSesion(user);
							g.desconectar();
							
							LoggerDeusto.log(Level.INFO, "Sesión iniciada ", null);			-Logger
							
							new VentanaInicio().setVisible(true);
							Login.this.setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
						}
						

					} catch (SQLException e1) {

						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al iniciar sesión [" + e1 + "]");
					}

				}
			}
		});*/
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
		
		/*	Imagen de fondo
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon iid = new ImageIcon();
        Image cuerpo = iid.getImage();
        lblNewLabel_1.setIcon(new ImageIcon(cuerpo));
		lblNewLabel_1.setBounds(0, 0, 793, 456);
		contentPane.add(lblNewLabel_1);*/
	}
}
