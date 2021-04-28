package dit.sdsw.client;

import java.rmi.*;
import java.util.Scanner;

import dit.sdsw.Color;
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
			System.out.println("\t\t __                      _       ___ _ _         ");
			System.out.println("\t\t/ _\\_ __ ___   __ _ _ __| |_    / __(_) |_ _   _ ");
			System.out.println("\t\t\\ \\| '_ ` _ \\ / _` | '__| __|  / /  | | __| | | |");
			System.out.println("\t\t_\\ \\ | | | | | (_| | |  | |_  / /___| | |_| |_| |");
			System.out.println("\t\t\\__/_| |_| |_|\\__,_|_|   \\__| \\____/|_|\\__|\\__, |");
			System.out.println("\t\t                                           |___/ ");
			System.out.println("\t\t                                                 ");
        	
        	
        	//Solicitar qué elemento inteligente se quiere crear
        	System.out.println(Color.BLUE + "\r[*] Listando opciones disponibles...\n" + Color.RESET);
        	System.out.println (Color.BLUE + "\t 1) " + Color.RESET + "Contenedor");
        	System.out.println (Color.BLUE + "\t 2) " + Color.RESET + "Farola");
        	System.out.println (Color.BLUE + "\t 3) " + Color.RESET + "Parking");
        	
        	int entradaTeclado = 0;
        	Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
        	
        	do {
        		System.out.print(Color.YELLOW + "\nSeleccione el NÚMERO del elemento inteligente que desea crear: " + Color.RESET);
        		entradaTeclado = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
        	} while (entradaTeclado!=1 && entradaTeclado!=2 && entradaTeclado!=3); 
           
        	RegistraServicios srv = (RegistraServicios) Naming.lookup("//" + args[0] + ":" + args[1] + "/RegistraServicios");
        	System.out.println("");
        	
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
            System.err.println(Color.RED + "[*] Error de comunicacion con servidor.");
        }
        catch (Exception e) {
            System.err.println(Color.RED + "[*] Excepcion en ClienteCity.");
        }
    }
}