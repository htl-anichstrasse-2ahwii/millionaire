package org.htl.millionaire.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.htl.millionaire.model.Game;
import org.htl.millionaire.model.Question;

public class Console {
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		Game game = new Game();
		Question q = game.getQuestionByRandom();
		System.out.println(q);
	}
}
