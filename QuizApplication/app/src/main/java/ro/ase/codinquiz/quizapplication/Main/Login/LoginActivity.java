package ro.ase.codinquiz.quizapplication.Main.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.HashSet;
import java.util.Set;

import ro.ase.codinquiz.quizapplication.Main.Student.JoinTestActivity;
import ro.ase.codinquiz.quizapplication.Main.Student.StudentMain;
import ro.ase.codinquiz.quizapplication.Main.Teacher.TeacherSeeStatistics;
import ro.ase.codinquiz.quizapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et = findViewById(R.id.inputEmail);

        final SharedPreferences sp = getApplication().getSharedPreferences("cOdin", MODE_PRIVATE);
        String name = sp.getString("username", null);
        if(name != null) {
            et.setText(name);
        }
    }

    public void joinTest(View view){
        Intent intent = new Intent(this, JoinTestActivity.class);
        startActivity(intent);
    }


    public void loginChecker(View view){
        Switch sw=(Switch)findViewById(R.id.switchLogin);
        if(sw.isChecked()){
            openTeacherActivity(view);

        }
        else
            openStudentActivity(view);
    }
    public void openTeacherActivity(View view){
        Intent intent =new Intent(this,TeacherSeeStatistics.class);
        startActivity(intent);
    }

    public void openStudentActivity(View view){
       Intent intent =new Intent(this,StudentMain.class);
       startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EditText et = findViewById(R.id.editStudentName);
        String name;
        name = et.getText().toString();
        SharedPreferences sp = getApplication().getSharedPreferences("cOdin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", name);
        editor.commit();
    }
}
