package MVCEmisor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

import clasesComunes.Emergencia;

public class RedEmisor implements IRedEmisor {
	
	InetAddress localhost;
	

	@Override
	public boolean EnviarEmergencia(Emergencia emergencia) 
	{
		Boolean llego = null;
		Properties properties = new Properties();
		try {
			FileInputStream configFile= new FileInputStream("configEmisor.properties");
			properties.load(configFile);
			System.out.println("Conectando...");
			int puerto = Integer.parseInt(properties.getProperty("puerto"));
	        Socket socket = new Socket(properties.getProperty("ipReceptor"),puerto);
	        System.out.println("Conectado!");

	        OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(emergencia);
            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out.println("hola");
	        socket.close();
	        llego = true;
			} 
		catch (Exception e) {
			System.out.println("Algo salio mal");
			llego = false;
			}

		return llego;
	}

}
