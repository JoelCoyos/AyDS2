package clasesComunes;

import java.io.Serializable;

public class Emergencia implements Serializable {
	
	public String ubicacion;
	public String tipoEmergencia;
	public String fecha;
	public String hora;
	
	public Emergencia(String ubicacion,String tipo) {
		this.tipoEmergencia=tipo;
		this.ubicacion=ubicacion;
		this.fecha = "22/04";
		this.hora = "03:36";
	}
	
	public String getUbicacion() {
		return this.ubicacion;
	}

	
}