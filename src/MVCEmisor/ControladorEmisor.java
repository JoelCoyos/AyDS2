package MVCEmisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import boton_panico.TipoEmergencia;

@SuppressWarnings("deprecation")
public class ControladorEmisor implements ActionListener,Observer
{
	VistaEmisor uiEmisor;
	public ControladorEmisor()
	{
		uiEmisor = new VistaEmisor();
		uiEmisor.setActionListener(this);
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
			System.out.print("Emergencia " + uiEmisor.tipoEmergencia()+"\n");
		}
		
	}
}
