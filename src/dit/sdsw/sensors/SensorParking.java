package dit.sdsw.sensors;

import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Utils;

public class SensorParking {
	
	private int plazas; //Plazas ocupadas.
	private int limite;
	
	public SensorParking(int limite) {
		this.plazas = 0;
		this.setLimite(limite);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {  
				setPlazas(Utils.randomIncrement(-20, 20, plazas, limite));
			}
		}, 0, 1000);
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
}
