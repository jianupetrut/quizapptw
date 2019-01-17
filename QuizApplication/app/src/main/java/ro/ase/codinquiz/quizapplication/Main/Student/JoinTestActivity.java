package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
    private String studentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);
        Toast.makeText(this,"started",Toast.LENGTH_SHORT);





    }

    public void onButtonStartClick(View view){

        EditText et=findViewById(R.id.editTestCode);
        EditText et2 = findViewById(R.id.editStudentName);

        final SharedPreferences sp = getApplication().getSharedPreferences("cOdin", MODE_PRIVATE);
        String name = sp.getString("username", null);

        if(name != null) {
            code=et.getText().toString();
            et2.setVisibility(View.INVISIBLE);


            Intent i = new Intent(this, StudentStartTestActivity.class);
            i.putExtra("CODE", code);

            i.putExtra("studentName", name);
            startActivity(i);
        }
        else {
            code = et.getText().toString();
            studentName = et2.getText().toString();

            if ("".equals(code) || "".equals(studentName)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error_text);
                builder.setMessage("Both fields are mandatory!");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Choice c = new Choice("Test", true);

                Intent i = new Intent(this, StudentStartTestActivity.class);
                i.putExtra("CODE", code);
                i.putExtra("CHOICE", c);
                i.putExtra("studentName", studentName);
                startActivity(i);
            }
        }
    }


}
