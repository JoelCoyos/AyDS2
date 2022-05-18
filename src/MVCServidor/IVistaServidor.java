package MVCServidor;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public interface IVistaServidor {
	public void agregar_emergencia(Emergencia emergencia);
	public void registro_receptor(RegistroReceptor receptor);
	
}
