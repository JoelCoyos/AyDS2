package MVCEmisor;

import boton_panico.TipoEmergencia;

public interface IVistaEmisor {
	
	public TipoEmergencia tipoEmergencia();
	public void EnviarEmergencia();
	
}
