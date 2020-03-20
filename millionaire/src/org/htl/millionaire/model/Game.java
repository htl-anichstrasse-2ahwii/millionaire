package org.htl.millionaire.model;

import java.io.IOException;
import java.sql.SQLException;

public class Game {
	int currentLevel = 0;
	int genre = 1;
	DB db = null;

	public Game(int currentLevel, int genre) throws ClassNotFoundException, IOException, SQLException {
		this.currentLevel = currentLevel;
		this.genre = genre;
		db = new DB();
	}

	public Game() throws ClassNotFoundException, IOException, SQLException {
		this(0,1);
	}

	public Question getQuestionByRandom() throws SQLException
	{
		return db.getQuestionByRandom(genre, currentLevel);
	}
}
