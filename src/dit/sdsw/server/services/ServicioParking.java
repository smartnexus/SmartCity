package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface ServicioParking extends Remote {
	void alertarAbierto() throws RemoteException;
	void alertarCerrado() throws RemoteException;
	void cambiarPlazasOcupadas(int plazasOcupadas) throws RemoteException;
}
