package es.deusto.spq.ventanas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.clases.Pelicula;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JList;

public class Buscador extends JFrame {

	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget pelisTarget = appTarget.path("peliculas");

	private JPanel contentPane;
	private JTextField textField;
	private JList<Pelicula> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscador frame = new Buscador();
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
	public Buscador() {
		this.setTitle("Búsqueda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<Pelicula> model = new DefaultListModel<>();
		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {
		};
		List<Pelicula> pelis = pelisTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		model.clear();
		for (Pelicula pelicula : pelis) {
			model.addElement(pelicula);
		}
		list = new JList<Pelicula>(model);
		list.setBounds(51, 63, 679, 322);
		list.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(list);
		
		
		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setBounds(53, 10, 51, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(113, 11, 224, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		final JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(490, 10, 85, 21);
		contentPane.add(btnNewButton);

		
		this.setLocationRelativeTo(null);
		setResizable(false);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});

	}
}
