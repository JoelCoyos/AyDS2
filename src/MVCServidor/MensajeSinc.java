package MVCServidor;

import java.io.Serializable;

public class MensajeSinc implements Serializable{
	
	private String tipo;
	private Serializable mensaje;
	
	public String getTipo() {
		return tipo;
	}

	public Serializable getMensaje() {
		return mensaje;
	}

	public MensajeSinc(String tipo,Serializable mensaje) {
		this.tipo = tipo;
		this.mensaje = mensaje;
	}

}
