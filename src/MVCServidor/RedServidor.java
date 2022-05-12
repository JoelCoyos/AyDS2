package MVCServidor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public class RedServidor implements IRedServidor {
	
	private ArrayList<String> ipBombero;
	private ArrayList<String> ipSeguridad;
	private ArrayList<String> ipMedica;
	
	private int puertoReceptor;
	private int puertoEmisor;
	
	public Emergencia emergencia;
	
	public RedServidor()
	{
		ipBombero = new ArrayList<>();
		ipSeguridad = new ArrayList<>();
		ipMedica = new ArrayList<>();
		
		puertoEmisor = 1001;
		puertoReceptor = 1002;
		
		new Thread(){public void run(){RegistroReceptor();}}.start();
		new Thread(){public void run(){RecibirEmergencia();}}.start();
	}

	@Override
	public void RegistroReceptor() {
		ServerSocket ss;
		try {
			ss = new ServerSocket(puertoReceptor);
			while(true)
			{
				Socket socket = ss.accept(); 
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				emergencia = (Emergencia) objectInputStream.readObject();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void RecibirEmergencia() {
		
        ServerSocket ss;
        Properties properties = new Properties();
        RegistroReceptor registro;
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
				registro = (RegistroReceptor) objectInputStream.readObject();
				System.out.println("Mensaje recibido");
				socket.close();
				if(registro.tipoEmergencia == "bombero")
					ipBombero.add(registro.ip);
				//Agregar el resto
					
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		

	@Override
	public void EnviarEmergencia() {
		// TODO Auto-generated method stub
		
	}
	

}
