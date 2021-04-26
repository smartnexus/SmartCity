package dit.sdsw;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Random;

public class Utils {
	
	public static void initRMIRegistry(String portNumber) {
		try {
			LocateRegistry.createRegistry(Integer.valueOf(portNumber));
		} catch (RemoteException e) {
			System.err.println(Color.RED + "[*] Puerto " + portNumber + "en uso." + Color.RESET);
		}
	}
		
	/**
	 * @param min -> Minimum value to inc/dec
	 * @param max -> Maximum value to inc/dec
	 * @param value -> Current value
	 * @param limit -> Maximum value operation can reach
	 * @return new value
	 */
	public static Integer randomIncrement(int min, int max, int value, int limit)  {
		Random random = new Random();
    	int diff = (random.nextInt((max - min) + 1) + min);
    	
    	if(diff < 0 && (value + diff) < 0)
    		diff = value;
    	else if(diff > 0 && (value + diff) > limit)	
    		diff = limit - value;
    	
    	return (value += diff);
	}
	
	public static Integer incrementarHora(long inicio, long ahora) {
		int nivel;
		//La hora actual va a ser la hora actual menos la hora inicial (las 00) teniendo en cuenta los dias
		//que ya han pasado
		//Como un dia dura 24 segundos pasamos el tiempo que ha transcurrido a segundos
		double tiempo = (double) ((ahora - inicio)/1000);
		int hora = (int) (tiempo % 24);
		switch (hora) {
			case 0: nivel = 80;
			break;
			case 1: nivel = 60;
			break;
			case 2: nivel = 40;
			break;
			case 3: nivel = 40;
			break;
			case 4: nivel = 40;
			break;
			case 5: nivel = 40;
			break;
			case 6: nivel = 25;
			break;
			case 7: nivel = 15;
			break;
			case 8: nivel = 10;
			break;
			case 9: nivel = 0;
			break;
			case 10: nivel = 0;
			break;
			case 11: nivel = 0;
			break;
			case 12: nivel = 0;
			break;
			case 13: nivel = 0;
			break;
			case 14: nivel = 0;
			break;
			case 15: nivel = 0;
			break;
			case 16: nivel = 0;
			break;
			case 17: nivel = 0;
			break;
			case 18: nivel = 0;
			break;
			case 19: nivel = 0;
			break;
			case 20: nivel = 0;
			break;
			case 21: nivel = 40;
			break;
			case 22: nivel = 100;
			break;
			case 23: nivel = 100;
			break;
			default: nivel = 0; //Si por lo que sea falla la hora por ahorro energetico mejor dejarla apagada
			break;
		}
		
		return nivel;
	}
}	

