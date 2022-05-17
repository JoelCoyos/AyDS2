package MVCServidor;

import clasesComunes.Emergencia;

public interface IRedServidor {
	
	public void RegistroReceptor();
	public void RecibirEmergencia();
	public void EnviarEmergencia();
	public Emergencia getEmergencia();
	
}
