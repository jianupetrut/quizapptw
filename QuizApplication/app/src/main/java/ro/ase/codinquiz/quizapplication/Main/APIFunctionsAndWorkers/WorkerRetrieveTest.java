package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;

public class WorkerRetrieveTest extends AsyncTask<List<Test>,Integer,List<Test>> {

    List<Test> testList;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveTest(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected List<Test> doInBackground(List<Test>... lists) {
        testList.add(functions.retrieveTest(2));
        lists[0]=testList;
        return testList;
    }
}
