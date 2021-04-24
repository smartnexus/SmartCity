package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import dit.sdsw.server.ServidorCity;

public class ServicioContenedorImpl extends UnicastRemoteObject implements ServicioContenedor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9114201825161312160L;
	private UUID id;
	private float latitud;
	private float longitud;
	private int tipo; //tipo: 1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico
	private float porcentaje;
	private boolean vaciar;
	private boolean alertado;
	

	public ServicioContenedorImpl(float latitud, float longitud, int tipo) throws RemoteException {
		this.id = UUID.randomUUID();
		this.latitud = latitud;
		this.longitud = longitud;
		this.tipo = tipo;
		this.porcentaje = 0; //Porcentaje lleno
		this.vaciar = false; 
		this.alertado = false;
	}

	@Override
	public void alertarLleno() throws RemoteException, InterruptedException{
		// TODO: Cómo vamos a avisar. DUDAS aquí
		ServidorCity.getLogger().info("[AVISO:SRV_CONT] Contenedor lleno  (ID = " + id.toString().substring(0,5) + ")");
		System.out.println("**************** Vaciando contenedor (ID = " + id.toString().substring(0,5) + ")");
		this.vaciar = true;
		Thread.sleep(5000);  //Simula proceso en el que se van a recoger los residus	
	}
	
	@Override
	public void alertarVacio() throws RemoteException {
		ServidorCity.getLogger().info("[AVISO:SRV_CONT] Contenedor vaciado (ID=" + id.toString().substring(0,5) + ")");
		this.vaciar = false;		
	}
	
	@Override
	public void cambiarPorcentaje(float porcentaje) throws RemoteException {
		this.porcentaje = porcentaje;		
	}
	
	@Override
	public void cambiarVaciar(boolean vaciar) throws RemoteException {
		this.vaciar = vaciar;
		this.alertado = false;
	}
	
	@Override
	public boolean obtenerVaciar() throws RemoteException {
		return this.vaciar;
	}
	
	public float getPorcentaje() {
		return this.porcentaje;
	}
		
	public UUID getId() {
		return this.id;
	}

	public float getLatitud() {
		return this.latitud;
	}
	
	public float getLongitud() {
		return this.longitud;
	}
	
	public int getTipo() {
		return this.tipo;
	}
}
