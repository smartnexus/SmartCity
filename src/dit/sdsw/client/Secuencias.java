package dit.sdsw.client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Color;
import dit.sdsw.sensors.SensorContenedor;
import dit.sdsw.sensors.SensorParking;
import dit.sdsw.sensors.SensorFarola;
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioParking;
import dit.sdsw.server.services.ServicioFarola;



public class Secuencias {
	
	
	public static void iniciarContenedor(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.println(Color.PURPLE + "**********************************************************\n"
				+ "*************" + Color.GREEN + "CREANDO CONTENEDOR INTELIGENTE" + Color.PURPLE + "***************\n"
				+ "**********************************************************");
		System.out.println(Color.BLUE + "\n[*] Introduzca parámetros del contenedor..." + Color.RESET);
		System.out.print(Color.YELLOW + "\nNivel máximo (en cm): " + Color.RESET);
		int nivelMax = entradaEscaner.nextInt();
		System.out.print(Color.YELLOW + "Latitud (separador ','): " + Color.RESET);
		float latitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Longitud (separador ','): " + Color.RESET);
		float longitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Tipo (1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico): " + Color.RESET);
		int tipo = entradaEscaner.nextInt();
		
		SensorContenedor sensorCont = new SensorContenedor(nivelMax);
		ServicioContenedor srvCont = srv.crearSrvContenedor(latitud, longitud, tipo);
		
		System.out.println(Color.BLUE + "\n[*] Contenedor inteligente creado." + Color.RESET);
		
		//Comprobar cada segundo el nivel del contenedor. Si llega al novel máximo, alertar al servidor.
		System.out.println("\n----------------------------------\n");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
			@Override
			public void run() {
				try { 
					srvCont.cambiarPorcentaje(sensorCont.getNivel()*100/sensorCont.getNivelMax());
					int perc = sensorCont.getNivel()*100/sensorCont.getNivelMax();
					System.out.print("\r" + Color.YELLOW + "Porcentaje: " + (perc==100?Color.RED + perc:Color.GREEN + perc) + "%" + Color.RESET);
						
		            if (sensorCont.getNivel() == nivelMax && !srvCont.obtenerVaciar()) {
		            	System.out.println(Color.BLUE + "\n\n[*] Contenedor lleno, alertando al servidor..." + Color.RESET);
		            	srvCont.alertarLleno();	    //Este método se va a ejecutar en el servidor
		            }
		            if (srvCont.obtenerVaciar()) {  //Servidor ha marcado contenedor para vaciar
		            	sensorCont.setNivel(0);
		            	srvCont.alertarVacio();	
		            	System.out.println(Color.BLUE + "\n[*] Contenedor vaciado." + Color.RESET);
		            	System.out.println("\n----------------------------------\n");
		            }
		        } catch (RemoteException e) {
		        	System.err.println(Color.RED + "\n[*] Error de comunicacion con servidor.");
					System.exit(0);
		        } catch (InterruptedException e) {
		        	e.printStackTrace();
		        }
			} 
		}, 0, 1000);
	}
	
	public static void iniciarParking(Scanner entradaEscaner, RegistraServicios srv) throws RemoteException {
		System.out.println(Color.PURPLE + "**********************************************************\n"
				+ "****************" + Color.GREEN + "CREANDO PARKING INTELIGENTE" + Color.PURPLE + "***************\n"
				+ "**********************************************************");
		System.out.println(Color.BLUE + "\n[*] Introduzca parámetros del parking..." + Color.RESET);
		System.out.print(Color.YELLOW + "\nNombre: " + Color.RESET);
		String nombre = entradaEscaner.next();
		System.out.print(Color.YELLOW + "Latitud (separador ','): " + Color.RESET);
		float latitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Longitud (separador ','): " + Color.RESET);
		float longitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Capacidad total: " + Color.RESET);
		int capacidadTotal = entradaEscaner.nextInt();
		System.out.print(Color.YELLOW + "Estableciendo abierto: " + Color.RESET + "true");
		boolean abierto = true;
		System.out.println(Color.YELLOW + "\nEstableciendo plazasOcupadas: " + Color.RESET + "0");
		int plazasOcupadas = 0;
		
		SensorParking sensor = new SensorParking(capacidadTotal);
		ServicioParking srvParking = srv.crearSrvParking(nombre, latitud, longitud, capacidadTotal, abierto, plazasOcupadas);
		
		System.out.println(Color.BLUE + "\n[*] Parking inteligente creado." + Color.RESET);
		
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
					System.err.println(Color.RED + "\n[*] Error de comunicacion con servidor.");
					System.exit(0);
				}
			}
		}, 0, 5000);
		
		while(true) {
			System.out.println("\n----------------------------------");
			System.out.print(Color.YELLOW + "\r\nEstado actual del parking: " + (srvParking.obtenerAbierto()?Color.GREEN + "abierto":Color.RED + "cerrado") + Color.RESET);
			System.out.print(Color.YELLOW + "\r\n\nPulse 0 para cambiar el estado: " + Color.RESET);
			int opcion = entradaEscaner.nextInt();
			if(opcion == 0)
				if(srvParking.obtenerAbierto())
					srvParking.alertarCerrado();
				else
					srvParking.alertarAbierto();
			
			System.out.print(Color.BLUE + "\r\n[*] Cambiado correctamente.\n" + Color.RESET);
		}
		
	}
	
	public static void iniciarFarola(Scanner entradaEscaner, RegistraServicios srv, long inicio) throws RemoteException{
		System.out.print(Color.PURPLE + "**********************************************************\n"
				+ "****************" + Color.GREEN + "CREANDO FAROLA INTELIGENTE" + Color.PURPLE + "****************\n"
				+ "**********************************************************\n");
		System.out.println(Color.BLUE + "\n[*] Introduzca parámetros de la farola..." + Color.RESET);
		
		System.out.print(Color.YELLOW + "\nLatitud (separador ','): " + Color.RESET);
		float latitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Longitud (separador ','): " + Color.RESET);
		float longitud = entradaEscaner.nextFloat();
		System.out.print(Color.YELLOW + "Color: " + Color.RESET);
		String color = entradaEscaner.next();
		boolean estado = false;
		
		System.out.println(Color.BLUE + "\n[*] Farola inteligente creado." + Color.RESET);
		System.out.println(Color.BLUE + "\n[*] Procesando estado de la farola..." + Color.RESET);
		
		SensorFarola sensor = new SensorFarola(inicio);
		ServicioFarola srvFarola = srv.crearSrvFarola(latitud, longitud, color, estado);
		
		System.out.println("\n-------------------------------------------\n");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					if (sensor.getNivel() > 0) {
						//Significa que la farola debe estar encendida
						srvFarola.setEstado(true);
						if (srvFarola.isEstado_ant() != true) {
							System.out.print(Color.BLUE + "\r[*] Se ha encendido la farola a las " + sensor.getHora() + " horas" + Color.RESET);
							srvFarola.alertarCambioEstado();
						}
					}
					if (sensor.getNivel() == 0) {
						//Significa que la farola debe estar apagada
						srvFarola.setEstado(false);
						if (srvFarola.isEstado_ant() != false) {
							System.out.print(Color.BLUE + "\r[*] Se ha apagado la farola a las " + sensor.getHora() + " horas      " + Color.RESET);
							srvFarola.alertarCambioEstado();
						}
					}
				
				} catch (RemoteException e) {
					System.err.println(Color.RED + "\n[*] Error de comunicacion con servidor.");
					System.exit(0);
				}
			}
		}, 0, 1000);
	}

}
