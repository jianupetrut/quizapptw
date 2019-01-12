package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.R;

public class StudentTestActivity extends AppCompatActivity {

    Test test;
    CheckBox cb1,cb2,cb3,cb4;
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);
        Intent intent=getIntent();
        test=(Test)intent.getSerializableExtra("TEST");
        cb1=findViewById(R.id.checkBox_test_Answer1);
        cb2=findViewById(R.id.checkBox_test_Answer2);
        cb3=findViewById(R.id.checkBox_test_Answer3);
        cb4=findViewById(R.id.checkBox_test_Answer4);
        tw=findViewById(R.id.textView_test_questionText);
        tw.setText(test.getQuestionList().get(0).getText());
        cb1.setText(test.getQuestionList().get(0).getAnswerList().get(0).getText());
        cb2.setText(test.getQuestionList().get(1).getAnswerList().get(1).getText());
        cb3.setText(test.getQuestionList().get(2).getAnswerList().get(2).getText());
        cb4.setText(test.getQuestionList().get(3).getAnswerList().get(3).getText());
    }
}
