package dit.sdsw.server.services;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

public class ServicioFarolaImpl extends UnicastRemoteObject implements ServicioFarola{
	
	private static final long serialVersionUID = -2410638850162365494L;
	
	UUID id;
    float latitud;
    float longitud;
    String color;
    boolean estado;//false representa apagado y true encendido
    boolean estado_ant = false; //Entendemos que las farolas inicialmente están apagadas
    
	public ServicioFarolaImpl(float latitud, float longitud, String color, boolean estado) throws RemoteException {
		this.latitud = latitud;
		this.longitud = longitud;
		this.color = color;
		this.estado = estado;
		id = UUID.randomUUID();
	}
	
	public void alertarCambioEstado() throws RemoteException {
		System.out.println("[AVISO:SRV_FAR] La farola" + id +" ha cambiado de estado");
		estado_ant = estado;
	}

	public boolean isEstado() throws RemoteException{
		return estado;
	}

	public void setEstado(boolean estado) throws RemoteException{
		this.estado = estado;
	}

	public boolean isEstado_ant() throws RemoteException {
		return estado_ant;
	}

	public void setEstado_ant(boolean estado_ant) {
		this.estado_ant = estado_ant;
	}
	

}
