package es.deusto.spq.datos;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.clases.Pelicula;
import es.deusto.spq.clases.Usuario;

public class PreparedData {

	public static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    public static PersistenceManager pm = pmf.getPersistenceManager();
    public static Transaction tx = pm.currentTransaction();

    public static Usuario user0 = new Usuario("user0", "1234", "user0@gmail.com", 48000);
    public static Usuario admin = new Usuario("admin", "1234", "admin@gmail.com", 48000);
    
    public static Pelicula peli0 = new Pelicula("Prueba", "yo", "acción", "Gran pelea", "alquilada", 2, 180);
    
    
    public static void main(String[] args) {
    	try {
    		tx.begin();
        	pm.makePersistent(user0);
        	pm.makePersistent(admin);
        	pm.makePersistent(peli0);
        	tx.commit();
		} finally {
			if(tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
    	
    }
    
}