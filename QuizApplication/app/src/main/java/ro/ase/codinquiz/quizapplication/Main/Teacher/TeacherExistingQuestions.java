package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveQuestions_All;
import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveTest_All;
import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.QuestionsListAdapter;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherExistingQuestions extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final int[] SelectedQuestionId = new int[1];
    List<Question> questionArrayList=new ArrayList<>();
    ListView existingQuestionsListView;
    QuestionsListAdapter adapter;
    Spinner spinner;
    List<Test> testArrayList=new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    List<String> stringArrayList=new ArrayList<>();
    boolean questionSelected=false;
    boolean testSelected=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_existing_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner=findViewById(R.id.spinnerSelectTest);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //------------------mockup data-------------------
//        List<Answer> ansList1=new ArrayList<>();
//        List<Answer> ansList2=new ArrayList<>();
//        Answer ansList1answer1=new Answer(1,"Answer 1",true,1);
//        Answer ansList1answer2=new Answer(2,"Answer 2",false,1);
//        Answer ansList1answer3=new Answer(3,"Answer 3",false,1);
//        Answer ansList1answer4=new Answer(4,"Answer 4",false,1);
//        ansList1.add(ansList1answer1);
//        ansList1.add(ansList1answer2);
//        ansList1.add(ansList1answer3);
//        ansList1.add(ansList1answer4);
//        Answer ansList2answer1=new Answer(5,"Answer 1",false,2);
//        Answer ansList2answer2=new Answer(6,"Answer 2",true,2);
//        Answer ansList2answer3=new Answer(7,"Answer 3",true,2);
//        Answer ansList2answer4=new Answer(8,"Answer 4",false,2);
//        ansList2.add(ansList2answer1);
//        ansList2.add(ansList2answer2);
//        ansList2.add(ansList2answer3);
//        ansList2.add(ansList2answer4);
//        Question q1=new Question("Cat1","Text for question 1",ansList1,null);
//        Question q2=new Question("Cat1","text for question2",ansList2,null);
//
//        questionArrayList.add(q1);
//        questionArrayList.add(q2);
        WorkerRetrieveQuestions_All workerRetrieveQuestionsAll=new WorkerRetrieveQuestions_All(getApplicationContext());
        try {
            questionArrayList=workerRetrieveQuestionsAll.execute(questionArrayList).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WorkerRetrieveTest_All workerRetrieveTestAll=new WorkerRetrieveTest_All(getApplicationContext());
        try {
            testArrayList=workerRetrieveTestAll.execute(testArrayList).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Test t:testArrayList
             ) {
            stringArrayList.add(t.getTestName());

        }
        //------------------mockup data-------------------


        existingQuestionsListView=findViewById(R.id.listView_ExistingQuestions);
        adapter=new QuestionsListAdapter(getApplicationContext(),R.layout.item_question,questionArrayList);
        existingQuestionsListView.setAdapter(adapter);
        existingQuestionsListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(existingQuestionsListView.getSelectedItem() != null) {
                    SelectedQuestionId[0] =(existingQuestionsListView.getSelectedItemPosition());
                    questionSelected=true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                    questionSelected=false;
            }
        });
        adapter.notifyDataSetChanged();
        spinner = findViewById(R.id.spinnerSelectTest);

        spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                stringArrayList);
        spinner.setAdapter(spinnerAdapter);
        int selectedItemId=0;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                testSelected=true;
                existingQuestionsListView.setSelection(existingQuestionsListView.getSelectedItemPosition());
                existingQuestionsListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                existingQuestionsListView.setSelector(android.R.color.holo_blue_light);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                testSelected=false;
            }
        });
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
        getMenuInflater().inflate(R.menu.teacher_existing_questions, menu);
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
        }else if (id == R.id.nav_see_statistics) {
            Intent intent = new Intent(this, TeacherSeeStatistics.class);
            startActivity(intent);
        }else if (id == R.id.nav_leave_feedback) {
            Intent intent =new Intent(this,TeacherFeedbackActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, TeacherShareTest.class);
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
    public void onAddClick(View view){

        if(testSelected&&questionSelected){
            for (Question q: questionArrayList) {
                if (q.getId()==SelectedQuestionId[0]){
                    for (Test t:testArrayList
                         ) {
                        t.getQuestionList().add(q);
                        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }
        else{
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Please select a test and a question!");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}
