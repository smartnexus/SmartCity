package dit.sdsw.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import dit.sdsw.server.services.RegistraServicios;
import dit.sdsw.server.services.RegistraServiciosImpl;

@SuppressWarnings("deprecation")
public class ServidorCity {
	
	public static void main (String args[]) {
	       if (args.length!=1) {
	            System.err.println("Uso: ServidorCity numPuertoRegistro");
	            return;
	        }
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

}
