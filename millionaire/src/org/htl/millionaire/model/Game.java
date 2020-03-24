package org.htl.millionaire.model;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author albert
 * Whole game logic is implemented here
 */
public class Game {
	int currentLevel = 0;
	int genre = 1;
	DB db = null;

	public Game(int currentLevel, int genre) throws ClassNotFoundException, IOException, SQLException {
		this.currentLevel = currentLevel;
		this.genre = genre;
		db = new DB();
	}

	/**
	 * per default start with level 0 and genre 1
	 */
	public Game() throws ClassNotFoundException, IOException, SQLException {
		this(0,1);
	}

	/**
	 * Get a question by random from actual difficulty and genre
	 */
	public Question getQuestionByRandom() throws SQLException
	{
		Question q = db.getQuestionByRandom(genre, currentLevel);
		q.shuffle();
		return q;
	}
	
	/**
	 * Increase level
	 */
	public void nextLevel() {
		currentLevel++;
	}
	
	/**
	 * 
	 * @return true if the current position of answers is the correct one
	 */
	public boolean isCorrect(Question q, int pos)
	{
		return q.isCorrectAnswer(pos);
	}
}
