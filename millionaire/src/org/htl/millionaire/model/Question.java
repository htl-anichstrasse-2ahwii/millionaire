package org.htl.millionaire.model;

import java.util.ArrayList;

public class Question {
	private int id;
	private String text;
	private int difficulty;
	private String info;
	private int genre;
	private int howOften;
	private ArrayList<Answer> answers;
	
	public Question(int id, String text, int difficulty, String info, int genre, int howOften,
			ArrayList<Answer> answers) {
		this.id = id;
		this.text = text;
		this.difficulty = difficulty;
		this.info = info;
		this.genre = genre;
		this.howOften = howOften;
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getInfo() {
		return info;
	}

	public int getGenre() {
		return genre;
	}

	public int getHowOften() {
		return howOften;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	@Override
	public String toString() {
		String s = String.format("%d . %s : \n", id, text);
		int pos = 1;
		for (Answer a : answers)
		{
			s += String.format("%d: %s\n", pos++, a);
		}
		return s;
	}
}
