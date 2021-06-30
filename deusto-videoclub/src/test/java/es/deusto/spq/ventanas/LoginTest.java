package es.deusto.spq.ventanas;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;
import es.deusto.spq.ventanas.Login;


public class LoginTest {
	private JTextField txtUsername1;
	private JPasswordField txtPassword1;

	private JTextField txtUsername2;
	private JPasswordField txtPassword2;
	private JTextField txtUsername3;
	private JPasswordField txtPassword3;

	private Login l;
	private Login l2;
	private Login l3;

	/**
	 * Metodo para crear la ventana Login, tener Usuarios y contraseñas
	 *
	 */
	@Before
	public void setUp() {
		txtUsername1 = new JTextField();
		txtPassword1 = new JPasswordField();

		txtUsername2 = new JTextField();
		txtPassword2 = new JPasswordField();

		txtUsername3 = new JTextField();
		txtPassword3 = new JPasswordField();

		l = new Login();
	}

	@Test
	public void testMain() {
		Login.main(new String[0]);
	}

	@Test
	public void testAcceder() {
		txtUsername1.setText("user0");
		txtPassword1.setText("1234");

		txtUsername2.setText("admin");
		txtPassword2.setText("admin");

		txtUsername3.setText("");
		txtPassword3.setText("");

		l.acceder(txtUsername1, txtPassword1);
		l.acceder(txtUsername2, txtPassword2);
		l.acceder(txtUsername3, txtPassword3);
	}

}
