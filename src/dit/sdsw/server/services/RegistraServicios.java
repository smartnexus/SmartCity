package dit.sdsw.server.services;

import java.rmi.*;

interface RegistraServicios extends Remote {
	//tipo: 1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico
    ServicioContenedor crearSrvContenedor(float latitud, float longitud, int tipo, float porcetaje, 
    		boolean vacio) throws RemoteException;
    ServicioParking crearSrvParking(String nombre, float latitud, float longitud, int capacidadTotal, 
    		boolean abierto, int plazas_ocupadas) throws RemoteException;
    
    //TODO: @jacjurtab rellenar parámetros (constructor)
    ServicioFarola crearSrvFarola() throws RemoteException;
}