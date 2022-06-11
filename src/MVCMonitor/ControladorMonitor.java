package MVCMonitor;

import java.io.FileInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

public class ControladorMonitor implements Observer {
	
	private VistaMonitor vista;
	private RedMonitor redMonitor;
	private int detected;

	public ControladorMonitor() {
		this.vista= new VistaMonitor();
		this.detected=0;
		this.redMonitor= new RedMonitor();
		this.redMonitor.addObserver(this);
		try {
			Properties properties = new Properties();
			FileInputStream configFile= new FileInputStream("configMonitor.properties");
			properties.load(configFile);
			String ubicacion=properties.getProperty("ubicacion");
			this.vista.actualizar_ubicacion(ubicacion);
		}
		catch(Exception e) {
			System.out.println("Error al leer archivo configMonitor.properties");
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("ServidorA"))
			this.vista.actualizarServidorA(redMonitor.getServidorA());
		if(arg.equals("ServidorB"))
			this.vista.actualizarServidorB(redMonitor.getServidorB());
		
	}
	
	
	
	

}
