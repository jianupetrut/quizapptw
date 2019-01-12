package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.R;

public class QuestionsAdapter extends ArrayAdapter<Question> {
    public QuestionsAdapter( Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
    }


    public View getView(int position,  View convertView,  ViewGroup parent) {
        if(convertView ==  null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.question_listview_item, null);
        }

        Question question = getItem(position);

        TextView questionNoTextView = convertView.findViewById(R.id.liQuestionNo);
        questionNoTextView.setText(R.string.questionNo + question.getId());
        TextView questionContentTextView = convertView.findViewById(R.id.liQuestionContent);
        questionContentTextView.setText(question.getText());
        CheckBox checkBox = convertView.findViewById(R.id.liCheckBox);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }

        return convertView;
    }
}
