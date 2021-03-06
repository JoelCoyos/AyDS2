package clasesComunes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Emergencia implements Serializable {
	
	private String ubicacion;
	private String tipoEmergencia;
	private String fechaHora;
	
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
	
	public String getTipoEmergencia()
	{
		return this.tipoEmergencia;
	}

	
}