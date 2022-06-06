package MVCMonitor;

import java.util.Observable;
import java.util.Observer;

public class ControladorMonitor implements Observer {
	
	private VistaMonitor vista;
	private RedMonitor redMonitor;

	public ControladorMonitor() {
		this.vista= new VistaMonitor();
		this.redMonitor= new RedMonitor();
		this.redMonitor.addObserver(this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("Disponible Primario")) {
			this.vista.iniciarMonitor();
		}
		else if(arg.equals("No Disponible Primario")) {
			this.vista.actualizarMonitor();
		}
		
	}
	
	
	
	

}
