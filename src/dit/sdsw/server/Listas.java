package dit.sdsw.server;


import java.util.List;

import dit.sdsw.server.services.RegistraServiciosImpl;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioFarola;
import dit.sdsw.server.services.ServicioParking;

public class Listas {
	
	private RegistraServiciosImpl srv;
	
	Listas (RegistraServiciosImpl srv) {
		this.srv = srv;
	}
	
	public void ListarContenedores (RegistraServiciosImpl srv) {
		List<ServicioContenedor> lista = srv.getL_cont();
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice));
		}
	}
	
	public void ListarFarolas () {
		List<ServicioFarola> lista = srv.getL_far();
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice));
		}
	}
	
	public void ListarParkings () {
		List<ServicioParking> lista = srv.getL_park();
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice));
		}
	}
}

