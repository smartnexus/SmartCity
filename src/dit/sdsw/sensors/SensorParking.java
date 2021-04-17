package dit.sdsw.sensors;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SensorParking {
	
	private int plazas; //Plazas ocupadas.
	private int limite;
	
	public SensorParking() {
		plazas = 0;
		limite = 20;
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override
            public void run() {  
            	Random random = new Random();
            	int diff = (random.nextInt(21) - 10);
            	
            	if(diff < 0 && (plazas + diff) < 0)
            		diff = plazas;
            	else if(diff > 0 && (plazas + diff) > limite)	
            		diff = limite - plazas;
            	
            	plazas += diff;
           }
		}, 0, 1000);
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	public static void main(String[] args) throws InterruptedException {
		SensorParking sensor = new SensorParking();
		Thread.sleep(10000);
		System.out.println("Running:" + sensor.plazas);
		Thread.sleep(10000);
		System.out.println("Running:" + sensor.plazas);
	}

}
