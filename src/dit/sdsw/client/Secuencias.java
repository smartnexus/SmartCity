package dit.sdsw.client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Utils;
import dit.sdsw.sensors.SensorContenedor; //TODO: Comentar esto, no debe importarse
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.ServicioContenedor;

public class Secuencias {
	
	public static void iniciarContenedor(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.print ("**********************************************************\n"
				+ "**************CREANDO CONTENEDOR INTELIGENTE**************\n"
				+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros del conteneror...");
		System.out.println("\nNivel máximo (en cm):   ");
		int nivelMax = entradaEscaner.nextInt();
		System.out.println("\nLatitud:   ");
		float latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud:   ");
		float longitud = entradaEscaner.nextFloat();
		System.out.println("\nTipo (1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico):   ");
		int tipo = entradaEscaner.nextInt();
		
		SensorContenedor sensorCont = new SensorContenedor(nivelMax);
		ServicioContenedor srvCont = srv.crearSrvContenedor(latitud, longitud, tipo);
		
		//Comprobar cada segundo el nivel del contenedor. Si llega al novel máximo, alertar al servidor.
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
				@Override
	            public void run() { try { 
					srvCont.cambiarPorcentaje(sensorCont.getNivel()*100/sensorCont.getNivelMax());
					/* DUDA: hacer algo? como se comprueba esto
					 * if (sensorCont.getNivel() == 0) 
						srvCont.cambiarVacio(true) ;
					else
						srvCont.cambiarVacio(false); 
					*/
	            	if (sensorCont.getNivel() == nivelMax)
	            		srvCont.alertarLleno();	   
	            		//TODO: servidor debe vaciar contenedor. Cómo??
	           } catch (RemoteException e) {
					e.printStackTrace();
	           }
			} 
		}, 0, 1000);
	}
	
	public static void iniciarParking(Scanner entradaEscaner, RegistraServicios srv) {
		
	}
	
	public static void iniciarFarola(Scanner entradaEscaner, RegistraServicios srv) {
		
	}

}
