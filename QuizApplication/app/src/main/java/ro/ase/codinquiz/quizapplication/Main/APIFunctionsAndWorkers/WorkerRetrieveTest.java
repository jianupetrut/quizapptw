package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Test;

public class WorkerRetrieveTest extends AsyncTask<Test,Integer,Test> {

    Test t=new Test();
    int id;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveTest(Context ctx,int testCode){
        this.ctx=ctx;
        id=testCode;
    }
    @Override
    protected Test doInBackground(Test... lists) {
        t=functions.retrieveTestByID(id);
        lists[0]=t;
        return t;
    }
}
