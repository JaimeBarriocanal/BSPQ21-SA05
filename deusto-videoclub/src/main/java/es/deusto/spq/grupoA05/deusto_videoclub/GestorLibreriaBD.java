package es.deusto.spq.grupoA05.deusto_videoclub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;



public class GestorLibreriaBD extends DataBaseManager{
	
	static GestorLibreriaBD gbd;
	private static final LoggerDeusto LOGGER = new LoggerDeusto(GestorLibreriaBD.class.getName(), 1);
	
	/**
	 * Acceder a la clase padre y crear objeto
	 */
	public GestorLibreriaBD(String dbName) {			
		super(dbName);
	}
	
	/**
	 * Metodo que inicia el gestor con la referencia del properties definida
	 */
	public static void iniciarGestor() {
		Prop.iniciarProp();
		gbd = new GestorLibreriaBD(Prop.prop.getProperty("db.path"));
	}

	/**
	 * Metodo que cierra la conexion de la base de datos
	 */
	public static void finalizar() {
		gbd.desconectarBD();
	}
	
	/**
	 * Metodo que cierra la conexion de la base de datos
	 */
	private void desconectarBD() {
		super.disconnect();
	}
	
	/**
	 * Metodo que inicia sesion comprobando los datos del usuario recibido con los que existen en la base de datos
	 * @param usr
	 * @return boolean
	 */
	public static boolean inicioSesion(Usuario usr) {
		boolean inicioCorrecto = false;
		try {
			if(usr != null) {
				System.out.println("[From Client] Peticion de inicio de sesion | Nombre de usuario: "+usr.getEmail());
				String Query = "select * from usuario where email='" + usr.getEmail() + "'";
				Statement statement = gbd.getStatement();
				ResultSet rs = statement.executeQuery(Query);			
				if (rs.next()) {
					// El ResultSet no está vacío
					String passFromBD = rs.getString("contrasenya");
					
					if(passFromBD.equals(usr.getPassword())) {
						// Contraseña correcta
						System.out.println("[Server] Credendiales correctas");
						inicioCorrecto = true;
					}else {
						System.out.println("[Server]  * Credenciales incorrectas");

					}
				}		
				rs.close();
				gbd.releaseStatement(statement);				
			}else {
				throw new Exception("El usuario es nulo");
			}			
		} catch (Exception ex) {
			LOGGER.getLOGGER().log(Level.WARNING,ex.getMessage());
		}
		
		return inicioCorrecto;
	}
	
	/**
	 * Metodo que comprueba si un usuario es administrador o no
	 * @param usr
	 * @return boolean
	 */
	public static boolean admin(Usuario usr) {
		boolean inicioCorrecto = false;
		try {
			if(usr != null) {
				System.out.println("[From Client] Peticion | Nombre de usuario: "+usr.getEmail());
				String Query = "select * from usuario where email='" + usr.getEmail() + "'";
				Statement statement = gbd.getStatement();
				ResultSet rs = statement.executeQuery(Query);			
				if (rs.next()) {
					// El ResultSet no está vacío
					String adminFromBD = rs.getString("admin");
					
					if(adminFromBD.equals("1")) {
						// Es admin
						System.out.println("[Server] Admin");
						inicioCorrecto = true;
					}else{
						System.out.println("[Server]  * No es admins");
					}
				}		
				rs.close();
				gbd.releaseStatement(statement);
			}else {
				throw new Exception("Objeto nulo");
			}
		}catch (Exception ex) {
			LOGGER.getLOGGER().log(Level.WARNING,ex.getMessage());
		}
		
		return inicioCorrecto;
	}
	
	/** Metodo que obtiene un nuevo id de la tabla de base de datos definida
	 */
	public static int getNewID(String tipo) {
		try {
			String sent = "select count(*) FROM "+tipo;
			Statement statement = gbd.getStatement();
			ResultSet rs;
			rs = statement.executeQuery(sent);
			if (rs.next()) {
		    	return rs.getInt(1);
			}
		} catch (Exception e) {
			LOGGER.getLOGGER().log(Level.WARNING,e.getMessage());
		}
		
		return 0;
	}

