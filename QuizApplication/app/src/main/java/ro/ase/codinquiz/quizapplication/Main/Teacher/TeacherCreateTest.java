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

import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveCategories_All;
import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.QuestionsAdapter;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SpinnerHintAdapter;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherCreateTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Spinner QuestionCategorySpinner ;
    private ArrayList<Question> questionsList; // pe care il populam din baza de date in functie de id-ul autorului si drepturile pe care le are pentru intrebarile altor autori
    public QuestionsAdapter questionsAdapter;
    private ArrayAdapter<String> adapter;
    private  ListView questionsListView;

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



        questionsList = new ArrayList<>();

        Question q1=new Question("Cat1","Text for question 1",null,null);
        Question q2=new Question("Cat1","text for question2",null,null);
        questionsList.add(q1);
        questionsList.add(q2);

        List<Category> categoryList=new ArrayList<>();
        //Display hint text "Question categories" for spinner


        WorkerRetrieveCategories_All workerRetrieveCategoriesAll=new WorkerRetrieveCategories_All(getApplicationContext());
        try {
            categoryList=workerRetrieveCategoriesAll.execute(categoryList).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> objects = new ArrayList<String>();
        for (Category c :
                categoryList) {
            objects.add(c.getName());
        }

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


    public void saveTest(View view) {
        List<Question> questionList=null;

        Switch shuffleSw = findViewById(R.id.switchShuffle);
        Switch feedbackSW = findViewById(R.id.switchFeedback);
        Switch resultSw = findViewById(R.id.switchResult);


        Boolean shuffle = shuffleSw.isChecked();
        Boolean feedback = feedbackSW.isChecked();
        Boolean result = resultSw.isChecked();
        Boolean oneWay = true;

        for (int i = 0; i < questionsListView.getCount(); i++) {
            CheckBox cb =  questionsListView.getChildAt(i).findViewById(R.id.liCheckBox);
            TextView tv =  questionsListView.getChildAt(i).findViewById(R.id.liQuestionNo);
            String questionId = tv.getText().toString().substring(12);
            if (cb.isChecked()) {
                questionList.add(questionList.get(Integer.parseInt(questionId)));
            }
        }

        int time =30;
        //Test test = new Test(id generat din baza de date, "Test no. id", questionList, shuffle,  feedback, result,  time, oneWay)
            //push test to db

        Toast.makeText(this,"A new test has successfully been created",Toast.LENGTH_LONG).show();
        Intent intent =new Intent(this,TeacherExistingTests.class);
        startActivity(intent);
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
            Intent intent =new Intent(this,TeacherExistingTests.class);
            startActivity(intent);
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
        }else if (id == R.id.nav_see_statistics) {
            Intent intent = new Intent(this, TeacherSeeStatistics.class);
            startActivity(intent);
        }else if (id == R.id.nav_rate_app) {
            Intent intent = new Intent(this, ToDoActivity2.class);
            startActivity(intent);
        }else if (id == R.id.nav_contact_us) {
            Intent intent = new Intent(this, ToDoActivity4.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
