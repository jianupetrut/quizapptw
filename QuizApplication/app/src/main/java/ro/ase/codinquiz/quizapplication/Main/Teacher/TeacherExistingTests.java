package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SliderAdapter;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherExistingTests extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //slider
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button selectTest;
    List<Test> testArrayList;

    ListView existingTestsListView;

    public List<Test> createExistingTests(){
        List<Test> existingTests = new ArrayList<>();
        Test test1 = new Test();
        Test test2 = new Test();
        Test test3 = new Test();
        Test test4 = new Test();
        Test test5 = new Test();
        test1.setId(0);
        test2.setId(1);
        test3.setId(3);
        test4.setId(4);
        test5.setId(5);
        test1.setTestName("Test 1");
        test2.setTestName("Test 2");
        test3.setTestName("Test 3");
        test4.setTestName("Test 4");
        test5.setTestName("Test 5");
        List<Question> questionArray = new ArrayList<>();
        //Question q1 = new Question("Weather","How's the weather?","sunny");
        List<Answer> ansList1=new ArrayList<>();
        List<Answer> ansList2=new ArrayList<>();
        Answer ansList1answer1=new Answer(1,"Answer 1",true,1);
        Answer ansList1answer2=new Answer(2,"Answer 2",false,1);
        Answer ansList1answer3=new Answer(3,"Answer 3",false,1);
        Answer ansList1answer4=new Answer(4,"Answer 4",false,1);
        ansList1.add(ansList1answer1);
        ansList1.add(ansList1answer2);
        ansList1.add(ansList1answer3);
        ansList1.add(ansList1answer4);
        Answer ansList2answer1=new Answer(5,"Answer 1",false,2);
        Answer ansList2answer2=new Answer(6,"Answer 2",true,2);
        Answer ansList2answer3=new Answer(7,"Answer 3",true,2);
        Answer ansList2answer4=new Answer(8,"Answer 4",false,2);
        ansList2.add(ansList2answer1);
        ansList2.add(ansList2answer2);
        ansList2.add(ansList2answer3);
        ansList2.add(ansList2answer4);
        //questionArray.add("Question 1");
        Question q1=new Question("Cat1","Text for question 1",ansList1,null);
        Question q2=new Question("Cat1","text for question2",ansList2,null);
        Question q3=new Question("Cat2","text for question1",ansList1,null);
        Question q4=new Question("Cat2","text for question1",ansList1,null);
        Question q5=new Question("Cat2","text for question1",ansList1,null);
        questionArray.add(q1);
        questionArray.add(q2);
        questionArray.add(q3);
        questionArray.add(q4);
        questionArray.add(q5);
        test1.setQuestionList(questionArray);
        test2.setQuestionList(questionArray);
        test3.setQuestionList(questionArray);
        test4.setQuestionList(questionArray);
        test5.setQuestionList(questionArray);
        existingTests.add(test1);
        existingTests.add(test2);
        existingTests.add(test3);
        existingTests.add(test4);
        existingTests.add(test5);
        return existingTests;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_existing_tests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //mockup data
        testArrayList=createExistingTests();

        //slider
        mSlideViewPager=(ViewPager)findViewById(R.id.slideViewPager);
        mDotLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter=new SliderAdapter(this,testArrayList);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

		  selectTest=(Button)findViewById(R.id.btnSelectTest);
				selectTest.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						openTestOptions();
					}
				});

    }

    private void openTestOptions() {
        Intent intent = new Intent(this,TeacherTestOptions.class);
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
        getMenuInflater().inflate(R.menu.teacher_existing_tests, menu);
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

    //dots indicator for slider
    public void addDotsIndicator(){
        mDots= new TextView[5];
        for(int i = 0; i < mDots.length; i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(50);
            mDots[i].setTextColor(getResources().getColor(R.color.CodinBlue));
            mDotLayout.addView(mDots[i]);
        }
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
