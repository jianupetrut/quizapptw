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
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherSeeStatistics extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BarChart barChart;
    private ArrayList<String> tests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_see_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        barChart=(BarChart)findViewById(R.id.bargraph);
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(64f,5));
        barEntries.add(new BarEntry(58f,6));
        barEntries.add(new BarEntry(47f,5));
        barEntries.add(new BarEntry(60f,8));


        BarDataSet barDataSet = new BarDataSet(barEntries,"Tests");

        tests=new ArrayList<>();
        tests.add("Test 1");
        tests.add("Test 2");
        tests.add("Test 3");
        tests.add("Test 4");
        barChart.setFitsSystemWindows(true);
        Description description= new Description();
        description.setText("This is the chart where you can see the average grade of all tests");
        barChart.setDescription(description);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();

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
        getMenuInflater().inflate(R.menu.teacher_see_statistics, menu);
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
