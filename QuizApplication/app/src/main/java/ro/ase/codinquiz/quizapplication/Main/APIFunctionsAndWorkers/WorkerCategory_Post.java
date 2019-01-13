package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;

public class WorkerCategory_Post extends AsyncTask<Category,Integer,Category> {

    Category c;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerCategory_Post(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected Category doInBackground(Category... categories) {

        c=categories[0];
        functions.postCategory(c);
        return c;
    }
}
