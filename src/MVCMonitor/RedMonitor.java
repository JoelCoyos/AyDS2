package MVCMonitor;

import java.io.FileInputStream;
import java.util.Properties;

public class RedMonitor implements IRedMonitor {
	
	private int puertoPrim,puertoSec;
	
	
	public RedMonitor() {
		
		try {
			Properties properties = new Properties();
			FileInputStream configFile= new FileInputStream("configServidor.properties");
			properties.load(configFile);
			puertoPrim = Integer.parseInt(properties.getProperty("puertoPrim"));
			puertoSec= Integer.parseInt(properties.getProperty("puertoSec"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void activoPrimario() {
		// TODO Auto-generated method stub

	}

}
