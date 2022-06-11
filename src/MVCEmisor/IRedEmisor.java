package MVCEmisor;

import clasesComunes.Emergencia;

public interface IRedEmisor {
	
	public void EnviarEmergencia(Emergencia emergencia,String ip, int puerto);

}