	/** Metodo que registra un usuario en la base de datos
	 * @param usuario  Usuario que se desea registrar en la BD
	 * @return  Codigo segun resultado. 
	 * 0 = Error interno, no se ha conseguido guardar el usuario
	 * 1 = Usuario registrado con exito
	 * 2 = Direccion de email ya en uso
	 * 3 = nombre de usuario ya en uso.
	 */
	public static int registrarUsuario(Usuario usuario) {		
		try {
			if(usuario != null) {
				//COMPROBAR QUE NO EXISTE YA UN USUARIO CON ESE EMAIL O USERNAME.
				String sent = "select * from usuario where email='" + usuario.getEmail() + "'";
				Statement statement = gbd.getStatement();
				ResultSet rs = statement.executeQuery(sent);
			    if (rs.next()) {
			    	System.out.println( "ERROR: El email ya esta registrado");
					return 2; //<-- Codigo 2: el mail ya esta en uso.		    	
			    }else {
			    	sent = "select * from usuario where username='" + usuario.getUsername() + "'";
					statement = gbd.getStatement();
					rs = statement.executeQuery(sent);
				    if (rs.next()) {
				    	System.out.println( "ERROR: El nombre de usuario ya esta uso");
						return 3; //<-- Codigo 3: el nombre de usuario ya esta en uso.		    	
				    }else {
				    	//REGISTRAR USUARIO...
				    	int id_usuario = getNewID("usuario") + 1; 
				    	try {
							statement.executeUpdate("INSERT INTO usuario"
					                + "(id_usuario, username, contrasenya, email, direccion, codigo_postal,admin) VALUES"
					                + "("+ id_usuario + ",'" + usuario.getUsername() + "','" + usuario.getPassword() + "','" 
					                + usuario.getEmail() + "','" + usuario.getAddress() + "'," + usuario.getCP() + ",0)");
							return 1; //<-- Codigo 1: No hay errores, el usuario se ha registrado correctamente
						} catch (SQLException e) {
							System.out.println( "ERROR al insertar usuario");
							e.printStackTrace();
						}
				    }
			    } 
			}else{
				throw new Exception("El usuario recibido es nulo");
			}
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			LOGGER.getLOGGER().log(Level.WARNING,ex.getMessage());
		}
		return 0; //<-- Codigo 0: algun error interno, no se ha podido registrar el usuario.
	}
	
	/**
	 * Metodo que borra un usuario de la base de datos
	 * @param usuario
	 * @return boolean
	 */
	public static boolean borrarUsuario(Usuario usuario) {
		boolean ok = false;		
		try {
			if(usuario != null && !usuario.getEmail().equals("admin")) {
				String sent = "delete from usuario where username='" + usuario.getUsername() + "'";
				Statement statement = gbd.getStatement();
				ok = statement.execute(sent);
				gbd.releaseStatement(statement);
				ok = true;
				return ok;
			}else {
				throw new Exception("No se ha podido borrar el usuario");
			}
			
		} catch (Exception ex) {
			LOGGER.getLOGGER().log(Level.WARNING,ex.getMessage());
		}			
		return ok;
	}


	/** Metodo que guarda un libro en la base de datos
	 * @param libro Libro que se desea guardar en la BD
	 * @return  Codigo segun resultado. 
	 * 0 = Error interno, no se ha conseguido guardar el libro
	 * 1 = Libro guardado con exito
	 * 2 = El libro ya existe en la BD
	 */
	public static int guardarPelicula(Pelicula pelicula) {
		String sent;
		Statement statement;
		ResultSet rs;		
		try {
			if(pelicula != null) {	
				//COMPROBAR QUE NO EXISTA YA LA PELÍCULA EN LA BD.	
				sent = "select * from pelicula where titulo='" + pelicula.getTitulo() + "'";
				statement = gbd.getStatement();
				rs = statement.executeQuery(sent);
			    if (rs.next()) {
			    	System.out.println( "ERROR: El titulo ya esta registrado");
			    	rs.close();
					gbd.releaseStatement(statement);
			    	return 2; //<-- Codigo 2: la película ya existe en la BD		    	
			    }else {
			    	//GUARDAR PELICULA...
			    	int id_pelicula = getNewID("pelicula") + 1;
			    	try {
						statement.executeUpdate("INSERT INTO libro"
				                + "(id_pelicula, titulo, director, genero, sinopsis, estado, precio, duracion) VALUES"
				                + "("+ id_pelicula + ",'" + pelicula.getTitulo() + "','" + pelicula.getDirector() + "'," 
				                + pelicula.getGenero() + "," + pelicula.getSinopsis() + ",'" + pelicula.getEstado() 
				                + "','" + pelicula.getPrecio() + "','" + pelicula.getDuracion() +"')");
						rs.close();
						gbd.releaseStatement(statement);
						return 1; //<-- Codigo 1: Película guardada correctamente
					} catch (SQLException e) {
						LOGGER.getLOGGER().log(Level.WARNING,e.getMessage());
					}			    
			    }  
			}else{
				throw new Exception("La pelicula recibida es nula");
			}
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			LOGGER.getLOGGER().log(Level.WARNING,ex.getMessage());
		}
		return 0; //<-- Codigo 0: algun error interno, no se ha podido guardar la película.
	}
	
