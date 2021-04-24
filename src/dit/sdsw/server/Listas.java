package dit.sdsw.server;


import java.util.List;

import dit.sdsw.server.services.RegistraServiciosImpl;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioFarola;
import dit.sdsw.server.services.ServicioParking;

public class Listas {
	
	private RegistraServiciosImpl srv;
	private String id = "Id";
	private String latitud = "Latitud";
	private String longitud = "Longitud";
	private String tipo = "Tipo";
	private String porcentaje = "Porcentaje";
	private String color = "Color";
	private String estado = "Estado";
	private String nombre = "Nombre";
	private String capacidad = "Capacidad Total";
	private String abierto = "Abierto";
	private String plazas = "Plazas Ocupadas";
	
	Listas (RegistraServiciosImpl srv) {
		this.srv = srv;
	}
	
	public void listarContenedores () {
		List<ServicioContenedor> lista = srv.getL_cont();
		System.out.println(id + "\t" + latitud + "\t" + longitud + "\t" + tipo + "\t" + porcentaje);
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
	
	public void listarFarolas () {
		List<ServicioFarola> lista = srv.getL_far();
		System.out.println(id + "\t" + latitud + "\t" + longitud + "\t" + color + "\t" + estado);
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
	
	public void listarParkings () {
		List<ServicioParking> lista = srv.getL_park();
		System.out.println(nombre + "\t" + latitud + "\t" + longitud + "\t" + capacidad + "\t" + abierto + "\t" + plazas);
		for(int indice = 0;indice<lista.size();indice++)
		{
		    System.out.println(lista.get(indice).toString());
		}
	}
}

