package org.htl.millionaire.model;

public class Answer {
	private int id;
	private String text;
	private boolean correct;
	
	public Answer(int id, String text, boolean correct) {
		this.id = id;
		this.text = text;
		this.correct = correct;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	@Override
	public String toString() {
		return String.format("%s", text);
	}
}
