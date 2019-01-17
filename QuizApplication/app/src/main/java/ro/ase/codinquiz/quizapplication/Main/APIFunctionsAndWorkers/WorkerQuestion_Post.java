package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;

public class WorkerQuestion_Post extends AsyncTask<Question,Integer,Question> {

    Question q;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerQuestion_Post(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected Question doInBackground(Question... questions) {


        q=questions[0];
        functions.postQuestion(q);
        return q;
    }
}
