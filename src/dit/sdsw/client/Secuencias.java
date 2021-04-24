package dit.sdsw.client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Color;
//import dit.sdsw.Utils;
import dit.sdsw.sensors.SensorContenedor; //TODO: Comentar esto, no debe importarse
import dit.sdsw.sensors.SensorParking;
import dit.sdsw.sensors.SensorFarola;
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioParking;
import dit.sdsw.server.services.ServicioFarola;



public class Secuencias {
	
	
	public static void iniciarContenedor(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.print ("**********************************************************\n"
						+ "**************CREANDO CONTENEDOR INTELIGENTE**************\n"
						+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros del conteneror...");
		System.out.println("\nNivel máximo (en cm):   ");
		int nivelMax = entradaEscaner.nextInt();
		System.out.println("\nLatitud (separador ','):   ");
		float latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud (separador ','):   ");
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
	            	if (sensorCont.getNivel() == nivelMax && !srvCont.obtenerAlertado()) {
	            		srvCont.alertarLleno();	    //Este método se va a ejecutar en el servidor
	            	}
	            	if (srvCont.obtenerVaciar()) {  //Servidor ha marcado contenedor para vaciar
	            		sensorCont.setNivel(0);
	            		srvCont.alertarVacio();	            		
	            	}
	           } catch (RemoteException e) {
					e.printStackTrace();
	           } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	           }
			} 
		}, 0, 1000);
	}
	
	public static void iniciarParking(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.println("**********************************************************\n"
				+ "***************CREANDO PARKING INTELIGENTE***************\n"
				+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros del parking...");
		System.out.println("\nNombre: ");
		String nombre = entradaEscaner.next();
		System.out.println("\nLatitud (separador ','): ");
		float latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud (separador ','): ");
		float longitud = entradaEscaner.nextFloat();
		System.out.println("\nCapacidad total: ");
		int capacidadTotal = entradaEscaner.nextInt();
		System.out.println("\nEstableciendo abierto: " + Color.BLUE + "true" + Color.RESET);
		boolean abierto = true;
		System.out.println("\nEstableciendo plazasOcupadas: " + Color.BLUE + "0" + Color.RESET);
		int plazasOcupadas = 0;
		
		SensorParking sensor = new SensorParking(capacidadTotal);
		ServicioParking srvParking = srv.crearSrvParking(nombre, latitud, longitud, capacidadTotal, abierto, plazasOcupadas);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					if(!srvParking.obtenerAbierto()) 
						sensor.setPlazas(0);
					 else
						 srvParking.cambiarPlazasOcupadas(sensor.getPlazas());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}, 0, 5000);
		
		while(true) {
			System.out.println("\nEstado actual del parking: " + (srvParking.obtenerAbierto()?Color.GREEN + "abierto":Color.RED + "cerrado") + Color.RESET);
			System.out.println("Pulse 0 para cambiar el estado: ");
			int opcion = entradaEscaner.nextInt();
			if(opcion == 0)
				if(srvParking.obtenerAbierto())
					srvParking.alertarCerrado();
				else
					srvParking.alertarAbierto();
				System.out.println("\nCambiado correctamente.");
		}
		
	}
	
	public static void iniciarFarola(Scanner entradaEscaner, RegistraServicios srv, long inicio) throws RemoteException{
		System.out.print("**********************************************************\n"
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
