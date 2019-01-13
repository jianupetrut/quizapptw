package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Helpers.ArrayListFragment;
import ro.ase.codinquiz.quizapplication.Main.Helpers.ImageFragment;
import ro.ase.codinquiz.quizapplication.R;

public class StudentTestActivity extends AppCompatActivity {

    final static Test[] Test = new Test[1];
    final static int[] ITEMS = new int[1];
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
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        Test[0]=test;
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

    public final int getITEMS() {
        return ITEMS[0];
    }

    public Test getTest() {
        return Test[0];
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show image
                     return ImageFragment.init(position);

                case 1: // Fragment # 1 - This will show image
                      return ImageFragment.init(position);
                case 2: // Fragment # 1 - This will show image
                    return ArrayListFragment.init(position, Test[0]);
                default:// Fragment # 2-9 - Will show list
                      return ArrayListFragment.init(position, Test[0]);
            }
        }

        @Override
        public int getCount() {
            return ITEMS[0];
        }
    }


}