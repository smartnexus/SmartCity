package dit.sdsw.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Scanner;

import dit.sdsw.Utils;
import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.RegistraServiciosImpl;

@SuppressWarnings("deprecation")
public class ServidorCity {
	
	private static Logger logger;

	public static void main (String args[]) throws RemoteException {
		
		if (args.length!=1) {
            System.err.println("Uso: ServidorCity numPuertoRegistro");
            return;
        }
		
		Utils.initRMIRegistry(args[0]);
		System.out.println("Registro RMI creado en el puerto: " + args[0]);
		
		logger = new Logger(false, "log.file");
		
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        
        try {
        	
            RegistraServicios srv = new RegistraServiciosImpl();
            RegistraServiciosImpl srv2 = (RegistraServiciosImpl) srv;
            Listas listas = new Listas(srv2);
            
            Naming.rebind("rmi://localhost:" + args[0] + "/RegistraServicios", srv);

            do {
	            System.out.println ("\t 1) Listar CONTENEDORES existentes en la ciudad.");
	        	System.out.println ("\t 2) Listar   PARKINGS   existentes en la ciudad.");
	        	System.out.println ("\t 3) Listar   FAROLAS    existentes en la ciudad.");
	        	System.out.println ("\t 4) Mostrar alertas.");
	        	System.out.println ("\t 5) Salir");
	        	
	        	
	        	int entradaTeclado = 0;
	        	Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
	        	
	        	do {
	        		System.out.print ("Seleccione la opción deseada: ");
	        		entradaTeclado = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
	                /// System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");  
	        	} while (entradaTeclado!=1 && entradaTeclado!=2 && entradaTeclado!=3 && entradaTeclado!=4); 
           
	        	
	        	
            } while(true);
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
