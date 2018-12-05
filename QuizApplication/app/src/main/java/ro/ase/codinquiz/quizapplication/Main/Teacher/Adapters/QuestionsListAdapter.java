package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Teacher.Models.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question>{

    public QuestionsListAdapter(Context theContext, List<Question> strings, int theLayoutResId) {
        super(theContext, theLayoutResId, strings);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);


    }
}