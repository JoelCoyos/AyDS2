package MVCServidor;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public interface IVistaServidor {
	public void AgregarLog(Log log);
	public void CambiarEstado(String estado);
}
