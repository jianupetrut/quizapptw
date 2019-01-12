package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Models.Choice;
import ro.ase.codinquiz.quizapplication.R;

public class JoinTestActivity extends AppCompatActivity {


    public static final String CODE="EXTRA_CODE";
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);
        Toast.makeText(this,"started",Toast.LENGTH_SHORT);





    }

    public void onButtonStartClick(View view){

        EditText et=findViewById(R.id.editTestCode);

        code=et.getText().toString();
        Choice c=new Choice("Test",true);

        Intent i=new Intent( this,StudentStartTestActivity.class);
        i.putExtra("CODE",code);
        i.putExtra("CHOICE",c);
        startActivity(i);

    }


}
