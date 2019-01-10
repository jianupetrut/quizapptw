package ro.ase.codinquiz.quizapplication.Main.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.R;

public class FinishedTestsAdapter extends ArrayAdapter<FinishedTest> {


    public FinishedTestsAdapter(@NonNull Context context, int resource, @NonNull List<FinishedTest> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==  null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_finishedtest, null);
        }
        FinishedTest test=getItem(position);
        //#,name,date,score
        TextView numberTW =convertView.findViewById(R.id.finishedTestNumberTextView);
        numberTW.setText("#"+test.getId());
        TextView testIdTW=convertView.findViewById(R.id.finishedTestNameTextView);
        testIdTW.setText(test.getTest_id());
        DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        TextView dateTW=convertView.findViewById(R.id.finishedTestDateTextView);
        dateTW.setText(df.format(test.getDate()));
        TextView scoreTW=convertView.findViewById(R.id.finishedTestScoreTextView);
        scoreTW.setText(String.format(test.getScore()+""));

        return convertView;
    }

}
