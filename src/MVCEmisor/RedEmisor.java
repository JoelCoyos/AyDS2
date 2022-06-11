package MVCEmisor;

import clasesComunes.Emergencia;
import clasesComunes.ServicioRed;

public class RedEmisor implements IRedEmisor {

	@Override
	public boolean EnviarEmergencia(Emergencia emergencia,String ip, int puerto) 
	{
		boolean llego = false;
		String respuesta = ServicioRed.EnviarObjeto(ip, puerto, emergencia);
		int i=0;
		while(i<3 && !llego) {
			respuesta = ServicioRed.EnviarObjeto(ip, puerto, emergencia);
			if(respuesta!=null)
				llego = respuesta.equals("Llego");
			else {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		return llego;
	}

}
