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
	

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("Emergencia")) {
			this.vista.agregar_emergencia(this.redServidor.getEmergencia());
		}
		else if(arg.equals("Registro"))
		{
			vista.registro_receptor(redServidor.getRegistro());
			
		}
		
	}
	
	public void RegistroServidor()
	{
		
	}
	
	

}
