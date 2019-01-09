package ro.ase.codinquiz.quizapplication.Main.Entities;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String text;
    private String category;
    private ArrayList<Answer> answerList;
    private Image image;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }
}
