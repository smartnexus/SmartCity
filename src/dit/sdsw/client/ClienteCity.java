package dit.sdsw.client;

import java.rmi.*;
import java.util.Scanner;
import dit.sdsw.server.services.*;


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
        	Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
        	
        	do { 
        		System.out.print ("Seleccione el NÚMERO del elemento inteligente que desea crear:      ");
        		entradaTeclado = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
                /// System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");  
        		System.out.println();
        	} while (entradaTeclado!=1 || entradaTeclado!=2 || entradaTeclado!=3);
           
        	RegistraServicios srv = (RegistraServicios) Naming.lookup("//" + args[0] + ":" + args[1] + "/RegistraServicios");
        	
            switch (entradaTeclado) {
            	case 1: //Contenedor
            		Secuencias.iniciarContenedor(entradaEscaner, srv);
            		break;
            	
            	case 2: //Farola
            		long inicio = System.currentTimeMillis();
            		Secuencias.iniciarFarola(entradaEscaner, srv, inicio);
            		break;
            		
            	case 3: //Parking
            		Secuencias.iniciarParking(entradaEscaner, srv);
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