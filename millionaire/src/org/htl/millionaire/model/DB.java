package org.htl.millionaire.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.htl.millionaire.utils.PropertyReader;

public class DB {
	Connection conn;
	public DB() throws IOException, ClassNotFoundException, SQLException {
		PropertyReader rd = new PropertyReader("db.properties");
		String user = rd.get("user");
		String password = rd.get("password");
		String driver = rd.get("driver");
		String url = rd.get("url");
		
		//Class.forName(driver); //e.g. "com.mysql.jdbc.Driver"
		conn = DriverManager.getConnection(url, user, password);
		
	}
	
	private int getLastInsertId() throws SQLException
	{
		String sql = "SELECT LAST_INSERT_ID()";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt(1);
		rs.close();
		stmt.close();
		return id;
	}
	
	public int addQuestion(String text, int difficulty, String info, int genre) throws SQLException
	{
		String sql = "INSERT INTO question (text, difficulty, info, genre) VALUES (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setInt(2, difficulty);
		stmt.setString(3, info);
		stmt.setInt(4, genre);
		stmt.executeUpdate();
		stmt.close();
		return getLastInsertId();
	}
	
	public void addAnswer(String text, int questionId, boolean correct) throws SQLException
	{
		String sql = "INSERT INTO answer(question_id, text, correct) VALUES (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionId);
		stmt.setString(2, text);
		stmt.setBoolean(3, correct);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void close() throws SQLException
	{
		conn.close();
	}
}
