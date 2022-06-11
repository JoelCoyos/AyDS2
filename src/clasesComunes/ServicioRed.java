package clasesComunes;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ServicioRed {
	
	public static<T extends Serializable> String EnviarObjeto(String ip, int puerto,T objeto)
	{
		String respuesta=null;
		Socket socket;
		try {
			socket = new Socket(ip, puerto);
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(objeto);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			respuesta = (String) in.readObject();
			socket.close();
		} catch (Exception e) {
			
		}
		return respuesta;
	}
	
	public static<T extends Serializable> T RecibirObjeto(int puerto,String respuesta)
	{
		ServerSocket ss;
		T objeto = null;
		try {
			ss = new ServerSocket(puerto);
			Socket socket = ss.accept();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			objeto = (T)objectInputStream.readObject();
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(respuesta);
			ss.close();
		} catch (Exception e) {
			
		}
		return objeto;
	}
	

}
