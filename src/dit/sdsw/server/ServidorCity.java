package dit.sdsw.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

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
            Naming.rebind("rmi://localhost:" + args[0] + "/RegistraServicios", srv);
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
