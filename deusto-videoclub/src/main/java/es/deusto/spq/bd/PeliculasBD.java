package es.deusto.spq.bd;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.clases.Pelicula;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("peliculas")
public class PeliculasBD {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> getPeliculas() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        Query<Pelicula> q = pm.newQuery(Pelicula.class);
        q.setOrdering("titulo desc");

        List<Pelicula> peliculas = q.executeList();

        pm.close();

        return peliculas;
		
	}
}
