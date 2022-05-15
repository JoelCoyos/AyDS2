package MVCServidor;

import java.util.Observable;
import java.util.Observer;

public class ControladorServidor implements Observer {
	
	private IVistaServidor vista;
	private RedServidor redServidor;
	
	public ControladorServidor(){
		this.vista= new VistaServidor();
		this.redServidor=new RedServidor();
		this.redServidor.addObserver(this);
		
	}
	
	
	//Recibe la emergencia de parte del emisor
	public void Recibir_Emergencia() {
		
		
	}
	
	//Envia la emergencia a un receptor
	public void Enviar_Emergencia() {
		
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
