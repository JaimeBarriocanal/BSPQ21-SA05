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
    
    public static Pelicula peli0 = new Pelicula("Prueba", "yo", "acción", "Gran pelea", "Alquilada", 2, 180);
    public static Pelicula peli1 = new Pelicula("Malditos Bastardos", "Quentin Tarantino", "acción", "Durante la II Guerra Mundial, un pelotón de soldados judíoamericanos conocidos como «Los Bastardos» son seleccionados para sembrar el terror a lo largo y ancho del Tercer Reich. ", "Alquilada", 4.50, 153);
    public static Pelicula peli2 = new Pelicula("Avengers: Endgame", "Hermanos Russo", "Ciencia Ficción", "Segunda entrega de la saga Vengadores", "Disponible", 6.95, 182);
    public static Pelicula peli3 = new Pelicula("Resacón en Las Vegas", "Todd Phillips", "Comedia", "Tres amigos se despiertan tras una loquísima despedida de soltero, pero no consiguen encontrar a su amigo, el que se suponía que se iba a casar", "Disponible", 3, 99);
    public static Pelicula peli4 = new Pelicula("Forrest Gump", "Robert Zemeckis", "Comedia", "Forrest Gump, un hombre amable de corta inteligencia será testigo de casi todos los sucesos cruciales de su país durante los años 60 y 70", "Alquilada", 3, 143);
    public static Pelicula peli5 = new Pelicula("El Lobo de Wall Street", "Martin Scorsese", "Comedia", "Martin Scorsese nos narra la lujosa vida del corredor de bolsa de Wall Street, Jordan Belfort, cuya vertiginosa carrera tuvo como final una prisión federal", "Alquilada", 5, 179);
    public static Pelicula peli6 = new Pelicula("El precio del poder", "Brian de Palma", "Drama", "Durante su despiadado ascenso como magnate de la droga en Miami, un gánster cubano cae en la adicción, la obsesión y la violencia, con unas consecuencias espantosas", "Disponible", 4, 169);
    public static Pelicula peli7 = new Pelicula("Django Desencadenado", "Quentin Tarantino", "Western", "Django, un esclavo liberado, recorre los Estados Unidos acompañado de un cazarrecompensas alemán para liberar a su mujer del sádico dueño de una plantación", "Disponible", 5.90, 165);
    public static Pelicula peli8 = new Pelicula("Seven", "David Fincher", "Drama", "Un curtido detective de homicidios y su nuevo compañero afrontan la caza de un asesino cuyos horrendos crímenes se inspiran en los siete pecados capitales", "Alquilada", 4.20, 126);
    public static Pelicula peli9 = new Pelicula("Misión imposible: Nación Secreta", "Christopher McQuarrie", "Acción", "Tras la disolución de la FMI, Ethan Hunt y su equipo inician una guerra contra el Sindicato, un grupo de espías renegados que ansía a destrucción mundial", "Alquilada", 2.95, 180);
   
    
    
    public static void main(String[] args) {
    	try {
    		tx.begin();
        	pm.makePersistent(user0);
        	pm.makePersistent(admin);
        	pm.makePersistent(peli0);
        	pm.makePersistent(peli1);
        	pm.makePersistent(peli2);
        	pm.makePersistent(peli3);
        	pm.makePersistent(peli4);
        	pm.makePersistent(peli5);
        	pm.makePersistent(peli6);
        	pm.makePersistent(peli7);
        	pm.makePersistent(peli8);
        	pm.makePersistent(peli9);
        	tx.commit();
		} finally {
			if(tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
    	
    }
    
}