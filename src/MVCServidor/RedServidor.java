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
		try {
			Properties properties = new Properties();
			FileInputStream configFile= new FileInputStream("configServidor.properties");
			properties.load(configFile);
			puertoEmisor = Integer.parseInt(properties.getProperty("puertoEmisor"));
			puertoReceptor = Integer.parseInt(properties.getProperty("puertoReceptor"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		new Thread(){public void run(){RegistroReceptor();}}.start();
		new Thread(){public void run(){RecibirEmergencia();}}.start();
		new Thread(){public void run(){escucharMonitor1();}}.start();
		
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
				for (String tipo : registro.tipoEmergencia) {
					if(tipo.equals("Bomberos"))
						ipBombero.add(registro);
					else if (tipo.equals("Seguridad"))
						ipSeguridad.add(registro);
					else if (tipo.equals("Medica"))
						ipMedica.add(registro);					
				}
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
        boolean llego = false;
		try {
			ss = new ServerSocket(puertoEmisor);
			while(true)
			{
				Socket socket = ss.accept(); 
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				emergencia = (Emergencia) objectInputStream.readObject();
				setChanged();
				notifyObservers("Emergencia");
				llego = EnviarEmergencias();
				if(llego)
				{
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
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
	public boolean EnviarEmergencias() {
		boolean llego = false;
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
		boolean aux = false;
		for (RegistroReceptor receptor : listaReceptores) {
			aux = EnviarEmergencia(receptor.ip, receptor.puerto);
			if(llego == false && aux == true) //Si llega un solo envio se toma como que llego
				llego = true;				
		}
		return llego;
	}
	
	public boolean EnviarEmergencia(String ip,int puertoReceptor)
	{
		boolean llego = false;
		try {	
	        Socket socket = new Socket(ip,puertoReceptor);
	        OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(emergencia);
            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if( in.readLine().equals("Llego"))
            	llego= true;
	        socket.close();
			} 
		catch (Exception e) {
			llego = false;
			}
		return llego;
	}

	@Override
	public Emergencia getEmergencia() {
		return this.emergencia;
	}

	@Override
	public void escucharMonitor1() {
		ServerSocket ss;
        boolean llego = false;
        String mensaje;
		try {
			ss = new ServerSocket(3001);
			while(true)
			{
				Socket socket = ss.accept(); 
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				mensaje = (String) objectInputStream.readObject();
				
				if(mensaje.equals("Disponible ServidorPrim?"))
				{
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("Llego");
				}
				socket.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	

}
