package MVCServidor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public class RedServidor extends Observable implements IRedServidor {
	
	private ArrayList<RegistroReceptor> ipBombero;
	private ArrayList<RegistroReceptor> ipSeguridad;
	private ArrayList<RegistroReceptor> ipMedica;
	
	private int puertoReceptor;
	private int puertoEmisor;
	
	private Emergencia emergencia;
	public RegistroReceptor registro;
	
	public RedServidor()
	{
		ipBombero = new ArrayList<RegistroReceptor>();
		ipSeguridad = new ArrayList<RegistroReceptor>();
		ipMedica = new ArrayList<RegistroReceptor>();
		
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
				registro = (RegistroReceptor) objectInputStream.readObject();
				if(registro.tipoEmergencia.equals("Bomberos"))
					ipBombero.add(registro);
				else if (registro.tipoEmergencia.equals("Seguridad"))
					 ipSeguridad.add(registro);
				else if (registro.tipoEmergencia.equals("Medica"))
					 ipMedica.add(registro);
				setChanged();
				notifyObservers("Registro");
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
		try {
			FileInputStream configFile= new FileInputStream("configServer.properties");
			properties.load(configFile);
			ss = new ServerSocket(puertoEmisor);
			while(true)
			{
				Socket socket = ss.accept(); 
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				emergencia = (Emergencia) objectInputStream.readObject();
				setChanged();
				notifyObservers("Emergencia");
				socket.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		

	@Override
	public void EnviarEmergencias() {
		ArrayList<RegistroReceptor> listaReceptores = null;
		if(this.emergencia.getTipoEmergencia().equals("Bomberos"))
		{
			listaReceptores = ipBombero;
		}
		else if(this.emergencia.getTipoEmergencia().equals("Seguridad"))
		{
			listaReceptores = ipSeguridad;
		}
		else if(this.emergencia.getTipoEmergencia().equals("Medica"))
		{
			listaReceptores = ipMedica;
		}
		for (RegistroReceptor receptor : listaReceptores) {
			EnviarEmergencia(receptor.ip, receptor.puerto);
		}
	}
	
	public void EnviarEmergencia(String ip,int puerto)
	{
		
		try {	
	        Socket socket = new Socket(ip,puerto);
	        OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(emergencia);
            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out.println("hola");
	        socket.close();
			} 
		catch (Exception e) {
			}
	}

	@Override
	public Emergencia getEmergencia() {
		return this.emergencia;
	}
	
	
	

}
