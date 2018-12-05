package ro.ase.codinquiz.quizapplication.Main.Teacher.Models;

public class Choice {
    private int ID;
    private String content;
    private boolean isCorrect;

    public Choice(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }
}
