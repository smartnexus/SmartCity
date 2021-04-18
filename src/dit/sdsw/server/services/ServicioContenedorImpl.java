package dit.sdsw.server.services;

import java.rmi.RemoteException;

public class ServicioContenedorImpl implements ServicioContenedor{
	private int id;
	private float latitud;
	private float longitud;
	private int tipo; //tipo: 1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico
	private float porcentaje = 0; //Porcentaje lleno
	private boolean vacio = true;
	

	public ServicioContenedorImpl(float latitud, float longitud, int tipo) {
		//TODO: @celllarod inicializar id
		this.latitud = latitud;
		this.longitud = longitud;
		this.tipo = tipo;

	}

	@Override
	public void alertarLleno() throws RemoteException {
		// TODO: Cómo vamos a avisar. DUDAS aquí
		System.out.println("[AVISO] Contenedor lleno (ID=" + this.id + ")");
		
	}

	@Override
	public void cambiarPorcentaje(float porcentaje) throws RemoteException {
		this.porcentaje = porcentaje;		
	}
	
	public float getId() throws RemoteException {
		return this.id;
	}
	
	public boolean isVacio() throws RemoteException {
		return this.vacio;
	}

	public float getLatitud() throws RemoteException {
		return this.latitud;
	}
	
	public float getLongitud() throws RemoteException {
		return this.longitud;
	}
	
	public float getPorcentaje() throws RemoteException {
		return this.porcentaje;
	}
	
	public int getTipo() throws RemoteException {
		return this.tipo;
	}
	
	
	//ACLARACION: servidor pondrá contenedor vacío cuando reciba alerta de contenedor lleno (para simular recogida de residuos)
	public void setVacio(boolean vacio) throws RemoteException {
		this.vacio = vacio;		
	}
}
