package dit.sdsw.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Color;
import dit.sdsw.Utils;
import dit.sdsw.server.services.ServicioContenedor;
import dit.sdsw.server.services.ServicioContenedorImpl;
import dit.sdsw.server.services.ServicioFarola;
import dit.sdsw.server.services.ServicioFarolaImpl;
import dit.sdsw.server.services.ServicioParking;
import dit.sdsw.server.services.ServicioParkingImpl;

public class Logger {
	
	private boolean enabled = false;
	private Listas listas;
	PrintWriter fd;
	
	public Logger(boolean enabled, String outputFile, Listas listas) {
		try {
			fd = new PrintWriter(outputFile);		
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		}
		//TODO: inicializar fuera.
		String url= "jdbc:postgresql://localhost:5432/dit";
		String user = "dit";
		String pass = "dit";
		
		//backupValues(url, user, pass);
		this.listas = listas;
		this.enabled = enabled;
	}
	
	public void info(String m) {
		if(enabled) 
			System.out.println("\n" + Color.BLUE + "[*] " + Color.RESET + m);
		
		fd.println(m);
		fd.flush();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void backupValues(String url, String user, String pass) {
		LogsDAO logs = new LogsDAO(url, user, pass);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for(ServicioContenedor contenedor : listas.getSrv().getL_cont()) {
					logs.guardarContenedor((ServicioContenedorImpl) contenedor);
				}
				
				for(ServicioParking parking : listas.getSrv().getL_park()) {
					logs.guardarParking((ServicioParkingImpl) parking);
				}
				
				for(ServicioFarola farola : listas.getSrv().getL_far()) {
					logs.guardarFarolas((ServicioFarolaImpl) farola);
				}
			}
		}, 0, 8000);
	}

}
