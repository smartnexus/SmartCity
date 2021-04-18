package dit.sdsw.client;

import java.util.Scanner;

public class Secuencias {
	
	public static void iniciarContenedor(Scanner entradaEscaner) {
		System.out.print ("**********************************************************\n"
				+ "**************CREANDO CONTENEDOR INTELIGENTE**************\n"
				+ "**********************************************************\n");
		System.out.println("\nIntroduzca parámetros del conteneror...");
		System.out.println("\nNivel máximo (en cm):   ");
		int nivelMax = entradaEscaner.nextInt();
		System.out.println("\nLatitud:   ");
		float Latitud = entradaEscaner.nextFloat();
		System.out.println("\nLongitud:   ");
		float Longitud = entradaEscaner.nextFloat();
		System.out.println("\nTipo (1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico):   ");
		int tipo = entradaEscaner.nextInt();
		
		//crear SensorContenedor ServicioSontenedor con los parametros indicados
		//sensor=new.
		//RegistrarServicior sensor_cont = (SensorContenedor) Naming.lookup("//" + args[0] + ":" + args[1] + "/SensorContenedor");
		//ServicioLog log_srv = srv.crearLog(args[3]);
	}
	
	public static void iniciarParking(Scanner entradaEscaner) {
		
	}
	
	public static void iniciarFarola(Scanner entradaEscaner) {
		
	}

}
