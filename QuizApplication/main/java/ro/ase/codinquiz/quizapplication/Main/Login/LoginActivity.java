package ro.ase.codinquiz.quizapplication.Main.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ro.ase.codinquiz.quizapplication.Main.Student.JoinTestActivity;
import ro.ase.codinquiz.quizapplication.Main.Student.StudentMain;
import ro.ase.codinquiz.quizapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void joinTest(View view){
        Intent intent = new Intent(this, JoinTestActivity.class);
        startActivity(intent);
    }
    public void openStudentActivity(View view){
       Intent intent =new Intent(this,StudentMain.class);
       startActivity(intent);
    }
}
