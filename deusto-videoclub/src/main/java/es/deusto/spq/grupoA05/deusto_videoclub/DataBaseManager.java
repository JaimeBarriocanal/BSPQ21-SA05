package es.deusto.spq.grupoA05.deusto_videoclub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseManager {

	private Connection connection;

	/**
	 * Constructor de la clase. Crea la clase y se conecta a la base de datos.
	 */
	public DataBaseManager(String dbName) {
		this.connect(dbName);
	}
	
	/**
	 * Metodo que se conecta a la BD
	 */
	private void connect(String dbName) {
		this.connectSQLITE(dbName);
	}
	
	/**
	 * Metodo para conectarse a la base de datos.
	 */
	private void connectSQLITE(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ".connectSQLITE(): " + ex);
		}
	}

	/**
	 * Metodo que cierra la conexion a la base de datos.
	 */
	public void disconnect() {
		try {
			this.connection.close();
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ".disconnect(): " + ex);
		}
	}

	/**
	 * Metodo que crea un statement para comunicarse con la BD.
	 */
	public Statement getStatement() {
		try {
			return this.connection.createStatement();
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ".getStatement(): " + ex);
			return null;
		}
	}

	/**
	 * Metodo que devuelve una conexion a la BD
	 * @return
	 */
	public Connection getConnection() {
		try {
			return this.connection;
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ex);
			return null;
		}
	}
	
	/**
	 * Cierra y libera el statement.
	 */
	public void releaseStatement(Statement statement) {
		try {
			statement.close();
		} catch (Exception ex) {
			System.err.println(this.getClass().getName() + ".releaseStatement(): " + ex);
		}
	}
}