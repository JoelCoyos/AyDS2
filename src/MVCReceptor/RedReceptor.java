package MVCReceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

import clasesComunes.Emergencia;
import clasesComunes.Propiedades;
import clasesComunes.RegistroReceptor;
import clasesComunes.ServicioRed;

@SuppressWarnings("deprecation")
public class RedReceptor extends Observable implements IRedReceptor {
	
	private static Emergencia emergencia;
	Propiedades propiedades;
	
	public RedReceptor() {
		propiedades = new Propiedades("configReceptor.properties");
		new Thread(){public void run(){Escuchar();}}.start();
	}
	
	public void Escuchar()
	{
			int puerto = Integer.parseInt(propiedades.getPropiedad("puerto"));
			while(true)
			{
				emergencia = (Emergencia) ServicioRed.RecibirObjeto(puerto, "Llego");
				setChanged();
				notifyObservers("Emergencia");
			}
	}
	
	public void RegistrarServidor()
	{
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			int puertoReceptor = Integer.parseInt(propiedades.getPropiedad("puerto"));
			int puertoServidor = Integer.parseInt(propiedades.getPropiedad("puertoServidor"));
			String[] tipoEmergencia = propiedades.getPropiedad("tipoEmergencia").split(",");
			String ipServidor = propiedades.getPropiedad("ipServidor");
			String ip = inetAddress.getHostAddress();
			RegistroReceptor registro = new RegistroReceptor(ip,tipoEmergencia,puertoReceptor);
			ServicioRed.EnviarObjeto(ipServidor, puertoServidor, registro);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Emergencia GetEmergencia() {
		return emergencia;
	}

}
