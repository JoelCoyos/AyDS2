package clasesComunes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	
	Properties properties = new Properties();
	FileInputStream configFile;
	
	public Propiedades(String nombre) {
		try {
			configFile = new FileInputStream(nombre);
			properties.load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropiedad(String propiedad)
	{
		return properties.getProperty(propiedad);
	}
	

}
