package dit.sdsw.server.services;

import java.rmi.RemoteException;

public class ServicioContenedorImpl implements ServicioContenedor{
	private int id;
	private float latitud;
	private float longitud;
	private int tipo; //tipo: 1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico
	private float porcentaje;
	private boolean vacio;
	

	public ServicioContenedorImpl(float latitud, float longitud, int tipo, float porcetaje, 
    		boolean vacio) {
		this.id = 
		this.latitud = latitud;
		this.longitud = longitud;
		this.tipo = tipo;
	}

	@Override
	public void alertarLleno() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
