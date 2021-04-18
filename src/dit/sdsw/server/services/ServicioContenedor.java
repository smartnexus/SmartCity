package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioContenedor extends Remote {
	
	public void alertarLleno() throws RemoteException;

}