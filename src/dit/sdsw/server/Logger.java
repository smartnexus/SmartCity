package dit.sdsw.server;

public class Logger {
	
	private boolean enabled = false;
	
	public Logger(boolean enabled, String outputFile) {
		this.enabled = enabled;
	}
	
	public void info(String m) {
		if(enabled) {
			System.out.println(m);
		}
		//TODO: metodo output file.
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
