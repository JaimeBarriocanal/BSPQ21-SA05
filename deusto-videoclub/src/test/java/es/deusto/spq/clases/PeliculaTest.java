package es.deusto.spq.clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {
//	TESTS DE INTEGRACION CON CODIGO DE BASE DE DATOS
	private Pelicula p;
	
	@Before
	public void setUp() {
		p = new Pelicula("titulo", "director", "genero", "sinopsis", "estado", 0, 0);
	}
	
	@Test
	public void testSetGetTitulo() {
		p.setTitulo("testTitulo");
		assertEquals("testTitulo", p.getTitulo());
	}
	
	@Test
	public void testSetGetDirector() {
		p.setDirector("testDirector");
		assertEquals("testDirector", p.getDirector());
	}
	
	@Test
	public void testSetGetGenero() {
		p.setGenero("testGenero");
		assertEquals("testGenero", p.getGenero());
	}
	
	@Test
	public void testSetGetSinopsis() {
		p.setSinopsis("testSinopsis");
		assertEquals("testSinopsis", p.getSinopsis());
	}
	
	@Test
	public void testSetGetEstado() {
		p.setEstado("testEstado");
		assertEquals("testEstado", p.getEstado());
	}
	
	@Test
	public void testSetGetPrecio() {
		p.setPrecio(0);
		assertEquals(0, p.getPrecio(), 0.1);
	}
	
	@Test
	public void testSetGetDuracion() {
		p.setDuracion(0);
		assertEquals(0, p.getDuracion());
	}
	

}
