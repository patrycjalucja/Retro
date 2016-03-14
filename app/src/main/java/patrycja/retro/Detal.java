package patrycja.retro;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Patrycja on 2016-03-03.
 */
public class Detal extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater
                .inflate(R.layout.detal, container, false);
        return view;
    }

    public void settText(String txt) {

    }


}



