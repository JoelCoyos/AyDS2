package MVCServidor;

import clasesComunes.Emergencia;

public interface IRedServidor {
	
	public void RegistroReceptor();
	public void RecibirEmergencia();
	public void EnviarEmergencias();
	public void EnviarEmergencia(String ip,int puerto);
	public Emergencia getEmergencia();
	
}
