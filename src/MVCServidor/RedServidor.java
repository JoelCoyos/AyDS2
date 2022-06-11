package MVCServidor;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Emergencia;
import clasesComunes.Propiedades;
import clasesComunes.RedEnviar;
import clasesComunes.RedRecibir;
import clasesComunes.RegistroReceptor;
import clasesComunes.ServicioRed;

public class RedServidor extends Observable implements IRedServidor {
	
	private ArrayList<RegistroReceptor> ipBombero;
	private ArrayList<RegistroReceptor> ipSeguridad;
	private ArrayList<RegistroReceptor> ipMedica;
	
	private int puertoReceptor;
	private int puertoEmisor;
	private int puertoMonitor;
	
	private Emergencia emergencia;
	private RegistroReceptor registro;
	
	private boolean primario;
	
	private RedEnviar redEnviar;
	private RedRecibir redRecibir;
	
	public RedServidor()
	{
		ipBombero = new ArrayList<RegistroReceptor>();
		ipSeguridad = new ArrayList<RegistroReceptor>();
		ipMedica = new ArrayList<RegistroReceptor>();
		Propiedades propiedades = new Propiedades("configServidor.properties");
		puertoReceptor = Integer.parseInt(propiedades.getPropiedad("puertoReceptor"));
		puertoEmisor = Integer.parseInt(propiedades.getPropiedad("puertoEmisor"));
		puertoMonitor = Integer.parseInt(propiedades.getPropiedad("puertoMonitor"));
		
		
		primario = esPrimario();
		if(primario)
		{
			new Thread(){public void run(){Primario();}}.start();
		}
		else 
		{
			new Thread(){public void run(){Secundario();}}.start();
		}
		new Thread(){public void run(){RecibirMonitor(puertoMonitor);}}.start();	
	}
	
	private void CambioSecundarioPrimario()
	{
		primario = true;
		new Thread(){public void run(){Primario();}}.start();
	}
	
	
	private void Primario()
	{

		new Thread(){public void run(){RegistroReceptor();}}.start();
		new Thread(){public void run(){RecibirEmergencia();}}.start();	
		redRecibir = new RedRecibir();
		redRecibir.Conectar(4001);
		redRecibir.Escuchar();

	}
	
	private void Secundario()
	{
		boolean aux=true;
		while(aux)
		{
			System.out.println("Contactando al primario");
			registro = redEnviar.RecibirMensaje();
			System.out.println("Recibir mensaje primario");
			if(registro!=null)
			{
				AgregarReceptor(registro);
				setChanged();
				notifyObservers("Registro");				
			}
			else {
				aux = false;
			}
		}
		redEnviar = null;
		
	}
	
	private boolean esPrimario()
	{
		boolean primario;
		redEnviar = new RedEnviar();
		boolean aux = redEnviar.Conectar("localhost", 4001);
		if(aux)
			primario = false;
		else
		{
			primario = true;
			redEnviar = null;
		}
		return primario;
	}
	
	private void RecibirMonitor(int puertoMonitor)
	{
		RedRecibir monitor = new RedRecibir();
		monitor.Conectar(puertoMonitor);
		while(true)
		{
			String mensaje= monitor.Escuchar();
			if(mensaje.equals("CayoPrimario"))
			{
				CambioSecundarioPrimario();
			}
			String estado = primario==true?"Primario":"Secundario";
			monitor.EnviarRed(estado);
		}

	}

	private void RegistroReceptor() {
		
		while(true)
		{
			registro = ServicioRed.RecibirObjeto(puertoReceptor, null);
			AgregarReceptor(registro);
			setChanged();
			notifyObservers("Registro");
			redRecibir.EnviarRed(registro);			
		}
	}
	
	private void AgregarReceptor(RegistroReceptor receptor)
	{
		for (String tipo : registro.tipoEmergencia) {
			if(tipo.equals("Bomberos"))
				ipBombero.add(registro);
			else if (tipo.equals("Seguridad"))
				ipSeguridad.add(registro);
			else if (tipo.equals("Medica"))
				ipMedica.add(registro);					
		}
	}

	private void RecibirEmergencia() {
		
        boolean llego = false;
        ServerSocket ss;
		try {
			ss = new ServerSocket(puertoEmisor);
			while(true)
			{
				Socket socket = ss.accept(); 
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				emergencia = (Emergencia) objectInputStream.readObject();
				setChanged();
				notifyObservers("Emergencia");
				llego = EnviarEmergencias();
				if(llego)
				{
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("Llego");
				}
				socket.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		

	private boolean EnviarEmergencias() {
		boolean llego = false;
		ArrayList<RegistroReceptor> listaReceptores = null;
		if(this.emergencia.getTipoEmergencia().equals("Bomberos"))
		{
			listaReceptores = ipBombero;
		}
		else if(this.emergencia.getTipoEmergencia().equals("Seguridad"))
		{
			listaReceptores = ipSeguridad;
		}
		else if(this.emergencia.getTipoEmergencia().equals("Medica"))
		{
			listaReceptores = ipMedica;
		}
		String aux;
		for (RegistroReceptor receptor : listaReceptores) {
			aux = ServicioRed.EnviarObjeto(receptor.ip, receptor.puerto, emergencia);
			if(llego == false && aux.equals("Llego")) //Si llega un solo envio se toma como que llego
				llego = true;				
		}
		return llego;
	}
	
	public RegistroReceptor getRegistro()
	{
		return this.registro;
	}
	

	@Override
	public Emergencia getEmergencia() {
		return this.emergencia;
	}
}
