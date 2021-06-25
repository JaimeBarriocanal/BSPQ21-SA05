package es.deusto.spq.grupoA05.deusto_videoclub;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private Client client;
	private WebTarget webTarget;

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final LoggerDeusto LOGGER = new LoggerDeusto(Login.class.getName(), 2);

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
		// Iniciar cliente
		client = ClientBuilder.newClient();
		Prop.iniciarProp();
		webTarget = client.target(String.format(Prop.prop.getProperty("server.url")));
		
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
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				String usuario = new String(textField.getText());
				String passwd = new String(passwordField.getPassword());

				if (usuario.isEmpty() && passwd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los datos");
				} else {
					try {
						WebTarget libreriaWebTarget = webTarget.path("/iniciosesion");
						System.out.println(libreriaWebTarget.getUri());
						Invocation.Builder invocationBuilder = libreriaWebTarget.request(MediaType.APPLICATION_JSON);

						user.setEmail(usuario);
						user.setPassword(passwd);
						Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

						if (response.getStatus() == Status.OK.getStatusCode()) {

							WebTarget admin = webTarget.path("/admin");
							System.out.println(admin.getUri());
							Invocation.Builder invocationBuilderAdmin = admin.request(MediaType.APPLICATION_JSON);
							Response responseAdmin = invocationBuilderAdmin
									.post(Entity.entity(user, MediaType.APPLICATION_JSON));
							if (responseAdmin.getStatus() == Status.OK.getStatusCode()) {
								// new MenuAdministrador(); Abrir ventana de admin
							} else {
								new Buscador();
							}
							Login.this.setVisible(false);
						} else {
							throw new Exception("Datos incorrectos");
						}
					} catch (javax.ws.rs.ProcessingException conExc) {
						String errorMessage = "No se ha podido establecer conexion con el servidor";
						JOptionPane.showMessageDialog(contentPane, errorMessage, "Error de conexion",
								JOptionPane.ERROR_MESSAGE);
						LOGGER.getLOGGER().log(Level.WARNING, errorMessage);
					} catch (Exception exc) {
						String errorMessage = "Se ha producido un error inesperado";
						JOptionPane.showMessageDialog(contentPane, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
						LOGGER.getLOGGER().log(Level.WARNING, exc.getMessage());
					}

				}
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
}
