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
import clasesComunes.Propiedades;
import clasesComunes.RegistroReceptor;
import clasesComunes.ServicioRed;

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
		Propiedades propiedades = new Propiedades("configServidor.properties");
		puertoReceptor = Integer.parseInt(propiedades.getPropiedad("puertoReceptor"));
		puertoEmisor = Integer.parseInt(propiedades.getPropiedad("puertoEmisor"));

		new Thread(){public void run(){RegistroReceptor();}}.start();
		new Thread(){public void run(){RecibirEmergencia();}}.start();
	}

	private void RegistroReceptor() {
		
		registro = ServicioRed.RecibirObjeto(puertoReceptor, null);
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

	private void RecibirEmergencia() {
		
        boolean llego = false;
        ServerSocket ss;
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
		

	private boolean EnviarEmergencias() {
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
		String aux;
		for (RegistroReceptor receptor : listaReceptores) {
			aux = ServicioRed.EnviarObjeto(receptor.ip, receptor.puerto, emergencia);
			if(llego == false && aux.equals("Llego")) //Si llega un solo envio se toma como que llego
				llego = true;				
		}
		return llego;
	}
	

	@Override
	public Emergencia getEmergencia() {
		return this.emergencia;
	}
	
	
	

}
