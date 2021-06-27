package es.deusto.spq.bd;

import java.util.List;
import java.util.logging.*;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import es.deusto.spq.clases.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("usuarios")
public class UsuariosBD {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        Query<Usuario> q = pm.newQuery(Usuario.class);
        q.setOrdering("name desc");

        List<Usuario> usuarios = q.executeList();

        pm.close();

        return usuarios;
		
	}
	
	public boolean comprobarUsuarios(String nombre, String contra) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        Usuario usuario = new Usuario();

        try (Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nombre
                + "' && password== '" + contra + "'")) {
            List<Usuario> usuarios = q.executeList();

            usuario = usuarios.get(0);
            System.out.println(usuario.toString());

            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, "ERROR", e);
            e.printStackTrace();

        }
        pm.close();

        return false;
		
	}
	
}
