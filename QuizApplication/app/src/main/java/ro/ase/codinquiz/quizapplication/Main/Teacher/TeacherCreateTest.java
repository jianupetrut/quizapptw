package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.QuestionsAdapter;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SpinnerHintAdapter;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherCreateTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QuestionCategorySpinner= (Spinner) findViewById(R.id.spQuestionCategory);;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(90);

        questionsList = new ArrayList<>();

        //Display hint text "Question categories" for spinner
        List<String> objects = new ArrayList<String>();
        objects.add("Category 1");
        objects.add("Category 2");
        objects.add("Category 3");
        objects.add("Question Category");  // add hint as last item

        //SpinnerHintAdapter adapter = new SpinnerHintAdapter(this, objects, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter = new ArrayAdapter<String>(TeacherCreateTest.this, R.layout.support_simple_spinner_dropdown_item, objects){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapter.setDropDownViewResource(R.layout.spinner_item);
        QuestionCategorySpinner.setAdapter(adapter);


        QuestionCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    String category = QuestionCategorySpinner.getSelectedItem().toString();
                    questionsList = new ArrayList<>();

                    //questionsList.add(Question); adauga intrebarile care au category = stringul de mai sus din db
                }

                if(QuestionCategorySpinner.getSelectedItem() != null) {
                    //update listview with questions of a certain category
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // QuestionCategorySpinner.setAdapter(adapter);
        //  QuestionCategorySpinner.setSelection(adapter.getCount()); // show hint


        questionsListView = (ListView)findViewById(R.id.lvQuestions);
        questionsAdapter = new QuestionsAdapter(getApplicationContext(),R.layout.question_listview_item, questionsList);
        questionsListView.setAdapter(questionsAdapter);

    }


    public void saveTest() {
        List<Question> questionList=null;

        Switch shuffleSw = findViewById(R.id.switchShuffle);
        Switch feedbackSW = findViewById(R.id.switchFeedback);
        Switch resultSw = findViewById(R.id.switchResult);
        NumberPicker numberPicker = findViewById(R.id.numberPicker);

        Boolean shuffle = shuffleSw.isChecked();
        Boolean feedback = feedbackSW.isChecked();
        Boolean result = resultSw.isChecked();
        Boolean oneWay = true;


        int time = numberPicker.getValue();
        //Test test = new Test(id generat din baza de date, "Test no. id", questionList, shuffle,  feedback, result,  time, oneWay)
            //push test to db
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
        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, TeacherShareTest.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
