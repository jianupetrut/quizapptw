package ro.ase.codinquiz.quizapplication.Main.Entities;

import android.media.Image;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private int id;
    private String category;
    private String text;
    private List<Answer> answerList;
    private Image image;

    public Question(String category,String text, List<Answer> answerList, Image image) {
        this.category = category;
        this.answerList = answerList;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
