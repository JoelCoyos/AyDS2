package boton_panico;

public class Emergencia {
	
	private String ubicacion;
	private TipoEmergencia tipoEmergencia;
	
	public Emergencia(String ubicacion,TipoEmergencia tipo) {
		this.tipoEmergencia=tipo;
		this.ubicacion=ubicacion;
	}
	
	public String getUbicacion() {
		return this.ubicacion;
	}
	
	public String getTipoEmergencia() {
		String emergencia="";
		switch(this.tipoEmergencia) {
			case medica: emergencia="Asistencia Medica";
			break;
			case incendios: emergencia="Bomberos";
			break;
			case seguridad: emergencia="Personal de seguridad";
			break;
		}
		return emergencia;
	}
	
}