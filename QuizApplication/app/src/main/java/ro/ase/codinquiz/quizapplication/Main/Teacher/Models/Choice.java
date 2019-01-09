package ro.ase.codinquiz.quizapplication.Main.Teacher.Models;

import java.io.Serializable;

public class Choice implements Serializable {
    private int ID;
    private String content;
    private boolean isCorrect;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Choice(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;

    }
}
