package es.deusto.spq.ventanas;

import static org.junit.Assert.*;

import javax.swing.ListModel;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.clases.Pelicula;

public class BuscadorAdminTest {
	Pelicula p = new Pelicula();
	Pelicula p2 = new Pelicula();
	Pelicula p3 = new Pelicula();
	ListModel <Pelicula> peliculas = new ListModel <>();
	
	@Before
	public void setup() {
		p = new Pelicula ("titulo", "director", "genero", "sinopsis", "disponible", 5, 120);
		p2 = new Pelicula ("titulo2", "director2", "genero2", "sinopsis2", "disponible2", 5, 120);
		p3 = new Pelicula ("", "", "", "", "", 0, 0);
		p4 = new Pelicula ("titulo3", "director3", "genero3", "sinopsis3", "disponible3", 5, 120);
		peliculas.add(0, p);
		peliculas.add(1, p2);
	}

	@Test
	public void testMain() {
		BuscadorAdmin.main(new String[0]);
	}

	@Test
	public void testEliminarPeliculaBd() {
		BuscadorAdmin.eliminarPeliculaBd(peliculas, p);
		BuscadorAdmin.eliminarPeliculaBd(peliculas, p3);
	}

}
