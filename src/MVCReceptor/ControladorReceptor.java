package MVCReceptor;

import boton_panico.Emergencia;

public class ControladorReceptor {
	
	private UIReceptor vista;
	private ModeloReceptor modelo;
	
	public ControladorReceptor(UIReceptor vista, ModeloReceptor modelo) {
		this.vista=vista;
		this.modelo=modelo;
	}
	
	public void agregarEmergencia(Emergencia emergencia) {
		
	}

}
