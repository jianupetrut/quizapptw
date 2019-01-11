package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        String text="Now taking test " +testCode +" as primitive and we also passed choice: " +c.getContent()+ " as object";
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
        test=new Test(Integer.parseInt(testCode),"Test",null,false,false,false,false,30,1,true);
        tw=findViewById(R.id.textViewStartTest_TestDetails);
        tw.setText("Now taking test"+ test.getTestName()+ ". You have "+test.getTime()+"  minutes");

    }

    public void startTest(View view){

        Intent i=new Intent( this,StudentTestActivity.class);
        startActivity(i);


    }
}
