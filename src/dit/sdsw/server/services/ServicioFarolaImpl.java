package dit.sdsw.server.services;
import java.util.*;

import dit.sdsw.server.ServidorCity;

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
		ServidorCity.getLogger().info("[AVISO:SRV_FAR] La farola " + id.toString().substring(0,5) +" ha cambiado de estado");
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
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return (id.toString().substring(0, 5) + "," + latitud + "," + longitud + "," + color + "," + estado);
	}
	

}
