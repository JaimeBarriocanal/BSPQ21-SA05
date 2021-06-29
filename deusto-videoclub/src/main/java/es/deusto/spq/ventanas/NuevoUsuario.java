package es.deusto.spq.ventanas;

import java.awt.EventQueue;
import es.deusto.spq.clases.Usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.JButton;
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
	private JTextField textField_1;
	private JTextField textField_2;


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
		lblTitulo.setBounds(225, 10, 315, 61);
		contentPane.add(lblTitulo);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(251, 115, 265, 27);
		contentPane.add(textField);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(251, 78, 155, 27);
		contentPane.add(lblNombre);

		JLabel label_2 = new JLabel("Contrase\u00F1a:");
		label_2.setBounds(251, 282, 85, 18);
		contentPane.add(label_2);

		final JButton btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.setBounds(291, 417, 176, 27);
		btnCrearNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUsuario(textField, textField_1, textField_2, passwordField, passwordField_1);
			}
		});
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
		lblRepetirContrasea.setBounds(251, 347, 127, 18);
		contentPane.add(lblRepetirContrasea);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(0, 0, 781, 444);
		contentPane.add(label_3);

		passwordField = new JPasswordField();
		passwordField.setBounds(251, 310, 265, 27);
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
		passwordField_1.setBounds(251, 375, 265, 27);
		contentPane.add(passwordField_1);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(251, 152, 155, 18);
		contentPane.add(lblEmail);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(251, 180, 265, 27);
		contentPane.add(textField_1);

		JLabel lblCdigoPostal = new JLabel("Código postal:");
		lblCdigoPostal.setBounds(251, 217, 155, 18);
		contentPane.add(lblCdigoPostal);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(251, 245, 265, 27);

		textField_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					textField_2.setEditable(true);
				} else {
					textField_2.setEditable(false);
				}
			}
		});

		contentPane.add(textField_2);
	}
	
	public void addUsuario(JTextField nombre, JTextField email, JTextField cp, JPasswordField contra, JPasswordField repcontra) {

		if (nombre.getText().equals("") || email.getText().equals("") || cp.getText().equals("")
				|| contra.getText().equals("") || repcontra.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Rellene todos los campos");
//		}else if(contra.getText().toString() != repcontra.getText().toString()) {
//			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
		}else {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			int codp = Integer.parseInt(cp.getText().toString());
			System.out.println("Añadiendo usuario en la BD");
			try {
				tx.begin();
				Usuario usuario = new Usuario(nombre.getText().toString(), contra.getText().toString(), email.getText().toString(), codp);
				pm.makePersistent(usuario);

				tx.commit();
				System.out.println("Añadido un nuevo usuario a la Base de Datos");

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();

			}
			Buscador b = new Buscador();
			b.setVisible(true);
			dispose();
		}
	}
	
}