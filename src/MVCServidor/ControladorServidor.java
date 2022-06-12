package MVCServidor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import clasesComunes.Emergencia;
import clasesComunes.RegistroReceptor;

public class ControladorServidor implements Observer {
	
	private IVistaServidor vista;
	private RedServidor redServidor;
	private ArrayList<Log> logs;
	
	public ControladorServidor(){
		this.vista= new VistaServidor();
		this.redServidor=new RedServidor();
		this.redServidor.addObserver(this);
		logs = new ArrayList<Log>();
		redServidor.setLogs(logs);
		
	}
	

	@Override
	public void update(Observable o, Object arg) {
		Log log = null;
		String mensaje;
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fechaHora=dtf.format(LocalDateTime.now());
		if (arg.equals("Emergencia")) {
			Emergencia emergencia = redServidor.getEmergencia();
			mensaje =  "Se ha recibido una emergencia "+emergencia.getTipoEmergencia()+" desde "+emergencia.getUbicacion();
			log = new Log(emergencia.getFechaHora(), mensaje);
			logs.add(log);
			this.vista.AgregarLog(log);
		}
		else if(arg.equals("EnvioEmergencia"))
		{
			RegistroReceptor receptor = redServidor.getRegistro();
			Emergencia emergencia = redServidor.getEmergencia();
			mensaje = "Se envio una emergencia al receptor en el puerto " + receptor.puerto +" con ip" + receptor.ip +  " de tipo " + emergencia.getTipoEmergencia();
			log = new Log(fechaHora, mensaje);
			logs.add(log);
			this.vista.AgregarLog(log);
			
		}
		else if(arg.equals("Registro"))
		{
			RegistroReceptor receptor = redServidor.getRegistro();
			mensaje = "Se ha agregado el receptor con ip " + receptor.ip + " en el puerto " + receptor.puerto + " de tipo ";
			for (String tipo : receptor.tipoEmergencia) {
				mensaje+=tipo + " ";
			}
			log = new Log(fechaHora, mensaje);
			logs.add(log);
			vista.AgregarLog(log);
			
		}
		else if(arg.equals("Sincronizar"))
		{
			this.logs = redServidor.getLogs();
			for (Log logSinc : logs) {
				vista.AgregarLog(logSinc);
			}
		}
		else
		{
			Log logSinc = (Log)arg;
			logs.add(logSinc);
			vista.AgregarLog(logSinc);
		}
			
	}
	
}
