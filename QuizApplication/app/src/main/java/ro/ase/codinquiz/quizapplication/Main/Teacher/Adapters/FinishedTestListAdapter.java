package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.R;

public class FinishedTestListAdapter extends ArrayAdapter<FinishedTest> {
    private Context context;
    private int resource;
    public FinishedTestListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FinishedTest> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String username=getItem(position).getUsername();
        String testName=getItem(position).getTestName();
        int score=getItem(position).getScore();
        Calendar calendar= new GregorianCalendar(2019,1,1);
     FinishedTest finishedTest=new FinishedTest(1,1,score,username,testName,calendar.getTime());
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);
        TextView textViewTest=(TextView)convertView.findViewById(R.id.testTextView);
        TextView textViewName=(TextView)convertView.findViewById(R.id.nameTextView);
        TextView textViewGrade=(TextView)convertView.findViewById(R.id.gradeTextView);

        textViewTest.setText(testName);
        textViewName.setText(username);
        String s = Float.toString(score);
        textViewGrade.setText(s);

        return convertView;
    }
}
