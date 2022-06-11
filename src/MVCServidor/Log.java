package MVCServidor;

import java.io.Serializable;

public class Log implements Serializable {
	String fechaHora;
	String mensaje;
	
	public Log(String fechaHora, String mensaje)
	{
		this.fechaHora = fechaHora;
		this.mensaje = mensaje;
	}

}
