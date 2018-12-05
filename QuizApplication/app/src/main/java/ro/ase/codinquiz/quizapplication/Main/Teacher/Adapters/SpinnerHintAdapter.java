package ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class SpinnerHintAdapter  extends ArrayAdapter<String> {

    public SpinnerHintAdapter(Context theContext, List<String> strings, int theLayoutResId) {
        super(theContext, theLayoutResId, strings);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}