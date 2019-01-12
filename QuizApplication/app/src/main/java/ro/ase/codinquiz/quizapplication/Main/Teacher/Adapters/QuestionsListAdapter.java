package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;


import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.R;

public class QuestionsListAdapter extends ArrayAdapter<Question>{


    public QuestionsListAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==  null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_question, null);
        }

        Question question=getItem(position);
        TextView idTW=convertView.findViewById(R.id.item_existingQuestions_idTW);
        TextView categoryTW=convertView.findViewById(R.id.item_existingQuestions_categoryTW);
        TextView textTW=convertView.findViewById(R.id.item_existingQuestions_textTW);
        idTW.setText(question.getId()+"");
        categoryTW.setText(question.getCategory());
        textTW.setText(question.getText());


        return convertView;
    }

}