package clasesComunes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Emergencia implements Serializable {
	
	public String ubicacion;
	public String tipoEmergencia;
	public String fechaHora;
	
	public Emergencia(String ubicacion,String tipo) {
		this.tipoEmergencia=tipo;
		this.ubicacion=ubicacion;
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		this.fechaHora=dtf.format(LocalDateTime.now());
		
	}
	
	public String getUbicacion() {
		return this.ubicacion;
	}
	
	public String getFechaHora() {
		return this.fechaHora;
	}

	
}