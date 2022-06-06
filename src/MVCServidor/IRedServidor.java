package MVCServidor;

import clasesComunes.Emergencia;

public interface IRedServidor {
	
	public void RegistroReceptor();
	public void RecibirEmergencia();
	public boolean EnviarEmergencias();
	public boolean EnviarEmergencia(String ip,int puerto);
	public Emergencia getEmergencia();
	public void escucharMonitor();
	
}
