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
	public static boolean inicioSesion(User usr) {
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
	public static boolean admin(User usr) {
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

	public static int registrarUsuario(User usuario) {		
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
	
	
	}
