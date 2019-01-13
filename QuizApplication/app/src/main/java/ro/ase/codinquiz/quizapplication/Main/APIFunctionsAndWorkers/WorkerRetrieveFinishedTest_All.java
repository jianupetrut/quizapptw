package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.APIFunctions;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;

public class WorkerRetrieveFinishedTest_All extends AsyncTask<List<FinishedTest>,Integer,List<FinishedTest>> {

    List<FinishedTest> testList;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveFinishedTest_All(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected List<FinishedTest> doInBackground(List<FinishedTest>... lists) {
        testList=functions.retrieveFinishedTest_All("stud0");
        lists[0]=testList;
        return testList;
    }
}
