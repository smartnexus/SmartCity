package dit.sdsw.server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioFarola extends Remote{
	public void alertarCambioEstado() throws RemoteException;
	public void setEstado(boolean estado) throws RemoteException;
	public boolean isEstado_ant() throws RemoteException;
}
