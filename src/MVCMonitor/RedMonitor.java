package MVCMonitor;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Properties;

public class RedMonitor extends Observable implements IRedMonitor {
	
	private int puertoPrim,puertoSec;
	
	
	public RedMonitor() {
		
		try {
			Properties properties = new Properties();
			FileInputStream configFile= new FileInputStream("configMonitor.properties");
			properties.load(configFile);
			puertoPrim = Integer.parseInt(properties.getProperty("puertoPrim"));
			puertoSec= Integer.parseInt(properties.getProperty("puertoSec"));
			
		} catch (Exception e) {
			System.out.println("Error en archivo configMonitor.properties");
		}
		
		new Thread(){public void run(){pingPrimario();}}.start();
		
	}


	@Override
	public void pingPrimario() {
		ServerSocket ss;
		try {
			ss= new ServerSocket(puertoPrim);
			while(true) {
				Socket socket=ss.accept();
				InetAddress ia= socket.getLocalAddress();
				if (ia.isReachable(5000)) 
					notifyObservers("Disponible Primario");
				else 
					notifyObservers("No Disponible Primario");
			}
		}
		catch(Exception e) {
			System.out.println("No esta funcionando la toma del ping del Servidor Primario");
		}
		
	}

	

}
