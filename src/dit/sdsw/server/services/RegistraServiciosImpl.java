package dit.sdsw.server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import dit.sdsw.server.ServidorCity;

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
		ServidorCity.getLogger().info("[AVISO:SRV_REG] Se ha registrado un nuevo contenedor. (Actual: " + l_cont.size() + ")");
		return c;
	}

	@Override
	public ServicioParking crearSrvParking(String nombre, float latitud, float longitud, int capacidadTotal,
			boolean abierto, int plazas_ocupadas) throws RemoteException {
		ServicioParking c = new ServicioParkingImpl(nombre, latitud, longitud, capacidadTotal, abierto, plazas_ocupadas);
		l_park.add(c);
		ServidorCity.getLogger().info("[AVISO:SRV_REG] Se ha registrado un nuevo Parking. (Actual: " + l_park.size() + ")");
		return c;
	}

	@Override
	public ServicioFarola crearSrvFarola(float latitud, float longitud, String color, boolean estado) throws RemoteException {
		ServicioFarola c = new ServicioFarolaImpl(latitud, longitud, color, estado);
		l_far.add(c);
		ServidorCity.getLogger().info("[AVISO:SRV_REG] Se ha registrado una nuevo farola. (Actual: " + l_far.size() + ")");
		return c;
	}

	public List<ServicioContenedor> getL_cont() {
		return l_cont;
	}


	public List<ServicioParking> getL_park() {
		return l_park;
	}


	public List<ServicioFarola> getL_far() {
		return l_far;
	}


}
