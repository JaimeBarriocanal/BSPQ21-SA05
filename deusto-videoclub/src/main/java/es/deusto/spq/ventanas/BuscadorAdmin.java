package es.deusto.spq.ventanas;

import java.awt.EventQueue;
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
import javax.swing.ListModel;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class BuscadorAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget pelisTarget = appTarget.path("peliculas");

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane = new JScrollPane();
	private JList<Pelicula> list;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorAdmin frame = new BuscadorAdmin();
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
	public BuscadorAdmin() {
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
		list.setBounds(51, 63, 679, 322);
		list = new JList<Pelicula>(model);
		
		
		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setBounds(53, 10, 51, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(113, 11, 224, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		final JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(420, 10, 85, 21);
		contentPane.add(btnNewButton);

		
		scrollPane.setBounds(51, 63, 679, 322);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(list);
		
		JButton btnNewButton2 = new JButton("Añadir");
		btnNewButton2.setBounds(185, 412, 85, 21);
		
		btnNewButton2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				CrearPelicula cp = new CrearPelicula();
				cp.setVisible(true);
				dispose();
			}
		});
		
		contentPane.add(btnNewButton2);
		
		final String peliculaSeleccionada = list.getSelectedValue().toString();
		
		JButton btnBorrar = new JButton("Eliminar");
		btnBorrar.setBounds(531, 412, 85, 21);
		
		btnBorrar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarPeliculaBd(model, peliculaSeleccionada);
			}
		});
		
		contentPane.add(btnBorrar);
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
	public void eliminarPeliculaBd(ListModel<Pelicula> listModel, String selectedFilm) {
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        System.out.println("Eliminando película de la BD");
        
        try {

            Query<Pelicula> q = pm.newQuery("SELECT FROM " + Pelicula.class.getName().toLowerCase() + " WHERE titulo== '" + selectedFilm + "'");
            List<Pelicula> listaPelicula = q.executeList();
            q.deletePersistentAll(listaPelicula);

            System.out.println("Eliminada película de la Base de Datos");

        } finally {
            pm.close();

            BuscadorAdmin ba = new BuscadorAdmin();
            ba.setVisible(true);
            dispose();
        }
    }
	
}
