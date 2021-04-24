package dit.sdsw.sensors;

import java.util.Timer;
import java.util.TimerTask;
import dit.sdsw.Utils;

public class SensorContenedor {
	private int nivel;       //Medido en centímetros
	private int nivelMax; 
	private boolean vaciando;
   
	public SensorContenedor(int nivelMax) {
		this.nivel = 0;
		this.nivelMax = nivelMax;
		this.vaciando = false;
		
		System.out.println("Nivel: " + this.nivel + "\n Nivel Máximo: " + this.nivelMax);
		
		//Simulamos el incremento del nivel de basura en el contenedor de forma aleatoria.
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
				@Override
	            public void run() {  
	            	setNivel(Utils.randomIncrement(0, 20, nivel, nivelMax));
	            	System.out.println("Nivel: "+ getNivel());
	           }
			}, 0, 1000);
		
	}

	public int getNivelMax() {
		return nivelMax;
	}

	public void setNivelMax(int nivelMax) {
		this.nivelMax = nivelMax;
	}

	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}	

	public boolean isVaciando() {
		return vaciando;
	}

	public void setVaciando(boolean vaciando) {
		this.vaciando = vaciando;
	}
	
	/*	public static void main(String args[]) throws InterruptedException {
	SensorContenedor sensor = new SensorContenedor(150);
	while(true) {
		Thread.sleep(1*1000);
		System.out.println(sensor.getNivel());
		
		if(sensor.getNivel() == 150) {
			sensor.setNivel(0);
		}
	}		
}
 */
}

