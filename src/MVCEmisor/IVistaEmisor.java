package MVCEmisor;


public interface IVistaEmisor {
	
	public String tipoEmergencia();
	public void MostrarNotificacion(String mensaje);
	public void actualizar_ubicacion(String ubicacion);
	
}
