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
	
	public void EnviarEmergencia()
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
		String tipoIp = null;
		if(tipoEmergencia.equals("Seguridad"))
			tipoIp = "ipSeguridad";
		else if(tipoEmergencia.equals("Bomberos"))
			tipoIp = "ipBomberos";
		else if(tipoEmergencia.equals("Medica"))
			tipoIp = "ipMedica";
		String[] ipStrings = properties.getProperty(tipoIp).split("/");
		String ubicacion = properties.getProperty("ubicacion");
		
		Emergencia emergencia = new Emergencia(ubicacion,tipoEmergencia);
		Boolean huboError = false;
		for (String ip : ipStrings) {
			Boolean llego = redEmisor.EnviarEmergencia(emergencia,ip,puerto);
			if(llego==false)
				huboError = true;
				
		}
		if(!huboError)
			vistaEmisor.MostrarNotificacion("El mensaje llego correctamente");
		else
			vistaEmisor.MostrarNotificacion("Hubo un error en el envio de la emergencia");
	}
	
}
