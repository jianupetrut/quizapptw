package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Models.Choice;
import ro.ase.codinquiz.quizapplication.R;

public class StudentStartTestActivity extends AppCompatActivity {

    TextView tw;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_start_test);
        Intent intent=getIntent();

        String testCode=intent.getStringExtra("CODE");
        Choice c=(Choice)intent.getSerializableExtra("CHOICE");
        //------------------mockup data-------------------

        List<Answer> ansList1=new ArrayList<>();
        List<Answer> ansList2=new ArrayList<>();
        Answer ansList1answer1=new Answer(1,"Answer 1",true,1);
        Answer ansList1answer2=new Answer(2,"Answer 2",false,1);
        Answer ansList1answer3=new Answer(3,"Answer 3",false,1);
        Answer ansList1answer4=new Answer(4,"Answer 4",false,1);
        ansList1.add(ansList1answer1);
        ansList1.add(ansList1answer2);
        ansList1.add(ansList1answer3);
        ansList1.add(ansList1answer4);
        Answer ansList2answer1=new Answer(5,"Answer 1",false,2);
        Answer ansList2answer2=new Answer(6,"Answer 2",true,2);
        Answer ansList2answer3=new Answer(7,"Answer 3",true,2);
        Answer ansList2answer4=new Answer(8,"Answer 4",false,2);
        ansList2.add(ansList2answer1);
        ansList2.add(ansList2answer2);
        ansList2.add(ansList2answer3);
        ansList2.add(ansList2answer4);
        Question q1=new Question("Cat1","Text for question 1",ansList1,null);
        Question q2=new Question("Cat1","text for question2",ansList2,null);
        List<Question> questions=new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        //------------------mockup data-------------------

        String text="Now taking test " +testCode +" as primitive and we also passed choice: " +c.getContent()+ " as object";
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
        test=new Test(Integer.parseInt(testCode),"Test",questions,false,false,false,false,30,1,true);
        tw=findViewById(R.id.textViewStartTest_TestDetails);
        tw.setText("Now taking test"+ test.getTestName()+ " with "+ test.getQuestionList().size()+ " questions . You have "+test.getTime()+"  minutes");

    }

    public void startTest(View view){

        Intent i=new Intent( this,StudentTestActivity.class);
        i.putExtra("TEST",test);
        startActivity(i);


    }
}
