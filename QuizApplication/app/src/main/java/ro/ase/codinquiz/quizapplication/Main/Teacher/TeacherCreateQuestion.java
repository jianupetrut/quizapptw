package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SpinnerHintAdapter;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherCreateQuestion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner QuestionCategorySpinner = null;
    List<String> categories=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QuestionCategorySpinner = (Spinner) findViewById(R.id.spQuestionCategory2);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        //Display hint text "Question categories" for spinner
         categories = new ArrayList<String>();
        categories.add("Category 2");
        categories.add("Category 3");
        categories.add("Question Category");  // add hint as last item

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        QuestionCategorySpinner.setAdapter(adapter);
      //  SpinnerHintAdapter adapter = new SpinnerHintAdapter(this, objects, android.R.layout.simple_spinner_item);


       // QuestionCategorySpinner.setAdapter(adapter);
     //  QuestionCategorySpinner.setSelection(adapter.getCount()-1); // show hint
    }

    public void saveQuestion() {
        List<Answer> answerList=null;
        TextView answer1TW=findViewById(R.id.textAnswer1);
        TextView answer2TW=findViewById(R.id.textAnswer2);
        TextView answer3TW=findViewById(R.id.textAnswer3);
        TextView answer4TW=findViewById(R.id.textAnswer4);
        CheckBox box1=findViewById(R.id.checkBoxAnswer1);
        CheckBox box2=findViewById(R.id.checkBoxAnswer2);
        CheckBox box3=findViewById(R.id.checkBoxAnswer3);
        CheckBox box4=findViewById(R.id.checkBoxAnswer4);
        boolean answer1isCorrect=false,answer2isCorrect=false,answer3isCorrect=false,answer4isCorrect=false;
        Answer answer1=new Answer(answer1TW.getText().toString());
        if (box1.isChecked()) {
            answer1.setCorrect(true);
        }

        Answer answer2=new Answer(answer2TW.getText().toString());
        if(box2.isChecked()) {
            answer2.setCorrect(true);
        }
        Answer answer3=new Answer(answer3TW.getText().toString());

        if(box3.isChecked()) {
            answer3.setCorrect(true);
        }

        Answer answer4=new Answer(answer4TW.getText().toString());
        if(box4.isChecked()) {
            answer4.setCorrect(true);
        }
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);
        TextView cat=findViewById(R.id.etCategory);
        if("".equals(cat.getText().toString())) {
            Question question = new Question(QuestionCategorySpinner.getSelectedItem().toString(), answerList, null);
        }
        else
        {
            categories.add(cat.getText().toString());
            Question question = new Question(cat.getText().toString(), answerList, null);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher_create_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_create_new_test) {
            Intent intent =new Intent(this,TeacherCreateTest.class);
            startActivity(intent);
        } else if (id == R.id.nav_existing_tests) {

        } else if (id == R.id.nav_create_new_question) {
            Intent intent =new Intent(this,TeacherCreateQuestion.class);
            startActivity(intent);
        } else if (id == R.id.nav_existing_questions) {
            Intent intent =new Intent(this,TeacherExistingQuestions.class);
            startActivity(intent);
        } else if (id == R.id.nav_assignment_history) {
            Intent intent =new Intent(this,TeacherSeeHistory.class);
            startActivity(intent);
        } else if (id == R.id.nav_leave_feedback) {
            Intent intent =new Intent(this,TeacherFeedbackActivity.class);
            startActivity(intent);
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
