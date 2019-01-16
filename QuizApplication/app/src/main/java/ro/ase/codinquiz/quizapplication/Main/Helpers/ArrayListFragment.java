package ro.ase.codinquiz.quizapplication.Main.Helpers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Student.StudentTestActivity;
import ro.ase.codinquiz.quizapplication.R;

public class ArrayListFragment extends ListFragment {
    int fragNum;
    final static String[] finalAnswer=new String[1];
    final static ArrayList<String> arr= new ArrayList<String>();
    final static int[] qNumber = new int[1];
    final static Test[] TESTS =new Test[1];

   public static ArrayListFragment init(int val, Test test) {
        ArrayListFragment truitonList = new ArrayListFragment();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        args.putStringArrayList("questions",(ArrayList)test.getQuestionList());
        TESTS[0]=test;
        truitonList.setArguments(args);
        int index=0;
        if(arr.isEmpty()){
        for (Answer s:test.getQuestionList().get(index).getAnswerList()) {
            arr.add(test.getQuestionList().get(qNumber[0]).getAnswerList().get(index).getText());
            index++;
        }}
        qNumber[0]=val;
        return truitonList;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View layoutView = inflater.inflate(R.layout.fragment_pager_list,
                    container, false);
            View tv = layoutView.findViewById(R.id.text);
            ((TextView) tv).setText("Question: " + qNumber[0]);
            return layoutView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, arr));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        l.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        l.setSelector(android.R.color.background_light);
        Object object = (Object) l.getItemAtPosition(position);
        Toast.makeText(this.getContext(), "item"+object.toString(),
                Toast.LENGTH_SHORT).show();

        if(TESTS[0].getQuestionList().get(qNumber[0]).getAnswerList().get(position).getText().equals(object.toString())){
            StudentTestActivity.setISCORRECT(qNumber[0],Boolean.TRUE);
        }else
            StudentTestActivity.setISCORRECT(qNumber[0],Boolean.FALSE);

    }
}
