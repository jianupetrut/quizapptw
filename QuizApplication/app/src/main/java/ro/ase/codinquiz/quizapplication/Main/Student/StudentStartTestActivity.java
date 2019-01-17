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
import java.util.concurrent.ExecutionException;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveTest;
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


        String text="Now taking test " +testCode;
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
        WorkerRetrieveTest workerRetrieveTest=new WorkerRetrieveTest(getApplicationContext(),Integer.parseInt(testCode));
        try {
            test=workerRetrieveTest.execute(test).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tw=findViewById(R.id.textViewStartTest_TestDetails);
        tw.setText("Now taking test"+ test.getTestName()+ " with "+ test.getQuestionList().size()+ " questions . You have "+test.getTime()+"  minutes");

    }

    public void startTest(View view){

        Intent i=new Intent( this,StudentTestActivity.class);
        i.putExtra("TEST",test);
        startActivity(i);


    }
}
