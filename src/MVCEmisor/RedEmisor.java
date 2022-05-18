package MVCEmisor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		boolean llego = false;
		
		try {	
	        Socket socket = new Socket(ip,puerto);

	        OutputStream outputStream = socket.getOutputStream();
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	        objectOutputStream.writeObject(emergencia);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if( in.readLine().equals("Llego"))
            	llego= true;
	        socket.close();
			} 
		catch (Exception e) {
			
			}
		return llego;
	}

}
