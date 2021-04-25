package dit.sdsw.server;


import java.util.List;

import dit.sdsw.Color;
import dit.sdsw.server.services.RegistraServiciosImpl;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioFarola;
import dit.sdsw.server.services.ServicioParking;

public class Listas {
	
	private RegistraServiciosImpl srv;
	//Cabeceras
	String[] cabCont = "ID,LATITUD,LONGITUD,TIPO,PORCENTAJE".split(",");
	String[] cabFar = "ID,LATITUD,LONGITUD,COLOR,ESTADO".split(",");
	String[] cabPar = "NOMBRE,LATITUD,LONGITUD,CAPACIDAD TOTAL,ABIERTO,PLAZAS OCUPADAS".split(",");
	
	
	public Listas (RegistraServiciosImpl srv) {
		this.srv = srv;		
	}

	public void listarContenedores () {
		List<ServicioContenedor> lista = srv.getL_cont();
		
		if (lista.isEmpty()) {
			System.out.println(Color.RED +"\n\tAún no existe ningún contenedor inteligente en la ciudad." + Color.RESET);
		}else {
			for (String i : cabCont) {
				System.out.format(Color.PURPLE+"%20s"+Color.RESET, i);
			}
			System.out.println();
			
			for(int indice = 0;indice<lista.size();indice++)
			{
				for(String a : lista.get(indice).toString().split(",")) {
					System.out.format("%20s", a);
				}
				System.out.println();			
			}
		}
	}
	
	public void listarFarolas () {
		List<ServicioFarola> lista = srv.getL_far();
		
		if (lista.isEmpty()) {
			System.out.println(Color.RED +"\n\tAún no existe ninguna farola inteligente en la ciudad." + Color.RESET);
		}else {
			for (String i : cabFar) {
				System.out.format(Color.PURPLE+"%20s"+Color.RESET, i);
			}
			System.out.println();
			
			for(int indice = 0;indice<lista.size();indice++)
			{
				for(String a : lista.get(indice).toString().split(",")) {
					System.out.format("%20s", a);
				}
				System.out.println();			
			}
		}
	}
	
	public void listarParkings () {
		List<ServicioParking> lista = srv.getL_park();
		
		if (lista.isEmpty()) {
			System.out.println(Color.RED +"\n\tAún no existe ningún parking inteligente en la ciudad." + Color.RESET);
		}else {
			for (String i : cabPar) {
				System.out.format(Color.PURPLE+"%20s"+Color.RESET, i);
			}
			System.out.println();
			
			for(int indice = 0;indice<lista.size();indice++)
			{
				for(String a : lista.get(indice).toString().split(",")) {
					System.out.format("%20s", a);
				}
				System.out.println();			
			}
		}
	}

	public RegistraServiciosImpl getSrv() {
		return srv;
	}

	public void setSrv(RegistraServiciosImpl srv) {
		this.srv = srv;
	}	
}

