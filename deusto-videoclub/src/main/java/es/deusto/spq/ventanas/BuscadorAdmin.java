/** \file 
 * Breve descripción de es.deusto.spq.ventanas.BuscadorAdmin.java. July 1, 2021
 */
package es.deusto.spq.ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import es.deusto.spq.clases.Pelicula;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextPane;

public class BuscadorAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget pelisTarget = appTarget.path("peliculas");

	private JPanel contentPane;
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
		this.setTitle("Editar películas");
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
		list.setBounds(51, 63, 333, 322);
		list.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(list);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(521, 82, 159, 20);
		contentPane.add(lblNewLabel_1);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(521, 112, 159, 20);
		contentPane.add(lblNewLabel_2);
		
		final JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(521, 142, 159, 20);
		contentPane.add(lblNewLabel_3);
		
		final JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(521, 258, 159, 20);
		contentPane.add(lblNewLabel_5);
		
		final JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(521, 291, 159, 20);
		contentPane.add(lblNewLabel_6);
		
		final JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(521, 321, 159, 20);
		contentPane.add(lblNewLabel_7);
		
		final JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setBounds(521, 172, 237, 85);
		textPane.setBackground(getBackground());
		contentPane.add(textPane);
		
		list.addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cargarDatos(lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, textPane,
						lblNewLabel_5, lblNewLabel_6, lblNewLabel_7, list, list.getSelectedIndex());		//añadir jlabel
			}
		});
		
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(420, 80, 51, 20);
		contentPane.add(lblTitulo);		
		
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
		
		JButton btnBorrar = new JButton("Eliminar");
		btnBorrar.setBounds(531, 412, 85, 21);
		
		btnBorrar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarPeliculaBd(model, list.getSelectedValue().getTitulo());
			}
		});
		
		contentPane.add(btnBorrar);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setBounds(420, 112, 51, 20);
		contentPane.add(lblDirector);
		
		JLabel lblGnero = new JLabel("Género:");
		lblGnero.setBounds(420, 142, 51, 20);
		contentPane.add(lblGnero);
		
		JLabel lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setBounds(420, 172, 66, 20);
		contentPane.add(lblSinopsis);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(420, 257, 51, 20);
		contentPane.add(lblEstado);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(420, 287, 51, 20);
		contentPane.add(lblPrecio);
		
		JLabel lblDuracin = new JLabel("Duración:");
		lblDuracin.setBounds(420, 317, 66, 20);
		contentPane.add(lblDuracin);
		
		
		
		

	}
	
	public void eliminarPeliculaBd(ListModel<Pelicula> listModel, String selectedFilm) {
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        System.out.println("Eliminando película de la BD");
        
        try {
            Query<Pelicula> q = pm.newQuery("SELECT FROM " + Pelicula.class.getName() + " WHERE titulo== '" + selectedFilm + "'");
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
	
	//añadir resto de jlabel
	public void cargarDatos(JLabel titulo, JLabel director, JLabel genero, JTextPane sinopsis, JLabel estado,
			JLabel precio, JLabel duracion, JList<Pelicula> listaPelis, int seleccionado) {
		titulo.setText(listaPelis.getModel().getElementAt(seleccionado).getTitulo());
		director.setText(listaPelis.getModel().getElementAt(seleccionado).getDirector());
		genero.setText(listaPelis.getModel().getElementAt(seleccionado).getGenero());
		sinopsis.setText(listaPelis.getModel().getElementAt(seleccionado).getSinopsis());
		estado.setText(listaPelis.getModel().getElementAt(seleccionado).getEstado());
		precio.setText(listaPelis.getModel().getElementAt(seleccionado).getPrecio()+"");
		duracion.setText(listaPelis.getModel().getElementAt(seleccionado).getDuracion()+"");
	}
}
