package es.deusto.spq.ventanas;

import static org.junit.Assert.*;

import javax.swing.JTextField;

import org.junit.Test;

public class CrearPeliculaTest {
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	
	@Before
	public void setup() {
		textField = new JTextField ("titulo");
		textField2 = new JTextField ("director");
		textField3 = new JTextField ("genero");
		textField4 = new JTextField ("sinopsis");
		textField5 = new JTextField ("disponible");
		textField6 = new JTextField ("6");
		textField7 = new JTextField ("120");
		
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPelicula() {
		fail("Not yet implemented");
	}

}
