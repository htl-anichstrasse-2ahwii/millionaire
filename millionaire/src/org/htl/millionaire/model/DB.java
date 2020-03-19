package org.htl.millionaire.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.htl.millionaire.utils.PropertyReader;

public class DB {
	Connection conn;
	public DB() throws IOException, ClassNotFoundException, SQLException {
		PropertyReader rd = new PropertyReader("db.properties");
		String user = rd.get("user");
		String password = rd.get("password");
		String driver = rd.get("driver");
		String url = rd.get("url");
		
		Class.forName(driver); //e.g. "com.mysql.jdbc.Driver"
		conn = DriverManager.getConnection(url, user, password);
		
	}
	
	public void close() throws SQLException
	{
		conn.close();
	}
}
