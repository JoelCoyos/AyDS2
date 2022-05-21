package clasesComunes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroReceptor implements Serializable {
	public String ip;
	public String[] tipoEmergencia;
	public int puerto;
	public String fechaHora;
	
	public RegistroReceptor(String ip,String[] tipoEmergencia,int puerto)
	{
		this.ip = ip;
		this.tipoEmergencia = tipoEmergencia;
		this.puerto = puerto;
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		this.fechaHora=dtf.format(LocalDateTime.now());
	}
}
