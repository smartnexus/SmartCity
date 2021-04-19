package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
		System.out.println("[AVISO:SRV_PARK] El cliente ha solicitado el valor del atributo: abierto (Nombre='" + nombre +"')");
		return abierto;
	}

	public void alertarAbierto() throws RemoteException {
		System.out.println("[AVISO:SRV_PARK] Se ha cambiado abierto a true (Nombre='" + nombre +"')");
		this.abierto = true;
		
	}

	public void alertarCerrado() throws RemoteException {
		System.out.println("[AVISO:SRV_PARK] Se ha cambiado abierto a false (Nombre='" + nombre +"')");
		this.abierto = false;
		
	}

	public void cambiarPlazasOcupadas(int plazasOcupadas) throws RemoteException {
		System.out.println("[AVISO:SRV_PARK] Se ha cambiado plazasOcupadas a " + plazasOcupadas + " (Nombre='" + nombre +"')");
		this.plazasOcupadas = plazasOcupadas;
		
	}

}
