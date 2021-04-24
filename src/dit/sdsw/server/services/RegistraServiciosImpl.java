package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RegistraServiciosImpl extends UnicastRemoteObject implements RegistraServicios {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4886948558163037526L;
	List<ServicioContenedor> l_cont;
	List<ServicioParking> l_park;
	List<ServicioFarola> l_far;
	int num_cont = 0;

    public RegistraServiciosImpl() throws RemoteException {
		 l_cont = new LinkedList<ServicioContenedor>();
		 l_park = new LinkedList<ServicioParking>();
		 l_far = new LinkedList<ServicioFarola>();
    }
    
	@Override
	public ServicioContenedor crearSrvContenedor(float latitud, float longitud, int tipo) throws RemoteException {
		ServicioContenedor c = new ServicioContenedorImpl(latitud, longitud, tipo);
		l_cont.add(c);
		return c;
	}

	@Override
	public ServicioParking crearSrvParking(String nombre, float latitud, float longitud, int capacidadTotal,
			boolean abierto, int plazas_ocupadas) throws RemoteException {
		ServicioParking c = new ServicioParkingImpl(nombre, latitud, longitud, capacidadTotal, abierto, plazas_ocupadas);
		l_park.add(c);
		return c;
	}

	@Override
	public ServicioFarola crearSrvFarola(float latitud, float longitud, String color, boolean estado) throws RemoteException {
		ServicioFarola c = new ServicioFarolaImpl(latitud, longitud, color, estado);
		l_far.add(c);
		return c;
	}	

}
