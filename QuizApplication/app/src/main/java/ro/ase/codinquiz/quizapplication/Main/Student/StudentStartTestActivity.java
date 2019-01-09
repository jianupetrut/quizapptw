package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ro.ase.codinquiz.quizapplication.Main.Teacher.Models.Choice;
import ro.ase.codinquiz.quizapplication.R;

public class StudentStartTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_start_test);
        Intent intent=getIntent();

        String testCode=intent.getStringExtra("CODE");
        Choice c=(Choice)intent.getSerializableExtra("CHOICE");
        String text="Now taking test " +testCode +" as primitive and we also passed choice: " +c.getContent()+ " as object";
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
