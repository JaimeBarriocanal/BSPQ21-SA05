package es.deusto.spq.grupoA05.deusto_videoclub;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Prop {
	
	public static Properties prop;
	private static InputStream is;
	
	public Prop() {
		try {
			prop = new Properties();
			is = new FileInputStream("src/main/resources/properties/deusto.properties");
			prop.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public static void iniciarProp() {
		try {
			prop = new Properties();
			is = new FileInputStream("src/main/resources/properties/deusto.properties");
			prop.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
	}
}

