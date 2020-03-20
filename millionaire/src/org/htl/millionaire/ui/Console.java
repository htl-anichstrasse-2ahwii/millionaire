package org.htl.millionaire.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.htl.millionaire.model.Game;
import org.htl.millionaire.model.Question;

public class Console {
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		Game game = new Game();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while (running)
		{
			Question q = game.getQuestionByRandom();
			System.out.println(q);
			System.out.println("Was ist die richtige Antwort");
			int answer = Integer.parseInt(scanner.nextLine()) - 1;
			if (q.isCorrectAnswer(answer))
			{
				System.out.println("stimmt!");
				q.nextLevel();
			} else
			{
				System.out.println("leider falsch!");
				running = false;
			}
			
		}

		scanner.close();
	}
}
