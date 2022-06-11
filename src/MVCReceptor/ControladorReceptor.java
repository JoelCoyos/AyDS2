package MVCReceptor;


import java.io.FileInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import clasesComunes.Emergencia;


@SuppressWarnings("deprecation")
public class ControladorReceptor implements Observer{
	
	private VistaReceptor vistaReceptor;
	private RedReceptor redReceptor;
	private Clip clip;
	AudioInputStream audioInputStream;
	
	public ControladorReceptor() {
		vistaReceptor = new VistaReceptor();
		redReceptor = new RedReceptor();
		redReceptor.addObserver(this);
		Properties properties = new Properties();
		try {
			FileInputStream configFile= new FileInputStream("configReceptor.properties");
			properties.load(configFile);
			String ubicacion= properties.getProperty("ubicacion");
			this.vistaReceptor.actualizar_ubicacion(ubicacion);
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/resources/notificacion.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		redReceptor.RegistrarServidor();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(arg.equals("Emergencia"))
		{
			RecibirEmergencia();
		}
	}
	
	private void RecibirEmergencia()
	{
		clip.setMicrosecondPosition(0);
		clip.start();
		Emergencia emergencia = redReceptor.GetEmergencia();
		vistaReceptor.MostrarEmergencia(emergencia);
	}	
}
