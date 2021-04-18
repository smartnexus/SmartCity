package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RegistraServiciosImpl implements RegistraServicios{
	List<ServicioContenedor> l_cont;
	List<ServicioParking> l_park;
	List<ServicioFarola> l_far;

    RegistraServiciosImpl() throws RemoteException {
		 l_cont = new LinkedList<ServicioContenedor>();
		 l_park = new LinkedList<ServicioParking>();
		 l_far = new LinkedList<ServicioFarola>();
    }
    
	@Override
	public ServicioContenedor crearSrvContenedor(float latitud, float longitud, int tipo, float porcetaje,
			boolean vacio) throws RemoteException {
		//TODO: descomentar
		//ServicioContenedor c = new ServicioContenedorImpl(latitud, longitud, tipo, porcetaje, vacio);
		//l_cont.add(c);
		//return c;
		return null;
	}

	@Override
	public ServicioParking crearSrvParking(String nombre, float latitud, float longitud, int capacidadTotal,
			boolean abierto, int plazas_ocupadas) throws RemoteException {
		ServicioParking c = new ServicioParkingImpl(nombre, latitud, longitud, capacidadTotal, abierto, plazas_ocupadas);
		l_park.add(c);
		return c;
	}

	@Override
	public ServicioFarola crearSrvFarola() throws RemoteException {
		// TODO descomentar y añadir parametros
		//ServicioFarola c = new ServicioFarolaImpl(...);
		//l_far.add(c);
		//return c;
		return null;
	}	

}
