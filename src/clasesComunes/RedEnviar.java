package clasesComunes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
			socket = new Socket(ip,puerto);
			pudo = true;
		} catch (IOException e) {
			
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
			try {
				System.out.println("error");
				socket.close();
			} catch (IOException e1) {
				System.out.println("error");
				e1.printStackTrace();
			}
		}
		return objeto;
	}
	
	public boolean estaActivo()
	{
		return socket.isConnected();
	}

}
