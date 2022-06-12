package MVCMonitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Propiedades;
import clasesComunes.RedEnviar;

public class RedMonitor extends Observable implements IRedMonitor {
	
	private Servidor servidorA, servidorB;
	private String ipServidor;
	
	private boolean elPrimarioCayo;
	
	
	Propiedades propiedades;
	
	
	public RedMonitor() {
		propiedades = new Propiedades("configMonitor.properties");
		ipServidor = propiedades.getPropiedad("ipServidor");
		int puertoA = Integer.parseInt(propiedades.getPropiedad("puertoA"));
		int puertoB = Integer.parseInt(propiedades.getPropiedad("puertoB"));
		servidorA = new Servidor(puertoA);
		servidorB = new Servidor(puertoB);
		servidorA.setEstado("Caido");
		servidorB.setEstado("Caido");
		elPrimarioCayo = false;
		setChanged();
		notifyObservers("ServidorA");
		setChanged();
		notifyObservers("ServidorB");
		new Thread(){public void run(){pingServidor(puertoA);}}.start();
		new Thread(){public void run(){pingServidor(puertoB);}}.start();
	}
	
	private void pingServidor(int puerto)
	{
		RedEnviar enviarServidor = new RedEnviar();
		while(true)
		{
			//System.out.println("Tratando de conectar con " +Integer.toString(puerto));
			boolean pudo = enviarServidor.Conectar(ipServidor, puerto);
			if(pudo)
			{
				String mensajeEnviar;
				if(elPrimarioCayo==false)
					mensajeEnviar = "ping";
				else {
					mensajeEnviar ="CayoPrimario";
				}
				enviarServidor.EnviarMensaje(mensajeEnviar);
				String respuesta = enviarServidor.RecibirMensaje();	
				elPrimarioCayo=false;
				estadoServidores(puerto, respuesta, pudo);					
			}
			else {
				//System.out.println("El servidor en el puerto " + Integer.toString(puerto)+ " esta caido :(");
				if(getServidorFromPuerto(puerto).getEstado().equals("Primario"))
				{
					//System.out.println("Se cayo el primario");
					elPrimarioCayo=true;					
				}
				else {
					estadoServidores(puerto, "Caido", false);	
				}
				estadoServidores(puerto, "Caido", pudo);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			
		}
	}
	
	private void estadoServidores(int puerto,String estado,boolean disponible)
	{
		//System.out.println("Actualizando estado vista");
		Servidor servidor=null;
		String mensajeObserver=null;
		if(puerto == servidorA.getPuerto())
		{
			servidor = servidorA;	
			mensajeObserver = "ServidorA";
		}
		else if(puerto == servidorB.getPuerto())
		{
			servidor = servidorB;		
			mensajeObserver = "ServidorB";
		}
		servidor.setDisponible(disponible);
		servidor.setEstado(estado);
		setChanged();
		notifyObservers(mensajeObserver);
	}
	
	@Override
	public Servidor getServidorA()
	{
		return servidorA;
	}
	
	@Override
	public Servidor getServidorB()
	{
		return servidorB;
	}
	
	private Servidor getServidorFromPuerto(int puerto)
	{
		Servidor servidor = null;
		if(puerto==servidorA.getPuerto())
			servidor = servidorA;
		else if(puerto == servidorB.getPuerto())
			servidor = servidorB;
		return servidor;
	}
	
	
}
