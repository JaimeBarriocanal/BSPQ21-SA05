package es.deusto.spq.grupoA05.deusto_videoclub;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import es.deusto.spq.grupoA05.deusto_videoclub.GestorLibreriaBD;
import es.deusto.spq.grupoA05.deusto_videoclub.Pelicula;
import es.deusto.spq.grupoA05.deusto_videoclub.Usuario;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/deusto-booking")
@Produces(MediaType.APPLICATION_JSON)
public class LibreriaServer {
	/**
	 * Metodo que inicia sesion recibiendo un usuario desde el cliente
	 * @param usr el usuario que esta tratando de iniciar sesion
	 * @return boolean que da responde a si se ha podido iniciar sesion
	 */
    @POST
    @Path("/iniciosesion")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response inicioSesion(Usuario usr) {
 
    	if(GestorLibreriaBD.inicioSesion(usr)) {
    		return Response.ok().build();
    	}else {
    		return Response.serverError().build();
    	}
	}
    
    /**
     * Metodo que registra un nuevo usuario recibido desde el cliente
     * @param usr usuario que se intenta registrar en la aplicacion
     * @return una response con un codigo en base a si se ha conseguido registrar el usuario
     */
    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response registroUsuario(Usuario usr) {
    	/* 0 = Error interno, no se ha conseguido guardar el usuario
		 * 1 = Usuario registrado con exito
		 * 2 = Direccion de email ya en uso
		 * 3 = nombre de usuario ya en uso.
		 * */
    	switch(GestorLibreriaBD.registrarUsuario(usr)) {
    		case 0:
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se ha podido guardar el usuario").build();
    		case 1:
    			return Response.ok().build();
    		case 2:
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Direccion de email en uso").build();
    		case 3:
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nombre de usuario en uso").build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en el servidor").build();
    	}    	
	}
    
	/**
	 * Metodo que busca si el cliente es administrador
	 * @param usr usuario que se usara para comprobar si es administrador
	 * @return una response con la confirmacion o negacion de que es administrador
	 */
    @POST
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response admin(Usuario usr) {
 
    	if(GestorLibreriaBD.admin(usr)) {
    		return Response.ok().build();
    	}else {
    		return Response.accepted().build();
    	}
	}
    
    /**
     * Metodo que borra un usuario recibido desde el cliente
     * @param usr usuario que se borrara de la base de datos
     * @return una response con el resultado de la accion
     */
    @POST
    @Path("/borrarusuario")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response borrarUsuario(Usuario usr) {
 
    	if(GestorLibreriaBD.borrarUsuario(usr)) {
    		return Response.ok().build();
    	}else {
    		return Response.serverError().build();
    	}
	}
    
    /**
     * Metodo que borra una película seleccionada desde el cliente
     * @param peli Pelicula que se borrara de la base de datos
     * @return una response con el resultado del intento de borrado
     */
    @POST
    @Path("/borrarpelicula")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response borrarPelicula(Pelicula peli) {
 
    	if(GestorLibreriaBD.borrarPelicula(peli)) {
    		return Response.ok().build();
    	}else {
    		return Response.serverError().build();
    	}
	}
    
    /**
     * Metodo que guarda una pelicula nueva en la BD con los datos recogidos desde el cliente
     * @param peli Pelicula que se quiere registrar en la base de datos
     * @return una response que da un codigo con el resultado de intentar guardar un libro
     */
    @POST
    @Path("/guardarpelicula")
    @Consumes(MediaType.APPLICATION_JSON)
	public Response guardarPelicula(Pelicula peli) {
    	/* 0 = Error interno, no se ha conseguido guardar el libro
		 * 1 = Libro guardado con exito
		 * 2 = Titulo ya existente
		 * */
    	switch(GestorLibreriaBD.guardarPelicula(peli)) {
    		case 0:
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se ha podido guardar la pelicula").build();
    		case 1:
    			return Response.ok().build();
    		case 2:
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Titulo ya existente").build();
    		default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en el servidor").build();
    	}    	
	}
    
    /**
     * Metodo que devuelve el stock completo de los libros
     * @return una lista de todos los libros almacenados en la base de datos
     */
    @GET
    @Path("/obtenerpeliculas")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> obtenerPeliculas() {
    	List<Pelicula> peliculasBD = GestorLibreriaBD.obtenerPeliculas();    	
    	GenericEntity<List<Pelicula>>  entity = new GenericEntity<List<Pelicula>>(peliculasBD){};
    	return entity.getEntity(); 
	}
    
    /**
     * Metodo que devuelve las peliculas segun la busqueda hecha
     * @param busqueda string con la busqueda que se hara contra la base de datos
     * @param filtrogenero filtro que se usara para filtar los resultados de la base de datos
     * @return lista de peliculas con la busqueda solicitada
     */
    @GET
    @Path("/searchpeliulas/{busqueda}/{filtrogenero}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> searchPeliculas(@PathParam("busqueda") String busqueda, @PathParam("filtrogenero") String filtrogenero) {
    	System.out.println("[Client] Busqueda de Peliculas: " + busqueda +"| Filtro: "+ filtrogenero.toUpperCase());
    	List<Pelicula> peliculasBD = GestorLibreriaBD.searchPeliculas(busqueda, filtrogenero);    	
    	GenericEntity<List<Pelicula>>  entity = new GenericEntity<List<Pelicula>>(peliculasBD){};
    	return entity.getEntity(); 
	}

 
}
