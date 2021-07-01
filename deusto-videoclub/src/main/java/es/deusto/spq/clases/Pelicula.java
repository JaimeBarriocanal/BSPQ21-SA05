/** \file 
 * Breve descripción de es.deusto.spq.clases.Pelicula.java. July 1, 2021
 */
package es.deusto.spq.clases;

import javax.jdo.annotations.*;

/**
 * 
 * Clase base de pelicula
 *
 */
@PersistenceCapable
public class Pelicula {

	private static int contador = 1;
	
	private int id;
	@PrimaryKey
	private String titulo;
	private String director;
	private String genero;
	private String sinopsis;
	private String estado;
	private double precio;
	private int duracion;

	/**
	 * 
	 * Contructor de pelicula
	 *
	 */
	public Pelicula(String titulo, String director, String genero, String sinopsis, String estado,
			double precio, int duracion) {
		super();
		this.id = contador++;
		this.titulo = titulo;
		this.director = director;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.estado = estado;
		this.precio = precio;
		this.duracion = duracion;
	}
	/**
	 * 
	 * Constructor vacío
	 *
	 */
	public Pelicula() {
		super();
		this.id = contador++;
		this.titulo = "Desconocido";
		this.director = "Desconocido";
		this.genero = "Accion";
		this.sinopsis = "Sin sinopsis";
		this.estado = "Disponible";
		this.precio = 0;
		this.duracion = 0;
	}

	/**
	 * 
	 * Obtener id de pelicula
	 *
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * Obtener titulo de pelicula
	 *
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * 
	 * Establecer titulo de pelicula
	 *
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * 
	 * Obtener director de pelicula
	 *
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * 
	 * Establecer director de pelicula
	 *
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * 
	 * Obtener genero de pelicula
	 *
	 */
	public String getGenero() {
		return genero;
	}
	/**
	 * 
	 * Establecer genero de pelicula
	 *
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	/**
	 * 
	 * Obtener sinopsis de pelicula
	 *
	 */
	public String getSinopsis() {
		return sinopsis;
	}
	/**
	 * 
	 * Establecer sinopsis de pelicula
	 *
	 */
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	/**
	 * 
	 * Obtener estado de pelicula
	 *
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * 
	 * Establecer estado de pelicula
	 *
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * Obtener precio de pelicula
	 *
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * 
	 * Establecer precio de pelicula
	 *
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * 
	 * Obtener duración de pelicula
	 *
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * 
	 * Establecer duración de pelicula
	 *
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * 
	 * toString
	 *
	 */
	@Override
	public String toString() {
		return titulo;
	}	
}