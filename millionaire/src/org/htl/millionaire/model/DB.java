package org.htl.millionaire.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.htl.millionaire.utils.PropertyReader;

/**
 * @author albert
 * Implements all DB access methods
 */
public class DB {
	Connection conn;
	/**
	 * Open connection. Connection properties are stored within <b>db.properties</b>
	 */
	public DB() throws IOException, ClassNotFoundException, SQLException {
		PropertyReader rd = new PropertyReader("db.properties");
		String user = rd.get("user");
		String password = rd.get("password");
		//String driver = rd.get("driver");
		String url = rd.get("url");
		
		//Class.forName(driver); //e.g. "com.mysql.jdbc.Driver"
		conn = DriverManager.getConnection(url, user, password);
		
	}
	
	/**
	 * Use this method for connecting tables. When storing an auto-generated primary key,
	 * use the return value of this method to fetch this id and store it as foreign key in another table
	 * @return
	 */
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
	
	/**
	 * Write Question information given by the parameters to database
	 */
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
	
	/**
	 * Write Answer information given by the parameters to database
	 */
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
	
	/**
	 * Fetch a question from database by random from a different genre with a certain difficulty
	 */
	public Question getQuestionByRandom(int genre, int difficulty) throws SQLException
	{
		String sql = "SELECT * FROM question WHERE genre = ? AND difficulty = ? ORDER BY RAND() LIMIT 1";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, genre);
		stmt.setInt(2, difficulty);
		ResultSet rs = stmt.executeQuery();
		Question q = null;
		if (rs.next())
		{
			int id = rs.getInt("id");
			String text = rs.getString("text");
			String info = rs.getString("info");
			int howOften = rs.getInt("how_often");
			ArrayList<Answer> answers = getAnwersByQuestionId(id);
			q = new Question(id, text, difficulty, info, genre, howOften, answers);
		}
		return q;
	}
	
	/**
	 * Get the corresponding anwers for a certain question
	 */
	private ArrayList<Answer> getAnwersByQuestionId(int qId) throws SQLException {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		String sql = "SELECT * FROM answer WHERE question_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, qId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			int id = rs.getInt("id");
			String text = rs.getString("text");
			boolean correct = rs.getBoolean("correct");
			Answer a = new Answer(id, text, correct);
			answers.add(a);
		}
		return answers;
	}

	/**
	 * Close database connection
	 */
	public void close() throws SQLException
	{
		conn.close();
	}
}
