package ro.ase.codinquiz.quizapplication.Main.Helpers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ro.ase.codinquiz.quizapplication.R;

public class ImageFragment extends Fragment {
    int fragVal;
    public static ImageFragment init(int val) {
        ImageFragment truitonFrag = new ImageFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layoutView = inflater.inflate(R.layout.fragment_image, container,
                    false);
            View tv = layoutView.findViewById(R.id.text);
            ((TextView) tv).setText("Question #" + fragVal);
            return layoutView;
    }
}
