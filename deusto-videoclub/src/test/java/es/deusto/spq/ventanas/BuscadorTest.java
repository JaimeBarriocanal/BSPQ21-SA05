package es.deusto.spq.ventanas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;

import es.deusto.spq.clases.Pelicula;

public class BuscadorTest {
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
		Buscador.main(new String[0]);
	}

	@Test
	public void testEliminarPeliculaBd() {
		Buscador.eliminarPeliculaBd(peliculas, p);
		Buscador.eliminarPeliculaBd(peliculas, p3);
		
	}

	@Test
	public void testAddPelicula() {
		Buscador.addPelicula(peliculas, p3);
		Buscador.addPelicula(peliculas, p4);
		
	}

}
