package MVCReceptor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Properties;

import clasesComunes.Emergencia;

@SuppressWarnings("deprecation")
public class RedReceptor extends Observable implements IRedReceptor {
	
	private static Emergencia emergencia;
	
	public RedReceptor() {
		new Thread(){public void run(){Escuchar();}}.start();
	}
	
	public void Escuchar()
	{
            ServerSocket ss;
            
			try {
				ss = new ServerSocket(1234);
				while(true)
				{
					Socket socket = ss.accept(); 
					System.out.println("Se conecto!");
					
					InputStream inputStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					emergencia = (Emergencia) objectInputStream.readObject();
					//PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					//String msg = in.readLine();
					//System.out.println(msg);
					System.out.println("Mensaje recibido");
					setChanged();
					notifyObservers("Emergencia");
					socket.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public Emergencia GetEmergencia() {
		return emergencia;
	}

}
