package dit.sdsw.server.services;

import java.rmi.*;

public interface RegistraServicios extends Remote {
	//tipo: 1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico
    ServicioContenedor crearSrvContenedor(float latitud, float longitud, int tipo) throws RemoteException;
    ServicioParking crearSrvParking(String nombre, float latitud, float longitud, int capacidadTotal, 
    		boolean abierto, int plazas_ocupadas) throws RemoteException;
    ServicioFarola crearSrvFarola(float latitud, float longitud, String color, boolean estado) throws RemoteException;
}