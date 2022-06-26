package MVCReceptor;

import clasesComunes.Emergencia;

public interface IVistaReceptor {
	
	public void MostrarEmergencia(Emergencia emergencia);
	public void actualizar_ubicacionPuerto(String ubicacion, int puerto);

}
