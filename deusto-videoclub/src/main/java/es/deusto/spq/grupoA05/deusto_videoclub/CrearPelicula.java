package es.deusto.spq.grupoA05.deusto_videoclub;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;

public class CrearPelicula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPelicula frame = new CrearPelicula();
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
	public CrearPelicula() {
		this.setTitle("Añadir película");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setBounds(53, 10, 51, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(113, 11, 224, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.setBounds(169, 355, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel2 = new JLabel("Director:");
		lblNewLabel2.setBounds(53, 46, 51, 20);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Género:");
		lblNewLabel3.setBounds(53, 87, 51, 20);
		contentPane.add(lblNewLabel3);
		
		JLabel lblNewLabel4 = new JLabel("Sinopsis:");
		lblNewLabel4.setBounds(53, 131, 62, 20);
		contentPane.add(lblNewLabel4);
		
		JLabel lblNewLabel5 = new JLabel("Estado:");
		lblNewLabel5.setBounds(53, 181, 51, 20);
		contentPane.add(lblNewLabel5);
		
		JLabel lblNewLabel6 = new JLabel("Precio:");
		lblNewLabel6.setBounds(53, 232, 51, 20);
		contentPane.add(lblNewLabel6);
		
		JLabel lblNewLabel7 = new JLabel("Duración:");
		lblNewLabel7.setBounds(53, 280, 62, 20);
		contentPane.add(lblNewLabel7);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(113, 47, 224, 19);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(113, 88, 224, 19);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(113, 132, 224, 19);
		contentPane.add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(113, 182, 224, 19);
		contentPane.add(textField5);
		
		textField6 = new JTextField();
		textField6.setColumns(10);
		textField6.setBounds(113, 233, 224, 19);
		contentPane.add(textField6);
		
		textField7 = new JTextField();
		textField7.setColumns(10);
		textField7.setBounds(113, 281, 224, 19);
		contentPane.add(textField7);
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
