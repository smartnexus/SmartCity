package dit.sdsw.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Scanner;

import dit.sdsw.Color;
import dit.sdsw.Utils;
import dit.sdsw.client.Secuencias;
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.RegistraServiciosImpl;

@SuppressWarnings("deprecation")
public class ServidorCity {
	
	private static Logger logger;

	public static void main (String args[]) throws RemoteException {
		
		if (args.length!=1) {
            System.err.println(Color.RED + "Uso: ServidorCity numPuertoRegistro" + Color.RESET);
            return;
        }
		
		Utils.initRMIRegistry(args[0]);
		System.out.println(Color.RED + "Registro RMI creado en el puerto: " + args[0] + Color.RESET);
		
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        
        try {
        	
            RegistraServicios srv = new RegistraServiciosImpl();
            RegistraServiciosImpl srv2 = (RegistraServiciosImpl) srv;
            Listas listas = new Listas(srv2);
            logger = new Logger(false, "log.file", listas);
            
            Naming.rebind("rmi://localhost:" + args[0] + "/RegistraServicios", srv);

            int entradaTeclado = 0;
            do {
            	System.out.println ("¿Qué desea hacer?");
	            System.out.println (Color.BLUE + "\t 1) "+ Color.RESET + " Listar CONTENEDORES existentes en la ciudad.");
	        	System.out.println (Color.BLUE + "\t 2) "+ Color.RESET + " Listar   PARKINGS   existentes en la ciudad.");
	        	System.out.println (Color.BLUE + "\t 3) "+ Color.RESET + " Listar   FAROLAS    existentes en la ciudad.");
	        	System.out.println (Color.BLUE + "\t 4) "+ Color.RESET + " Mostrar alertas.");
	        	System.out.println (Color.BLUE + "\t 5) "+ Color.RESET + " Salir");	
	        	
	        	
	        	Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
	        	
	        	do {
	        		System.out.print (Color.YELLOW + "Seleccione la opción deseada: "+ Color.RESET);
	        		entradaTeclado = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
	                /// System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");  
	        	} while (entradaTeclado!=1 && entradaTeclado!=2 && entradaTeclado!=3 && entradaTeclado!=4 && entradaTeclado!=5); 
	        	
	        	switch (entradaTeclado) {
	            	case 1: //Listar contenedor
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		System.out.println(Color.BLUE + "[*] Listando contenedores existentes..." + Color.RESET);
	            		listas.listarContenedores();
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		break;
	            	
	            	case 2: //Listar parkings
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		System.out.println(Color.BLUE + "[*] Listando parkings existentes..." + Color.RESET);
	            		listas.listarParkings();
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		break;
	            		
	            	case 3: //Listar farolas
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		System.out.println(Color.BLUE + "[*] Listando farolas existentes..." + Color.RESET);
	            		listas.listarFarolas();
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		break;
	            	case 4: //Mostrar logs
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		System.out.println(Color.BLUE + "[*] Mostrando alertas (Pulse \"0\" para salir de este modo) ..." + Color.RESET);
	            		logger.setEnabled(true);
	            		int entrada = 8;
	            		while(entrada != 0){
	            			entrada = entradaEscaner.nextInt();
	            			if (entrada == 0) {
	            				logger.setEnabled(false);
	            			}
	            		}
	            		System.out.println(Color.RED +"\n---------------------------------------------" + Color.RESET);
	            		break;
	        	}      	
	        	
            } while(entradaTeclado!=5);
            System.exit(0);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorLog:");
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLog(Logger logger) {
		ServidorCity.logger = logger;
	}
	
}
