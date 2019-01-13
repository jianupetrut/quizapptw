
package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.R;

public class SliderAdapter extends PagerAdapter {
    Context context;

    LayoutInflater layoutInflater;
    List<Test> tests ;



    public SliderAdapter(Context context,List<Test> tests){
        this.context=context;
        this.tests=tests;
    }


    @Override
    public int getCount() {
        return tests.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        for (Test test :
                tests) {


            TextView testIdTextView = (TextView) view.findViewById(R.id.existingTestID);
            testIdTextView.setText(""+test.getId());


            TextView testNameTextView = (TextView) view.findViewById(R.id.existingTestName);
            testNameTextView.setText(test.getTestName());

            TextView testQuestionTextView = (TextView) view.findViewById(R.id.existingTestQuestion);
            StringBuilder textForQuestions=new StringBuilder("");
            for (Question q :
                    test.getQuestionList()) {
                textForQuestions.append(q.getText());
            }
            testQuestionTextView.setText(textForQuestions);

        }



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
