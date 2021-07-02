/** \file 
 * Breve descripción de es.deusto.spq.clases.Usuario.java. July 1, 2021
 */
package es.deusto.spq.clases;

import javax.jdo.annotations.*;
import javax.jdo.annotations.PersistenceCapable;
/**
 * 
 * Clase base de Usuario
 *
 */
@PersistenceCapable
public class Usuario {
	private String id;
	@PrimaryKey
	private String username;
	private String password;
	private String email;
	private int cp;
	private boolean administrador;

	/**
	 * 
	 * Contructor vacío de usuario
	 *
	 */

	public Usuario() {
	}
	/**
	 * 
	 * Contructor de usuario
	 *
	 */

	public Usuario(String username, String password, String email, int cp) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.cp = cp;
		this.administrador = false;
	}
	/**
	 * 
	 * Contructor de usuario
	 *
	 */

	public Usuario(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email; 
		this.administrador = false;
		this.cp = 0;
	}

	/**
	 * 
	 * Obtener administrador
	 *
	 */
	public boolean getAdministrador() {
		return administrador;
	}
	/**
	 * 
	 * Establecer administrador
	 *
	 */
	public void setAdministrador(boolean admin) {
		this.administrador = admin;
	}
	/**
	 * 
	 * Obtener Username
	 *
	 */
	
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * Establecer Username
	 * 
	 */
	public void setUsername(String user_code) {
		this.username = user_code;
	}
	/**
	 * 
	 * Obtener Contraseña
	 *
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * Establecer contraseña
	 *
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * Obtener Email
	 *
	 */

	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * Establecer Email
	 *
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * Obtener codigo postal
	 *
	 */

	public int getCP() {
		return cp;
	}
	/**
	 * 
	 * establecer codigo postal
	 *
	 */

	public void setCP(int cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + this.id + ", Username=" + this.username + ", Email=" + email + ", CP=" + cp + "]";
	}
	
}