	/** Metodo que borra un libro de la base de datos
	 * @param libro 
	 * @return boolean
	 */
	public static boolean borrarPelicula(Pelicula pelicula) {
        boolean ok = false;
        
    	try {
    		if(pelicula != null) {
    			String sent = "delete from pelicula where nombre ='" + pelicula.getTitulo() + "'";
                Statement statement = gbd.getStatement();
                ok = statement.execute(sent);
                gbd.releaseStatement(statement);
                ok = true;
                return ok;
            }else {
            	throw new Exception("El usuario recibido es nulo");
            }
            
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }                
        return ok;
    }
	

	/**
	 * Metodo que obtiene una lista de las películas que hay actualmente en la base de datos
	 * @return List
	 */
	public static List<Pelicula> obtenerPeliculas(){
		ArrayList<Pelicula> peliculas = new ArrayList<>();
		try {
			String Query = "select * from pelicula";
			Statement statement = gbd.getStatement();
			ResultSet rs = statement.executeQuery(Query);
			while(rs.next()) {
				String titulo = rs.getString("titulo");
				String director = rs.getString("director");
				String genero = rs.getString("genero");
				String sinopsis = rs.getString("sinopsis");
				String estado = rs.getString("sinopsis");
				double precio = rs.getDouble("venta");
				int duracion = rs.getInt("genero");
				
				Pelicula p = new Pelicula(titulo,director,genero, sinopsis, estado, precio, duracion);
				peliculas.add(p);
			}
		} catch (SQLException e) {
			LOGGER.getLOGGER().log(Level.WARNING,e.getMessage());
		}
		return peliculas;	
	}
	
	/**
	 * Metodo que realiza la busqueda de una o más películas en base de datos 
	 * @param search
	 * @param filtrogenero
	 * @return
	 */
	public static List<Pelicula> searchPeliculas(String search, String filtrogenero){
		ArrayList<Pelicula> peliculas = new ArrayList<>();
		String[] busquedaSplit = search.split(" ");
		String filtroCaps = filtrogenero.toUpperCase();
		try {
			if(!search.equals("")){
				// Buscar por titulo
				for(String s : busquedaSplit) {
					String Query = "";
					if(filtroCaps.equals("TODOS")) {
						Query = "select * from pelicula where titulo='" + s +"'";
					}else {
						Query = "select * from pelicula where titulo='" + s +"' and genero='"+ filtroCaps +"'";
					}
					
					Statement statement = gbd.getStatement();
					ResultSet rs = statement.executeQuery(Query);
					while(rs.next()) {
						String titulo = rs.getString("titulo");
						String director = rs.getString("director");
						String genero = rs.getString("genero");
						String sinopsis = rs.getString("sinopsis");
						String estado = rs.getString("estado");
						double precio = rs.getDouble("precio");
						int duracion = rs.getInt("duracion");
						
						Pelicula p = new Pelicula(titulo, director, genero, sinopsis,
								estado, precio, duracion);
						peliculas.add(p);
					}
					rs.close();
					gbd.releaseStatement(statement);
				}
				
				// Buscar por director
				for(String s : busquedaSplit) {
					String Query = "";
					if(filtroCaps.equals("TODOS")) {
						Query = "select * from pelicula where autor='" + s +"'";
					}else {
						Query = "select * from pelicula where autor='" + s +"' and genero='"+ filtroCaps +"'";
					} 
					Statement statement = gbd.getStatement();
					ResultSet rs = statement.executeQuery(Query);
					while(rs.next()) {
						String titulo = rs.getString("titulo");
						String director = rs.getString("director");
						String genero = rs.getString("genero");
						String sinopsis = rs.getString("sinopsis");
						String estado = rs.getString("estado");
						double precio = rs.getDouble("precio");
						int duracion = rs.getInt("duracion");
						
						Pelicula p = new Pelicula(titulo, director, genero, sinopsis,
								estado, precio, duracion);
						peliculas.add(p);
					}
					rs.close();
					gbd.releaseStatement(statement);
				}
			}else {
				throw new Exception("Busqueda vacia");
			}
			
		} catch (Exception e) {
			LOGGER.getLOGGER().log(Level.WARNING,e.getMessage());
		}
		return peliculas;	
	}
	
	
	}
