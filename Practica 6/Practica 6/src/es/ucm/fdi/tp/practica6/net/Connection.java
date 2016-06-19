package es.ucm.fdi.tp.practica6.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Recoge los objetos e instrucciones necesarias para mantener una conexion con un dispositivo
 */
public class Connection {
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	/**
	 * Crea una nueva conexión
	 * @param s Socket de la conexion
	 * @throws IOException
	 */
	public Connection (Socket s) throws IOException {
		this.s = s;
		this.out = new ObjectOutputStream(s.getOutputStream());
		this.in = new ObjectInputStream(s.getInputStream());
	}
	
	/**
	 * Envía un objeto
	 * @param o Objeto a enviar
	 * @throws IOException
	 */
	public void sendObject (Object o) throws IOException {
		out.writeObject(o);
		out.flush();
		out.reset();
	}
	
	/**
	 * Recibe un objeto
	 * @return devolvemos el objeto recibido
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object getObject () throws ClassNotFoundException, IOException {
		return in.readObject();
	}
	
	/**
	 * Cierra el Socket s
	 * @throws IOException
	 */
	public void close() throws IOException {
		s.close();
	}
}
