package dit.sdsw.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import dit.sdsw.Color;
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
		
		this.listas = listas;
		this.enabled = enabled;
		
		backupValues("jdbc:postgresql://localhost:5432/sdsw", "dit", "dit");
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
		if(logs.checkConnection()) {
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
		} else {
			System.err.println(Color.RED + "[*] No se ha podido realizar la conexión con la base de datos. "
					+ "Desactivando backup automático...\n" + Color.RESET);
		}
	}

}
