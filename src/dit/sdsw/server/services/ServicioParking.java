package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioParking extends Remote {
	boolean obtenerAbierto() throws RemoteException;
	void alertarAbierto() throws RemoteException;
	void alertarCerrado() throws RemoteException;
	void cambiarPlazasOcupadas(int plazasOcupadas) throws RemoteException;
}
