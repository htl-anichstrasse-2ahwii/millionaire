package org.htl.millionaire.model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author albert
 * Because import is done in more than one table, a bit of programming is necessary
 */
public class Importer {

	public Importer() {
	}

	/**
	 * Start the import here
	 * @param file the name of the CSV-File
	 * @param genreId which genre should be associated with the questions to be imported
	 */
	public void doImport(String file, int genreId) throws IOException, SQLException, ClassNotFoundException {
		DB db = new DB();
		Scanner s = new Scanner (new File(file));
		s.nextLine(); //skip headline
		while (s.hasNextLine())
		{
			String l = s.nextLine();
			String[] p = l.split("\t");
			System.out.println(Arrays.toString(p));
			int difficulty = Integer.parseInt(p[0]);
			String qText = p[1];
			String ans1 = p[2];
			String ans2 = p[3];
			String ans3 = p[4];
			String ans4 = p[5];
			
			String info = "";
			if (p.length == 7)
				info = p[6];
			
			int qId = db.addQuestion(qText, difficulty, info, genreId);
			db.addAnswer(ans1, qId, true);
			db.addAnswer(ans2, qId, false);
			db.addAnswer(ans3, qId, false);
			db.addAnswer(ans4, qId, false);
		}
		s.close();
		db.close();
	}

	/**
	 * Start the import separated from the game
	 */
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		Importer importer = new Importer();
		importer.doImport("data/MillionaireDefaultUS.txt", 1 );
		
	}
}
