package dit.sdsw.sensors;

import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Utils;

public class SensorFarola {
	
	private int nivel; //Nivel de luz de 0 a 100 (intensidad luminica de la farola)
	
	public SensorFarola(long inicio) {
		//Cada hora la farola proporcionara más nivel de luz o menos
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				long ahora = System.currentTimeMillis();
				setNivel(Utils.incrementarHora(inicio, ahora));
			}
		}, 0, 1000);
	}

	public int getNivel() {
		return nivel;
	}	

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}