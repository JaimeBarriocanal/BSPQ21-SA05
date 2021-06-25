package es.deusto.spq.grupoA05.deusto_videoclub;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class GestorVideoclubBDTest{

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	Usuario usr1, usr2, usr3, usrError, usrInitSesion, admin;
	Pelicula peli1, peli2, peliError;

	@Before
	public void setUp() {
		Prop.iniciarProp();
		GestorVideoclubBD.iniciarGestor();
		admin = new Usuario("admin", "admin", "admin");
		usr1 = new Usuario("utest", "test", "utest@deusto.es");
		usrInitSesion = new Usuario("initses", "initses", "initses@deusto.es");
		GestorVideoclubBD.registrarUsuario(usrInitSesion);
		usr2 = new Usuario("utest2", "noregistrado", "noregistrado@deusto.es");
		usrError = new Usuario("newusername", "';", "newemail");
		usr3 = new Usuario("utest", "noregistrado", "noregistrado@deusto.es");
		peli1 = new Pelicula("Titulo", "director", "aventuras", "sinposis", "VENTA", 10, 120);
		peli2 = new Pelicula("Titulo", "director", "aventuras", "sinposis", "VENTA", 10, 120);
		peliError = new Pelicula("NewTitulo", "';", "", "", "", 0, 0);
	
	}

	@Test
	public void testRegistrarUsuario() {
		//Test
		assertEquals(0, GestorVideoclubBD.registrarUsuario(usrError));
		assertEquals(1, GestorVideoclubBD.registrarUsuario(usr1)); // Funciona
		assertEquals(2, GestorVideoclubBD.registrarUsuario(usr1)); // Error nombre email
		assertEquals(3, GestorVideoclubBD.registrarUsuario(usr3)); // Debe dar fallo de nombre 
		//Limpiar BD
		GestorVideoclubBD.borrarUsuario(usr1);
		GestorVideoclubBD.borrarUsuario(usrError);
	}

	@Test
	public void testGetNewID() {
		//Cargar usuario
		GestorVideoclubBD.registrarUsuario(usr1);
		//Test
		assertNotEquals(0, GestorVideoclubBD.getNewID("usuario")); //Libro guardado
		assertEquals(0, GestorVideoclubBD.getNewID(";"));
		//Limpiar BD
		GestorVideoclubBD.borrarUsuario(usr1);
	}

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 200, average = 100)
	public void testInicioSesion() {
		//Test
		assertTrue(GestorVideoclubBD.inicioSesion(usrInitSesion));
		assertFalse(GestorVideoclubBD.inicioSesion(usr2));
		assertFalse(GestorVideoclubBD.inicioSesion(null));
	}

	@Test
	public void testAdmin() {
		//Cargar usuarios
		GestorVideoclubBD.registrarUsuario(admin);
		GestorVideoclubBD.registrarUsuario(usr1);
		//Test
		assertTrue(GestorVideoclubBD.admin(admin));
		assertFalse(GestorVideoclubBD.admin(usr1));
		assertFalse(GestorVideoclubBD.admin(null));
		//Limpiar BD
		GestorVideoclubBD.borrarUsuario(usr1);
	}

	@Test
	public void testGuardarPelicula() {
		//Test
		assertEquals(0, GestorVideoclubBD.guardarPelicula(null));
		assertEquals(0, GestorVideoclubBD.guardarPelicula(librError));
		assertEquals(1, GestorVideoclubBD.guardarPelicula(peli1)); //Libro guardado
		assertEquals(2, GestorVideoclubBD.guardarPelicula(peli1)); //Debe dar fallo de titulo
		//Limpiar BD
		GestorVideoclubBD.borrarPelicula(peliError);
		GestorVideoclubBD.borrarPelicula(peli1);
	}

	@Test
	public void testObtenerPeliculas() {
		// Guardar un libro de prueba por lo menos
		GestorVideoclubBD.guardarPelicula(peli1);

		assertNotEquals(0, GestorVideoclubBD.obtenerPeliculas().size()); //Array no vacÃ­o

		// Borrar libro
		GestorVideoclubBD.borrarPelicula(peli1);
	}

	@Test
	public void testSearchPeliculas() {
		// Cargar libros
		GestorVideoclubBD.guardarPelicula(peli1);
		GestorVideoclubBD.guardarPelicula(peli2);
		//Test
		// Buscar sin filtro
		GestorVideoclubBD.searchPeliculas("", "Todos");
		assertNotEquals("", GestorVideoclubBD.searchPeliculas(lbr1.getTitulo().toString(), "Todos"));
		assertNotEquals("", GestorVideoclubBD.searchPeliculas(lbr1.getDirector().toString(), "Todos"));

		// Buscar con filtro
		assertNotEquals("", GestorVideoclubBD.searchPeliculas(peli1.getTitulo().toString(), peli1.getGenero()));
		//limpiar BD
		GestorVideoclubBD.borrarPelicula(peli1);
		GestorVideoclubBD.borrarPelicula(peli2);
	}

	@Test
	public void testBorrarUsuario() {
		//Cargar usuarios
		GestorVideoclubBD.registrarUsuario(usr1);
		GestorVideoclubBD.registrarUsuario(usrError);
		//Test
		assertTrue(GestorVideoclubBD.borrarUsuario(usr1));
		assertTrue(GestorVideoclubBD.borrarUsuario(usrError));//Usuario borrado
	}

	@Test
	public void testBorrarPelicula() {
		//Cargar libro
		GestorVideoclubBD.guardarPelicula(peli1);
		//Test
		assertTrue(GestorVideoclubBD.borrarPelicula(peli1)); //Libro borrado
	}

	@After
	public void tearDown() throws Exception {
		GestorVideoclubBD.borrarUsuario(usrInitSesion);
		GestorVideoclubBD.finalizar();
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(GestorVideoclubBDTest.class.getName());
		//$JUnit-BEGIN$

		//$JUnit-END$
		return suite;
	}
}