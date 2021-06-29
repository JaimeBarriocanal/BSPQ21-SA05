package es.deusto.spq.clases;

import javax.jdo.annotations.*;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Usuario {
	private String id;
	@PrimaryKey
	private String username;
	private String password;
	private String email;
	private int cp;
	private boolean administrador;

	
	public Usuario() {
	}
	public Usuario(String username, String password, String email, int cp) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.cp = cp;
		this.administrador = false;
	}
	
	public Usuario(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email; 
		this.administrador = false;
		this.cp = 0;
	}
	
	public boolean getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(boolean admin) {
		this.administrador = admin;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String user_code) {
		this.username = user_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCP() {
		return cp;
	}

	public void setCP(int cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + this.id + ", Username=" + this.username + ", Email=" + email + ", CP=" + cp + "]";
	}
	
}
