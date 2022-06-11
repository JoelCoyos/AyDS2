package clasesComunes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class RedRecibir {
	
	ServerSocket serverSocket;
	RedEnviar redEnviar;
	
	public boolean Conectar(int puerto)
	{
		try {
			serverSocket = new ServerSocket(puerto);
			return true;
		} catch (IOException e) {
			System.out.println("error conectar ss");
			return false;
		}
	}
	
	public<T extends Serializable> T Escuchar()
	{
		T objeto=null;
		try {
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			objeto = (T)objectInputStream.readObject();
			redEnviar = new RedEnviar(socket);
		} catch (Exception e) {
			
		}
		return objeto;
	}
	
	public <T extends Serializable> T RecibirMensaje()
	{
		return redEnviar.RecibirMensaje();
	}
	
	public<T extends Serializable> void EnviarRed(T objeto)
	{
		if(redEnviar!=null)
			redEnviar.EnviarMensaje(objeto);
	}
	
	public void Cerrar()
	{
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
