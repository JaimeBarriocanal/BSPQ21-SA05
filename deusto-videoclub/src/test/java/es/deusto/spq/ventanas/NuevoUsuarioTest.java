package es.deusto.spq.ventanas;

import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.clases.Usuario;

public class NuevoUsuarioTest {
	
	private JTextField txtUsername1;
	private JTextField txtEmail1;
	private JTextField txtCP1;
	private JTextField txtUsername2;
	private JTextField txtEmail2;
	private JTextField txtCP2;
	private JPasswordField txtPassword11;
	private JPasswordField txtPassword12;
	private JPasswordField txtPassword21;
	private JPasswordField txtPassword22;

	
	
	NuevoUsuario nu;

	
	@Before
	public void setup(){
		txtUsername1 = new JTextField();
		txtEmail1 = new JTextField();
		txtCP1 = new JTextField();
		txtPassword11 = new JPasswordField();
		txtPassword12 = new JPasswordField();
		
		txtUsername2 = new JTextField();
		txtEmail2 = new JTextField();
		txtCP2 = new JTextField();
		txtPassword21 = new JPasswordField();
		txtPassword22 = new JPasswordField();

		
	
		nu = new NuevoUsuario();
	}

	@Test
	public void testMain() {
		NuevoUsuario.main(new String[0]);
	}

	@Test
	public void testAddUsuario() {
		txtUsername1.setText("gorka");
		txtEmail1.setText("gorka@gmail.com");
		txtCP1.setText("12");
		txtPassword11.setText("pass");
		txtPassword12.setText("pass");
		
		txtUsername2.setText("");
		txtEmail2.setText("");
		txtCP2.setText("");
		txtPassword21.setText("");
		txtPassword22.setText("");
		
		nu.addUsuario(txtUsername1, txtEmail1, txtCP1, txtPassword11, txtPassword12);
		nu.addUsuario(txtUsername1, txtEmail1, txtCP1, txtPassword11, txtPassword12);
		
	}

}
