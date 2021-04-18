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
		System.out.println("[AVISO] Contenedor lleno (ID=" + this.id);
		
	}

	@Override
	public float obtenerId() throws RemoteException {
		return this.id;
	}

	@Override
	public float obtenerLatitud() throws RemoteException {
		return this.latitud;
	}

	@Override
	public float obtenerLongitud() throws RemoteException {
		return this.longitud;
	}

	@Override
	public int obtenerTipo() throws RemoteException {
		return this.tipo;
	}

	@Override
	public float obtenerPorcentaje() throws RemoteException {
		return this.porcentaje;
	}

	@Override
	public boolean isVacio() throws RemoteException {
		return this.vacio;
	}

	@Override
	public void cambiarVacio(boolean vacio) throws RemoteException {
		this.vacio = vacio;		
	}

	@Override
	public void cambiarPorcentaje(float porcentaje) throws RemoteException {
		this.porcentaje = porcentaje;
		
	}

}
