package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioContenedor extends Remote {
	
	public void alertarLleno() throws RemoteException;
	public float obtenerId() throws RemoteException;
	public float obtenerLatitud() throws RemoteException;
	public float obtenerLongitud() throws RemoteException;
	public int obtenerTipo() throws RemoteException;
	public float obtenerPorcentaje() throws RemoteException;
	public boolean isVacio() throws RemoteException;
	public void cambiarVacio(boolean vacio) throws RemoteException;
	public void cambiarPorcentaje(float porcentaje) throws RemoteException;
}