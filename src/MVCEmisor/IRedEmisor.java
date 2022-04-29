package MVCEmisor;

import clasesComunes.Emergencia;

public interface IRedEmisor {
	
	public boolean EnviarEmergencia(Emergencia emergencia,String ip, int puerto);

}
