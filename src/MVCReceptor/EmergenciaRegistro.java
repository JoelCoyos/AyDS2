package MVCReceptor;

import boton_panico.Emergencia;

public class EmergenciaRegistro {
	private String fechaHora;
	private Emergencia emergencia;
	
	public EmergenciaRegistro(String fechaHora,Emergencia e) {
		this.emergencia=e;
		this.fechaHora=fechaHora;
	}

	public String getFechaHora() {
		return fechaHora;
	}
	
	public String getUbicacion() {
		return this.emergencia.getUbicacion();
	}
	
}
