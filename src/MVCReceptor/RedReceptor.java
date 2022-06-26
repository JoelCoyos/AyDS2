package MVCReceptor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Emergencia;

@SuppressWarnings("deprecation")
public class RedReceptor extends Observable implements IRedReceptor {
	
	private static Emergencia emergencia;
	Properties properties;
	String[] tiposEmergencia;
	public Integer puerto;
	
	public RedReceptor() {
		properties = new Properties();
		FileInputStream configFile;
		try {
			configFile = new FileInputStream("configReceptor.properties");
			properties.load(configFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tiposEmergencia = properties.getProperty("tipoEmergencia").split(",");
		puerto = Integer.parseInt(properties.getProperty("puerto"));
		new Thread(){public void run(){Escuchar();}}.start();
	}
	
	public void Escuchar()
	{
            ServerSocket ss;
			try {
				ss = new ServerSocket(puerto);
				while(true)
				{
					Socket socket = ss.accept(); 
					System.out.println("Se conecto!");
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					emergencia = (Emergencia) objectInputStream.readObject();
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					if( Arrays.asList(tiposEmergencia).contains(emergencia.getTipoEmergencia()) == true)
					{
						setChanged();
						notifyObservers("Emergencia");
					}
					else {
						out.println("Llego");
					}
					socket.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public Emergencia GetEmergencia() {
		return emergencia;
	}

}
