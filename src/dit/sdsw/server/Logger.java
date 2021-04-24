package dit.sdsw.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {
	
	private boolean enabled = false;
	PrintWriter fd;
	
	public Logger(boolean enabled, String outputFile) {
		try {
			fd = new PrintWriter(outputFile);		
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		}
		this.enabled = enabled;
	}
	
	public void info(String m) {
		if(enabled) 
			System.out.println(m);
		
		fd.println(m);
		fd.flush();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
