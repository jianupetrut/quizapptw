package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerFinishedTest_Post;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Helpers.ArrayListFragment;
import ro.ase.codinquiz.quizapplication.Main.Helpers.ImageFragment;
import ro.ase.codinquiz.quizapplication.R;

public class StudentTestActivity extends AppCompatActivity {

    final static Test[] Test = new Test[1];
    final static int[] ITEMS = new int[1];
    static Boolean[] ISCORRECT ;
    MyAdapter mAdapter;
    ViewPager mPager;

    // CheckBox cb1,cb2,cb3,cb4;
    // TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Test test;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);
        Intent intent = getIntent();
        test = (Test) intent.getSerializableExtra("TEST");
        ITEMS[0] = test.getQuestionList().size();
        ISCORRECT= new Boolean[ITEMS[0]];
        Test[0]=test;
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
       for(int i=0;i<ITEMS[0];i++){
           Toast.makeText(this.getBaseContext()," "+ISCORRECT[i],Toast.LENGTH_SHORT).show();
       }
//        cb1=findViewById(R.id.checkBox_test_Answer1);
//        cb2=findViewById(R.id.checkBox_test_Answer2);
//        cb3=findViewById(R.id.checkBox_test_Answer3);
//        cb4=findViewById(R.id.checkBox_test_Answer4);
//        tw=findViewById(R.id.textView_test_questionText);
//        tw.setText(test.getQuestionList().get(0).getText());
//        cb1.setText(test.getQuestionList().get(0).getAnswerList().get(0).getText());
//        cb2.setText(test.getQuestionList().get(1).getAnswerList().get(1).getText());
//        cb3.setText(test.getQuestionList().get(2).getAnswerList().get(2).getText());
//        cb4.setText(test.getQuestionList().get(3).getAnswerList().get(3).getText());
    }
    public static void setISCORRECT(int position, Boolean value){
        ISCORRECT[position]=value;
    }
    public final int getITEMS() {
        return ITEMS[0];
    }

    public Test getTest() {
        return Test[0];
    }

    public void submit(View view){
        int score=0;
        for(int i=0;i<ISCORRECT.length;i++){
            if(ISCORRECT[i]==Boolean.TRUE){
                score++;
            }
        }
        score=score*100/ITEMS[0];
        FinishedTest finishedTest=new FinishedTest();
        finishedTest.setScore(score);
        DateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        finishedTest.setDate(Calendar.getInstance().getTime());
        finishedTest.setTestName(this.getTest().getTestName());
        finishedTest.setUsername("user0");
        finishedTest.setTest_id(1);
        WorkerFinishedTest_Post workerFinishedTestPost=new WorkerFinishedTest_Post(getApplicationContext());
        workerFinishedTestPost.execute(finishedTest);
        Toast.makeText(this, "Test finished. Your score:"+score+"%",
                Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
//            switch (position) {
//                case 0: // Fragment # 0 - This will show image
//                     return ImageFragment.init(position);
//
//                case 1: // Fragment # 1 - This will show image
//                      return ImageFragment.init(position);
//
//                default:// Fragment # 2-9 - Will show list
//                      return ArrayListFragment.init(position, Test[0]);
//            }
            if(Test[0].getQuestionList().get(position).getImage()!=null){
                return ImageFragment.init(position);

            }else{
                ArrayListFragment fragment=new ArrayListFragment();
                return fragment.init(position,Test[0]);
              //  return ArrayListFragment.init(position, Test[0]);
            }
        }

        @Override
        public int getCount() {
            return ITEMS[0];
        }
    }


}