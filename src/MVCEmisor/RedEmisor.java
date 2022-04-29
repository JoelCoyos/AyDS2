package MVCEmisor;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import clasesComunes.Emergencia;

public class RedEmisor implements IRedEmisor {
	
	InetAddress localhost;
	

	@Override
	public boolean EnviarEmergencia(Emergencia emergencia,String ip, int puerto) 
	{
		Boolean llego = null;
		
		try {
			
			System.out.println("Conectando...");
			
	        Socket socket = new Socket(ip,puerto);
	        System.out.println("Conectado!");

	        OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(emergencia);
            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out.println("hola");
	        socket.close();
	        llego = true;
			} 
		catch (Exception e) {
			System.out.println("Algo salio mal");
			llego = false;
			}

		return llego;
	}

}
