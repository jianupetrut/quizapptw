package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;

public class WorkerRetrieveCategories_All extends AsyncTask<List<Category>,Integer,List<Category>> {

    List<Category> categoryList;
    APIFunctions functions=new APIFunctions();
    Context ctx;
    public WorkerRetrieveCategories_All(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected List<Category> doInBackground(List<Category>... categories) {
        categoryList=functions.retrieveCategories_All();
        categories[0]=categoryList;
        return categoryList;
    }
}
