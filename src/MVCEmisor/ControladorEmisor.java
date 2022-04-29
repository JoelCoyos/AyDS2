package MVCEmisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import clasesComunes.Emergencia;
import clasesComunes.TipoEmergencia;

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
		Emergencia emergencia = new Emergencia("Aula 101", vistaEmisor.tipoEmergencia());
		Boolean llego = redEmisor.EnviarEmergencia(emergencia);
		if(llego)
			vistaEmisor.MostrarNotificacion("El mensaje llego correctamente");
	}
	
}
