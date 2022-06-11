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

import clasesComunes.Propiedades;
import clasesComunes.RedEnviar;

public class RedMonitor extends Observable implements IRedMonitor {
	
	private Servidor servidorA, servidorB;
	private String ipServidor;
	
	
	Propiedades propiedades;
	
	
	public RedMonitor() {
		propiedades = new Propiedades("configMonitor.properties");
		ipServidor = propiedades.getPropiedad("ipServidor");
		int puertoA = Integer.parseInt(propiedades.getPropiedad("puertoA"));
		int puertoB = Integer.parseInt(propiedades.getPropiedad("puertoB"));
		servidorA = new Servidor(puertoA);
		servidorB = new Servidor(puertoB);
		new Thread(){public void run(){pingServidor(puertoA);}}.start();
		new Thread(){public void run(){pingServidor(puertoB);}}.start();
	}
	
	private void pingServidor(int puerto)
	{
		RedEnviar enviarServidor = new RedEnviar();
		while(true)
		{
			boolean pudo = enviarServidor.Conectar(ipServidor, puerto);
			if(pudo)
			{
				enviarServidor.EnviarMensaje("ping");
				String respuesta = enviarServidor.RecibirMensaje();			
				System.out.println(respuesta);
				estadoServidores(puerto, respuesta, pudo);
			}
			else {
				System.out.println("El servidor en el puerto " + Integer.toString(puerto)+ " esta caido :(");
				estadoServidores(puerto, "", pudo);
				
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			
		}
	}
	
	private void estadoServidores(int puerto,String estado,boolean disponible)
	{
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
	
	public Servidor getServidorA()
	{
		return servidorA;
	}
	
	public Servidor getServidorB()
	{
		return servidorB;
	}
	
	
}
