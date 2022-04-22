package MVCReceptor;

import boton_panico.Emergencia;
import MVCReceptor.VistaReceptor;

public class ControladorReceptor {
	
	private VistaReceptor vista;
	private ModeloReceptor modelo;
	
	public ControladorReceptor(VistaReceptor vista, ModeloReceptor modelo) {
		this.vista=vista;
		this.modelo=modelo;
	}
	
	public void agregarEmergencia(Emergencia emergencia) {
		
	}

}
