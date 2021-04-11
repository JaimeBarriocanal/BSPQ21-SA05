package es.deusto.spq.grupoA05.deusto_videoclub;

public class Pelicula {

	private static int contador = 1;
	
	private int id;
	private String titulo;
	private String director;
	private String genero;
	private String sinopsis;
	private String estado;
	private double precio;
	private int duracion;

	
	public Pelicula(String titulo, String director, String genero, String sinopsis, String estado,
			double precio2, int duracion) {
		super();
		this.id = contador++;
		this.titulo = titulo;
		this.director = director;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.estado = estado;
		this.precio = precio2;
		this.duracion = duracion;
	}
	
	/**Constructor de libro staandar.
	 * Usado para realizar tests
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

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getdirector() {
		return director;
	}

	public void setdirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getestado() {
		return estado;
	}

	public void setestado(String estado) {
		this.estado = estado;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getduracion() {
		return duracion;
	}

	public void setduracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", director=" + director + ", genero=" + genero + ", sinopsis="
				+ sinopsis + ", estado=" + estado + ", precio=" + precio + ", duracion=" + duracion + "]";
	}	
}