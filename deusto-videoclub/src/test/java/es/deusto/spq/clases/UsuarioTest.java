package es.deusto.spq.clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Usuario u;
	
	@Before
	public void setUp() {
		u = new Usuario("nombre", "contraseña", "email", 48000);
	}

	@Test
	public void testSetGetNombre() {
		u.setUsername("testNombre");
		assertEquals("testNombre", u.getUsername());
	}
	
	@Test
	public void testSetGetContra() {
		u.setPassword("testContraseña");
		assertEquals("testContraseña", u.getPassword());
	}
	
	@Test
	public void testSetGetEmail() {
		u.setEmail("testEmail");
		assertEquals("testEmail", u.getEmail());
	}
	
	@Test
	public void testSetCP() {
		u.setCP(48000);
		assertEquals(48000, u.getCP());
	}

}
