package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;

public class WorkerFinishedTest_Post extends AsyncTask<FinishedTest,Integer,FinishedTest> {

    FinishedTest finishedTest;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerFinishedTest_Post(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected FinishedTest doInBackground(FinishedTest... finishedTests) {

        finishedTest=finishedTests[0];
        functions.postFinishedTest(finishedTest);
        return finishedTest;
    }
}
