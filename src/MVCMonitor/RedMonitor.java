package MVCMonitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Properties;

public class RedMonitor extends Observable implements IRedMonitor {
	
	private int puertoPrim,puertoSec;
	private String ip;
	
	
	public RedMonitor() {
		
		try {
			Properties properties = new Properties();
			FileInputStream configFile= new FileInputStream("configMonitor.properties");
			properties.load(configFile);
			ip=properties.getProperty("ipServidor");
			puertoPrim = Integer.parseInt(properties.getProperty("puertoPrim"));
			puertoSec= Integer.parseInt(properties.getProperty("puertoSec"));
			
		} catch (Exception e) {
			System.out.println("Error en archivo configMonitor.properties");
		}
		
		new Thread(){public void run(){pingPrimario();}}.start();
		
	}


	@Override
	public void pingPrimario() {
		while(true) {
			try {
				Socket socket = new Socket(ip,puertoPrim);
				OutputStream outputStream = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject("Disponible ServidorPrim?");
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				if( in.readLine().equals("Llego"))
	            	notifyObservers("Disponible Primario");
				Thread.sleep(5000);
			}
			catch(Exception e) {
				notifyObservers("No Disponible Primario");
			}
		}
	}

	

}
