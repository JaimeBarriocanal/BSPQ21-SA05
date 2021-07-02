/** \file 
 * Breve descripción de es.deusto.spq.bd.PeliculasBDTest.java. July 1, 2021
 */
package es.deusto.spq.bd;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;

import es.deusto.spq.clases.Main;
import es.deusto.spq.clases.Pelicula;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * Clase test de PeliculasBD
 *
 */
public class PeliculasBDTest {
	public ContiPerfRule rule = new ContiPerfRule();
	private HttpServer server;
	private WebTarget appTarget;
	private Client client;

	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		client = ClientBuilder.newClient();
		appTarget = client.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	@PerfTest(invocations = 100, threads = 40)
	public void getPeliculasTest() {
		WebTarget pelisTarget = appTarget.path("peliculas");
		
		List<Pelicula> listapelis = Arrays.asList(new Pelicula("Seven", "David Fincher", "Drama", "Un curtido detective de homicidios y su nuevo compañero afrontan la caza de un asesino cuyos horrendos crímenes se inspiran en los siete pecados capitales", "Alquilada", 4.20, 126),                
				new Pelicula("Resacón en Las Vegas", "Todd Phillips", "Comedia", "Tres amigos se despiertan tras una loquísima despedida de soltero, pero no consiguen encontrar a su amigo, el que se suponía que se iba a casar", "Disponible", 3, 99));
	
		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {
        };
        List<Pelicula> pelis = pelisTarget.request(MediaType.APPLICATION_JSON).get(genericType);
        List<Pelicula> pelis2 = new ArrayList<Pelicula>();
        for (int i = 0; i < pelis.size(); i++) {

            if (pelis.get(i).getTitulo().equals(listapelis.get(0).getTitulo())) {

                pelis2.add(pelis.get(i));
                assertEquals(listapelis.get(0).getTitulo(), pelis2.get(0).getTitulo());
            }

        }

        assertEquals(listapelis.get(0).getDirector(), pelis.get(0).getDirector());
        assertEquals(listapelis.get(1).getDuracion(), pelis.get(1).getDuracion());
	}

}
