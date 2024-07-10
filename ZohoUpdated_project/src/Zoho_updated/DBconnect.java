package Zoho_updated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect{

	public static Connection getConnection()throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/zoho_updated","root","dbpassword");
	}
}
