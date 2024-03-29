package connectDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public void connect(){
		String url = "jdbc:sqlserver://localhost:1433;databasename=DBQuanLyKaraoke";
		String user = "sa";
		String password = "123";
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void disconnect () {
		if(con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) throws SQLException {
		getConnection();
		new ConnectDB().connect();
	}

}