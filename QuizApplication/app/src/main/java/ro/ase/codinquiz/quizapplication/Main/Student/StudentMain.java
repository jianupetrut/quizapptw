package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveFinishedTest_All;
import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveTest;
import ro.ase.codinquiz.quizapplication.Main.Adapters.FinishedTestsAdapter;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity3;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;

import ro.ase.codinquiz.quizapplication.R;

public class StudentMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<FinishedTest> arrayOfWebData=new ArrayList<FinishedTest>();
    ListView assignmentHistoryListView;

    private FinishedTestsAdapter adapter;

    static ArrayList<String> resultRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assignmentHistoryListView=(ListView)findViewById(R.id.assignment_history_listview);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        assignmentHistoryListView=(ListView)findViewById(R.id.assignment_history_listview);
        WorkerRetrieveFinishedTest_All worker=new WorkerRetrieveFinishedTest_All(getApplicationContext());
        try {
            arrayOfWebData=worker.execute(arrayOfWebData).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter=new FinishedTestsAdapter(this,R.layout.item_finishedtest,arrayOfWebData);
        adapter.notifyDataSetChanged();
        assignmentHistoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Test test;
        WorkerRetrieveTest workerRetrieveTest=new WorkerRetrieveTest(getApplicationContext());
        try {
            test=workerRetrieveTest.execute().get().get(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
        getMenuInflater().inflate(R.menu.student_main, menu);
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

    public void joinTest(View view){
        Intent intent = new Intent(this, JoinTestActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {
            Intent intent =new Intent(this,StudentMain.class);
            startActivity(intent);
        } else if (id == R.id.nav_new_assignment) {
            Intent intent =new Intent(this,StudentStartTestActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_leave_feedback) {
            Intent intent =new Intent(this,StudentFeedbackActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_see_school_week) {
            Intent intent =new Intent(this,ToDoActivity3.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate_app) {
            Intent intent =new Intent(this,ToDoActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact_us) {
            Intent intent =new Intent(this,ToDoActivity4.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
