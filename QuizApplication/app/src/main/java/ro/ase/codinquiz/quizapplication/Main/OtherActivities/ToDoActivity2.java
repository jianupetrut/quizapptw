package ro.ase.codinquiz.quizapplication.Main.OtherActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ro.ase.codinquiz.quizapplication.R;

public class ToDoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do2);
    }

    public void sendRatings(View view) {

        Toast.makeText(this, "Thank you for your input, it has been submitted!",
                Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}
