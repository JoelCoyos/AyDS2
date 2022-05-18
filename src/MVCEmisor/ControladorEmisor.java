package MVCEmisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import clasesComunes.Emergencia;

@SuppressWarnings("deprecation")
public class ControladorEmisor implements ActionListener,Observer
{
	private VistaEmisor vistaEmisor;
	private RedEmisor redEmisor;
	public ControladorEmisor()
	{
		vistaEmisor = new VistaEmisor();
		vistaEmisor.setActionListener(this);
		redEmisor = new RedEmisor();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == "Enviar Emergencia")
		{
			EnviarEmergencia();
		}
	}
	
	private void EnviarEmergencia()
	{
		
		Properties properties = new Properties();
		FileInputStream configFile;
		try {
			configFile = new FileInputStream("configEmisor.properties");
			properties.load(configFile);
		} catch (Exception e) {
			vistaEmisor.MostrarNotificacion("Error en el archivo de configuracion");
		}
		int puerto = Integer.parseInt(properties.getProperty("puerto"));
		String tipoEmergencia = vistaEmisor.tipoEmergencia();
		String ip = properties.getProperty("ipServidor");
		String ubicacion = properties.getProperty("ubicacion");
		
		Emergencia emergencia = new Emergencia(ubicacion,tipoEmergencia);
		Boolean llego = redEmisor.EnviarEmergencia(emergencia,ip,puerto);
				
		if(llego)
			vistaEmisor.MostrarNotificacion("El mensaje llego correctamente");
		else
			vistaEmisor.MostrarNotificacion("Hubo un error en el envio de la emergencia");
	}
	
}
