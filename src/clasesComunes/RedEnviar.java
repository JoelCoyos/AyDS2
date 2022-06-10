package clasesComunes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class RedEnviar {
	
	Socket socket;
	
	public RedEnviar() {};
	
	public RedEnviar(Socket socket)
	{
		this.socket = socket;
	}
	
	public boolean Conectar(String ip, int puerto)
	{
		boolean pudo=false;
		try {
			System.out.println("Tratando de conectarse...");
			socket = new Socket(ip,puerto);
			System.out.println("Se pudo conectar!");
			pudo = true;
		} catch (IOException e) {
			System.out.println("error");
		}
		return pudo;
	}
	
	public<T extends Serializable> boolean EnviarMensaje(T objeto)
	{
		boolean pudo=false;
		if(socket!=null)
		{
			try {
				OutputStream outputStream = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(objeto);
				pudo = true;
			} catch (IOException e) {
			}
		}
		return pudo;
	}
	
	public <T extends Serializable> T RecibirMensaje()
	{
		T objeto = null;
		try {
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			objeto = (T)objectInputStream.readObject();
		} catch (Exception e) {
			
		}
		return objeto;
	}

}
