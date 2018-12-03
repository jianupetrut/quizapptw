package ro.ase.codinquiz.quizapplication.Main.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ro.ase.codinquiz.quizapplication.R;

public class JoinTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);
        Toast.makeText(this,"started",Toast.LENGTH_SHORT);
    }
}
