package es.deusto.spq.grupoA05.deusto_videoclub;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerDeusto {
	
	private static Logger LOGGER;
	private static Handler filehandler;
	
	public LoggerDeusto(String classname, int tipo) {
		LOGGER = Logger.getLogger(classname);
		Prop.iniciarProp();
		try {
			switch(tipo) {
				case 1: // Se referencia al log de servidor
					filehandler = new FileHandler(Prop.prop.getProperty("log.path") + Prop.prop.getProperty("log.server.name"), true);
					LOGGER.addHandler(filehandler);
					break;
				case 2: // Se referencia al log del cliente
					filehandler = new FileHandler(Prop.prop.getProperty("log.path") + Prop.prop.getProperty("log.client.name"), true);
					LOGGER.addHandler(filehandler);
					break;
				default:
					throw new Exception("No se ha podido crear el FileHandler");					
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public Logger getLOGGER() {
		return LOGGER;
	}
}
