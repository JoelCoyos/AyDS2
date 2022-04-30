package MVCReceptor;


import java.io.File;
import java.util.Observable;
import java.util.Observer;

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
		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/resources/notificacion.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println("Llego");
		if(arg.equals("Emergencia"))
		{
			RecibirEmergencia();
		}
	}
	
	public void RecibirEmergencia()
	{
		clip.start();
		Emergencia emergencia = redReceptor.GetEmergencia();
		vistaReceptor.agregarEmergencia(emergencia.getFechaHora(), emergencia.tipoEmergencia, emergencia.ubicacion);
	}	
}
