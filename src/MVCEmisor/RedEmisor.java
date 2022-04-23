package MVCEmisor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import boton_panico.Emergencia;

public class RedEmisor implements IRedEmisor {
	
	InetAddress localhost;
	
	public RedEmisor()
	{
		
	}


	@Override
	public boolean EnviarEmergencia(Emergencia emergencia) 
	{
		Boolean llego = null;
		try {
			System.out.println("Conectando...");
	        Socket socket = new Socket("localhost", 1234);
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
			llego = false;
			}

		return llego;
	}

}
