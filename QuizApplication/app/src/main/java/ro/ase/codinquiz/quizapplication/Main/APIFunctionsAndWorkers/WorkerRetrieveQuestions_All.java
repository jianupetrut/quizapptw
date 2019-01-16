package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;

public class WorkerRetrieveQuestions_All extends AsyncTask<List<Question>,Integer,List<Question>> {

    ArrayList<Question> questionList=new ArrayList<>();
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveQuestions_All(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected List<Question> doInBackground(List<Question>... questions) {
        functions.retrieveQuestion_All(questionList);
        questions[0]=questionList;
        return questionList;
    }
}
