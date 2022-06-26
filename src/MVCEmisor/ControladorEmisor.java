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
		redEmisor = new RedEmisor();
		vistaEmisor = new VistaEmisor();
		vistaEmisor.setActionListener(this);
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
		
		String ubicacion = properties.getProperty("ubicacion");
		String tipoEmergencia = vistaEmisor.tipoEmergencia();
		Emergencia emergencia = new Emergencia(ubicacion,tipoEmergencia);
		Boolean seMostro = false;
		String[] ipPuertos = properties.getProperty("ipReceptor").split(",");
		for (String k : ipPuertos) {
			String[] ipPuerto = k.split(" ");
			String ip = ipPuerto[0];
			int puerto = Integer.parseInt(ipPuerto[1]);
			Boolean llego = redEmisor.EnviarEmergencia(emergencia,ip,puerto);
			if((seMostro == false) && (llego == true))
			{
				vistaEmisor.MostrarNotificacion("El mensaje llego correctamente");
				seMostro = true;
			}

		}
		if(!seMostro)
			vistaEmisor.MostrarNotificacion("Hubo un error en el envio de la emergencia");
	}
	
}
