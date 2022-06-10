package MVCEmisor;

import clasesComunes.Emergencia;
import clasesComunes.ServicioRed;

public class RedEmisor implements IRedEmisor {

	@Override
	public boolean EnviarEmergencia(Emergencia emergencia,String ip, int puerto) 
	{
		boolean llego = false;
		String respuesta = ServicioRed.EnviarObjeto(ip, puerto, emergencia);
		if(respuesta!=null)
			llego = respuesta.equals("Llego");
		return llego;
	}

}
