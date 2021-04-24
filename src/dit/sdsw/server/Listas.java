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
		System.out.println("Id\t Latitud\t Longitud\t Tipo\t Porcentaje");
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
	
	public void ListarFarolas () {
		List<ServicioFarola> lista = srv.getL_far();
		System.out.println("Id\t Latitud\t Longitud\t Color\t Estado");
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
	
	public void ListarParkings () {
		List<ServicioParking> lista = srv.getL_park();
		System.out.println("Nombre\t Latitud\t Longitud\t Capacicdad Total\t Abierto\t Plazas Ocupadas");
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
}

