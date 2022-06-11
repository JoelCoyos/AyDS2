package MVCEmisor;

import java.util.Observable;

import clasesComunes.Emergencia;
import clasesComunes.ServicioRed;

public class RedEmisor extends Observable implements IRedEmisor {
	
	public boolean llego;
	

	@Override
	public void EnviarEmergencia(Emergencia emergencia,String ip, int puerto) 
	{
		llego = false;
		String respuesta;
		int i=0;
		while(i<5 && !llego) {
			System.out.println("Tratando de enviar...");
			respuesta = ServicioRed.EnviarObjeto(ip, puerto, emergencia);
			if(respuesta!=null && respuesta.equals("Llego"))
				llego = true;
			else {
				System.out.println("Reintentando...");
				i++;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
			}
		}
		setChanged();
		notifyObservers(llego);	
	}

}
