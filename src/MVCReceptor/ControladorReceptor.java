package MVCReceptor;

import boton_panico.Emergencia;
import boton_panico.RedEmisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class ControladorReceptor implements ActionListener,Observer{
	
	private VistaReceptor vistaReceptor;
	private RedReceptor redReceptor;
	
	public ControladorReceptor() {
		vistaReceptor = new VistaReceptor();
		redReceptor = new RedReceptor();
		redReceptor.addObserver(this);
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
		Emergencia emergencia = redReceptor.GetEmergencia();
		vistaReceptor.agregarEmergencia(emergencia.fecha, emergencia.tipoEmergencia, emergencia.ubicacion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}
	
}
