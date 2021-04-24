package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dit.sdsw.server.ServidorCity;

public class ServicioParkingImpl extends UnicastRemoteObject implements ServicioParking {

	private static final long serialVersionUID = 2429902483238591715L;
	
	private String nombre;
	private float latitud;
	private float longitud;
	private int capacidadTotal;
	private boolean abierto;
	private int plazasOcupadas;

	public ServicioParkingImpl(String nombre, float latitud, float longitud, int capacidadTotal, 
			boolean abierto, int plazasOcupadas) throws RemoteException {
		
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.capacidadTotal = capacidadTotal;
		this.abierto = abierto;
		this.plazasOcupadas = plazasOcupadas;
	}
	
	public boolean obtenerAbierto() throws RemoteException {
		ServidorCity.getLogger().info("[AVISO:SRV_PARK] El cliente ha solicitado el valor del atributo: abierto (Nombre='" + nombre +"')");
		return abierto;
	}

	public void alertarAbierto() throws RemoteException {
		ServidorCity.getLogger().info("[AVISO:SRV_PARK] Se ha cambiado abierto a true (Nombre='" + nombre +"')");
		this.abierto = true;
		
	}

	public void alertarCerrado() throws RemoteException {
		ServidorCity.getLogger().info("[AVISO:SRV_PARK] Se ha cambiado abierto a false (Nombre='" + nombre +"')");
		this.abierto = false;
		
	}

	public void cambiarPlazasOcupadas(int plazasOcupadas) throws RemoteException {
		ServidorCity.getLogger().info("[AVISO:SRV_PARK] Se ha cambiado plazasOcupadas a " + plazasOcupadas + " (Nombre='" + nombre +"')");
		this.plazasOcupadas = plazasOcupadas;
		
	}
	
	@Override
	public String toString() {
		return (nombre + "\t" + latitud + "\t" + longitud + "\t" + capacidadTotal + "\t" + abierto + "\t" + plazasOcupadas);
	}

}
