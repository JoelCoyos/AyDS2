package MVCServidor;

import java.util.ArrayList;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public interface IRedServidor {
	
	public Emergencia getEmergencia();
	public RegistroReceptor getRegistro();
	public ArrayList<Log> getLogs();
	public void setLogs(ArrayList<Log> logs);
	
}
