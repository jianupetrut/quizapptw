package ro.ase.codinquiz.quizapplication.Main.Entities;

import java.io.Serializable;

public class Answer implements Serializable {

    private int id;
    private String text;
    private boolean isCorrect;
    private int questionId;

    public Answer() {
    };

    public Answer(String text) {
        this.text = text;
    }

    public Answer(int id, String text, boolean isCorrect, int questionId) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
