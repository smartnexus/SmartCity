package dit.sdsw.client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//import dit.sdsw.Utils;
import dit.sdsw.sensors.SensorContenedor; //TODO: Comentar esto, no debe importarse
import dit.sdsw.sensors.SensorParking;
import dit.sdsw.sensors.SensorFarola;
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioParking;
import dit.sdsw.server.services.ServicioFarola;

public class Secuencias {
	
	public static final String ANSI_BLUE = "\u001B[34m";
	
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
	
	public static void iniciarParking(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.print ("**********************************************************\n"
				+ "**************CREANDO PARKING INTELIGENTE**************\n"
				+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros del parking...");
		System.out.println("\nNombre:   ");
		String nombre = entradaEscaner.next();
		System.out.println("\nLatitud (separador ','):   ");
		float latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud (separador ','):   ");
		float longitud = entradaEscaner.nextFloat();
		System.out.println("\nCapacidad total:   ");
		int capacidadTotal = entradaEscaner.nextInt();
		System.out.println("\nEstableciendo abierto: " + ANSI_BLUE + "\ntrue   ");
		boolean abierto = true;
		System.out.println("\nEstableciendo plazasOcupadas: " + ANSI_BLUE + "\n0   ");
		int plazasOcupadas = 0;
		
		SensorParking sensor = new SensorParking(plazasOcupadas);
		ServicioParking srvParking = srv.crearSrvParking(nombre, latitud, longitud, capacidadTotal, abierto, plazasOcupadas);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					srvParking.cambiarPlazasOcupadas(sensor.getPlazas());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}, 0, 1000);
		
		while(true) {
			System.out.println("\nEstado actual del parking: " + (srvParking.obtenerAbierto()?"abierto":"cerrado"));
			System.out.println("Pulse 0 para cambiar el estado:   ");
			int opcion = entradaEscaner.nextInt();
			if(opcion == 0)
				System.out.println("Cambiado correctamente.");
				if(srvParking.obtenerAbierto())
					srvParking.alertarAbierto();
				else
					srvParking.alertarCerrado();
		}
		
	}
	
	public static void iniciarFarola(Scanner entradaEscaner, RegistraServicios srv, long inicio) throws RemoteException{
		System.out.print ("**********************************************************\n"
				+ "**************CREANDO FAROLA INTELIGENTE**************\n"
				+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros de la farola...");
		System.out.println("\nLatitud (separador ','):   ");
		float latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud (separador ','):   ");
		float longitud = entradaEscaner.nextFloat();
		System.out.println("\nColor:   ");
		String color = entradaEscaner.next();
		System.out.println("\nProcesando estado de la farola (false=apagado/true=encendido)\n");
		boolean estado = false;
		
		SensorFarola sensor = new SensorFarola(inicio);
		ServicioFarola srvFarola = srv.crearSrvFarola(latitud, longitud, color, estado);
		
		while (true) {
			if (sensor.getNivel() > 0) {
				//Significa que la farola debe estar encendida
				srvFarola.setEstado(true);
				if (srvFarola.isEstado_ant() != true) {
					srvFarola.alertarCambioEstado();
				}
			}
			if (sensor.getNivel() == 0) {
				//Significa que la farola debe estar apagada
				srvFarola.setEstado(false);
				if (srvFarola.isEstado_ant() != false) {
					srvFarola.alertarCambioEstado();
				}
			}
			
		}
	}
	
//	public static void main(String args[]) {
//		try {
//			iniciarParking(new Scanner(System.in), null);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//	}

}
