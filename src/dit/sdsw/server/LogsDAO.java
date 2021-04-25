package dit.sdsw.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dit.sdsw.server.services.ServicioContenedorImpl;
import dit.sdsw.server.services.ServicioFarolaImpl;
import dit.sdsw.server.services.ServicioParkingImpl;

public class LogsDAO {
	
	private String url;
	private String user;
	private String pass;
	
	public LogsDAO(String url, String  user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public boolean guardarContenedor(ServicioContenedorImpl srv) {
		Connection conn;
		boolean resultado = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO contenedores (id, lat, long, tipo, perc) VALUES (?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			String[] valores = srv.toString().split(",");
			st.setString(1, valores[0]);
			st.setString(1, valores[1]);
			st.setString(1, valores[2]);
			st.setString(1, valores[3]);
			st.setFloat(1, Float.parseFloat(valores[4]));
			int contador = st.executeUpdate();
			if (contador == 1) {
				resultado=true;
			}
			st.close();
			conn.close();		
			
		} catch (SQLException e) {
			//Nada que hacer
		}
		
		return resultado;
	}
	
	public boolean guardarParking(ServicioParkingImpl srv) {
		Connection conn;
		boolean resultado = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO parkings (nombre, lat, long, abierto, plazas) VALUES (?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			String[] valores = srv.toString().split(",");
			st.setString(1, valores[0]);
			st.setString(1, valores[1]);
			st.setString(1, valores[2]);
			st.setString(1, valores[4]);
			st.setInt(1, Integer.parseInt(valores[5]));
			int contador = st.executeUpdate();
			if (contador == 1) {
				resultado=true;
			}
			st.close();
			conn.close();		
			
		} catch (SQLException e) {
			// Nada que hacer
		}
		
		return resultado;
	}
	
	public boolean guardarFarolas(ServicioFarolaImpl srv) {
		Connection conn;
		boolean resultado = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO farolas (id, lat, long, color, estado) VALUES (?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			String[] valores = srv.toString().split(",");
			st.setString(1, valores[0]);
			st.setString(1, valores[1]);
			st.setString(1, valores[2]);
			st.setString(1, valores[3]);
			st.setString(1, valores[4]);
			int contador = st.executeUpdate();
			if (contador == 1) {
				resultado=true;
			}
			st.close();
			conn.close();		
			
		} catch (SQLException e) {
			//Nada que hacer
		}
		
		return resultado;
	}

}
