package MVCReceptor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

@SuppressWarnings("deprecation")
public class RedReceptor extends Observable implements IRedReceptor {
	
	private static Emergencia emergencia;
	Properties properties;
	
	public RedReceptor() {
		new Thread(){public void run(){Escuchar();}}.start();
		properties = new Properties();
	}
	
	public void Escuchar()
	{
            ServerSocket ss;
            Properties properties = new Properties();
			try {
				FileInputStream configFile= new FileInputStream("configReceptor.properties");
				properties.load(configFile);
				int puerto = Integer.parseInt(properties.getProperty("puerto"));
				ss = new ServerSocket(puerto);
				while(true)
				{
					Socket socket = ss.accept(); 
					System.out.println("Se conecto!");
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					emergencia = (Emergencia) objectInputStream.readObject();
					System.out.println("Mensaje recibido");
					setChanged();
					notifyObservers("Emergencia");
					socket.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void RegistrarServidor()
	{
		try {
			FileInputStream configFile= new FileInputStream("configReceptor.properties");
			properties.load(configFile);
			InetAddress inetAddress = InetAddress.getLocalHost();
			RegistroReceptor registro = new RegistroReceptor(inetAddress.getHostAddress(),properties.getProperty("tipoEmergencia"));
			//Cambiar por configuracion archivo
			Socket socket = new Socket("192.168.0.105",1001);
			OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(registro);
			socket.close();
			} catch (Exception e) {
			e.printStackTrace();
			}

	}
	
	@Override
	public Emergencia GetEmergencia() {
		return emergencia;
	}

}
