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

public class ServicioRed {
	
	public static<T extends Serializable> String EnviarObjeto(String ip, int puerto,T objeto)
	{
		String respuesta="";
		Socket socket;
		try {
			socket = new Socket(ip, puerto);
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(objeto);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			respuesta = in.readLine();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(respuesta);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objeto;
	}
	

}
