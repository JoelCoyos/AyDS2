package MVCServidor;

import java.io.Serializable;
import java.util.ArrayList;

import clasesComunes.RegistroReceptor;

public class Sincronizacion implements Serializable {
	
	private ArrayList<RegistroReceptor> ipBombero;
	private ArrayList<RegistroReceptor> ipSeguridad;
	private ArrayList<RegistroReceptor> ipMedica;
	
	private ArrayList<Log> logs;

	public Sincronizacion(ArrayList<RegistroReceptor> ipBombero, ArrayList<RegistroReceptor> ipSeguridad,
			ArrayList<RegistroReceptor> ipMedica, ArrayList<Log> logs) {
		this.ipBombero = ipBombero;
		this.ipSeguridad = ipSeguridad;
		this.ipMedica = ipMedica;
		this.logs = logs;
	}

	public ArrayList<RegistroReceptor> getIpBombero() {
		return ipBombero;
	}

	public ArrayList<RegistroReceptor> getIpSeguridad() {
		return ipSeguridad;
	}

	public ArrayList<RegistroReceptor> getIpMedica() {
		return ipMedica;
	}

	public ArrayList<Log> getLogs() {
		return logs;
	}
	
	
	
	

}
