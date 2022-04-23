package MVCEmisor;

import boton_panico.TipoEmergencia;

public interface IVistaEmisor {
	
	public String tipoEmergencia();
	public void MostrarNotificacion(String mensaje);
	
}
