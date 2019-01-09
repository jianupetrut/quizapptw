package ro.ase.codinquiz.quizapplication.Main.Teacher.Models;

import java.util.ArrayList;

public class Question {
    private int ID;
    private String content;
    // property for the auxiliary mime
    private int allowedTime;
    private ArrayList<Choice> answers;
    private String category;
    private  String feedback; //if the users chooses a wrong answer, prompt this
    private int authorID;

    public Question(String content, ArrayList<Choice> answers, String category, String feedback, int authorID) {
        this.content = content;
        this.answers = answers;
        this.category = category;
        this.feedback = feedback;
        this.authorID=authorID;
    }

    @Override
    public String toString() {
        return "Question no. "+this.ID;
    }
}
