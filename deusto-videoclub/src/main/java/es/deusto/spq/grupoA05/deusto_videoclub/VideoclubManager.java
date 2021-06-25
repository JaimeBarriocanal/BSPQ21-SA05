package es.deusto.spq.grupoA05.deusto_videoclub;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import es.deusto.spq.grupoA05.deusto_videoclub.GestorLibreriaBD;
import es.deusto.spq.grupoA05.deusto_videoclub.Prop;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class LibreriaManager {
    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("es.deusto.spq.grupoA05");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        BASE_URI = String.format("http://%s:%s/",Prop.prop.getProperty("server.ip"),Prop.prop.getProperty("server.port"));
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
//    	String hostname = args[0];
//		String port = args[1];
		// Activar conexion a BD
		GestorLibreriaBD.iniciarGestor();
		// Iniciar referencia al archivo properties
		Prop.iniciarProp();
		// Activar el servidor
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        GestorLibreriaBD.finalizar();
        server.stop();
    }
}
