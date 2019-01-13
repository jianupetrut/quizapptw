package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;

public class WorkerRetrieveTest_All extends AsyncTask<List<Test>,Integer,List<Test>> {

    List<Test> testList=new ArrayList<>();
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveTest_All(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected List<Test> doInBackground(List<Test>... lists) {
        testList= functions.retrieveTest_All(2);
        lists[0]=testList;
        return testList;
    }
}
