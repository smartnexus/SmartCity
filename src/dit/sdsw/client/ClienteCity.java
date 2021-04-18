package dit.sdsw.client;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;


class ClienteCity {
    static public void main (String args[]) {
        if (args.length!=2) {
            System.err.println("Uso: ClienteCity hostregistro numPuertoRegistro");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
        	
        	//Solicitar qué elemento inteligente se quiere crear
        	System.out.println ("\t\t 1) Contenedor");
        	System.out.println ("\t\t 2) Farola");
        	System.out.println ("\t\t 1) Parking");
        	
        	int entradaTeclado = 0;
        	Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        	
        	do { 
        		System.out.print ("Seleccione el NÚMERO del elemento inteligente que desea crear:      ");
        		entradaTeclado = entradaEscaner.nextInt (); //Invocamos un método sobre un objeto Scanner
                /// System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");  
        		System.out.println ();
        	} while (entradaTeclado!=1 || entradaTeclado!=2 || entradaTeclado!=3);
           
            switch (entradaTeclado) {
            	case 1: //Contenedor
            		System.out.print ("**********************************************************\n"
            						+ "**************CREANDO CONTENEDOR INTELIGENTE**************\n"
            						+ "**********************************************************\n");
            		System.out.println("\nIntroduzca parámetros del conteneror...");
            		System.out.println("\nNivel máximo (en cm):   ");
            		int nivelMax = entradaEscaner.nextInt();
            		System.out.println("\nLatitud:   ");
            		int Latitud = entradaEscaner.nextInt();
            		System.out.println("\nLongitud:   ");
            		int Longitud = entradaEscaner.nextInt();
            		System.out.println("\nTipo (1-Vidrio/ 2-Cartón/ 3-Orgánico/ 4-Plástico):   ");
            		int tipo = entradaEscaner.nextInt();
            		
            		//crear SensorContenedor ServicioSontenedor con los parametros indicados
            		//sensor=new.
            		//RegistrarServicior sensor_cont = (SensorContenedor) Naming.lookup("//" + args[0] + ":" + args[1] + "/SensorContenedor");
                    //ServicioLog log_srv = srv.crearLog(args[3]);
            		
            		break;
            	
            	case 2: //Farola
            		
            		break;
            		
            	case 3: //Parking
            		
            		break;    
            }
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteCity:");
            e.printStackTrace();
        }
    }
}