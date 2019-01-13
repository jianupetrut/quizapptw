
package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import ro.ase.codinquiz.quizapplication.R;

public class SliderAdapter extends PagerAdapter {
    Context context;

    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context=context;
    }
    public String[] slide_descriptions={
                    "QUESTION 1\n" +
                    "QUESTION 2\n" +
                    "QUESTION 3\n" +
                    "QUESTION 4\n" +
                    "QUESTION 5\n" +
                    "QUESTION 6\n",
                    "QUESTION 1\n" +
                    "QUESTION 2\n" +
                    "QUESTION 3\n" +
                    "QUESTION 4\n" +
                    "QUESTION 5\n" +
                    "QUESTION 6\n",
                    "QUESTION 1\n" +
                    "QUESTION 2\n" +
                    "QUESTION 3\n" +
                    "QUESTION 4\n" +
                    "QUESTION 5\n" +
                    "QUESTION 6\n",
                    "QUESTION 1\n" +
                    "QUESTION 2\n" +
                    "QUESTION 3\n" +
                    "QUESTION 4\n" +
                    "QUESTION 5\n" +
                    "QUESTION 6\n",
                    "QUESTION 1\n" +
                    "QUESTION 2\n" +
                    "QUESTION 3\n" +
                    "QUESTION 4\n" +
                    "QUESTION 5\n" +
                    "QUESTION 6\n"

    };
    public String[] slide_headings={
            "TEST 1",
            "TEST 2",
            "TEST 3",
            "TEST 4",
            "TEST 5"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
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
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideQuestions = (TextView)view.findViewById(R.id.question_list);
        slideHeading.setText(slide_headings[position]);
        slideQuestions.setText(slide_descriptions[position]);
       // Button btn = (Button)view.findViewById(R.id.btnSelectTest);
       // btn.setText("SELECT");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
