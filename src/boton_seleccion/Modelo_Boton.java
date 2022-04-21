package boton_seleccion;

public class Modelo_Boton {
	
	private String id; // Identificador(Tipo de emergencia)
	private boolean activado; // Esta cliqueado o no
	
	public Modelo_Boton(String id) {
		this.activado=false;
	}
	
	public boolean estaActivado() {
		return this.activado;
	}
	
	public void activar() {
		this.activado=true;
	}
	
	public void desactivar() {
		this.activado=false;
	}
	
	public String getId() {
		return this.id;
	}
}
