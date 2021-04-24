package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioContenedor extends Remote {
	
	public void alertarLleno() throws RemoteException, InterruptedException;
	public void alertarVacio() throws RemoteException;
	public void cambiarVaciar(boolean vaciar) throws RemoteException;
	public boolean obtenerVaciar() throws RemoteException;
	public boolean obtenerAlertado() throws RemoteException;
	public void cambiarPorcentaje(float porcentaje) throws RemoteException;
}