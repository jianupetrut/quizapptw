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
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SpinnerHintAdapter;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Models.Question;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherCreateTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    Spinner QuestionCategorySpinner = (Spinner) findViewById(R.id.spQuestionCategory);
    private ArrayList<Question> questionsList; // pe care il populam din baza de date in functie de id-ul autorului si drepturile pe care le are pentru intrebarile altor autori

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Display hint text "Question categories" for spinner
        List<String> objects = new ArrayList<String>();
        objects.add("Category 1");
        objects.add("Category 2");
        objects.add("Category 3");
        objects.add("Question Category");  // add hint as last item

        SpinnerHintAdapter adapter = new SpinnerHintAdapter(this, objects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // QuestionCategorySpinner.setAdapter(adapter);
      //  QuestionCategorySpinner.setSelection(adapter.getCount()); // show hint

        ListView questionsListView = (ListView)findViewById(R.id.lvQuestions);

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
        getMenuInflater().inflate(R.menu.teacher_create_test, menu);
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
