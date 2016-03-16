package patrycja.retro;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Patrycja on 2016-03-03.
 */
public class Detal extends Fragment {

    TextView textView;

    TextView title;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater
                .inflate(R.layout.detal, container, false);

        return view;
    }


    public void settText(String txt) {
        textView = (TextView) getView().findViewById(R.id.textView);
        title = (TextView) getView().findViewById(R.id.title);
        title.setText("Opis miasta");

            textView.setText(txt);
    }


  /*  public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof List.OverviewFragmentActivityListener) {
            listener = (List.OverviewFragmentActivityListener) activity;
        } else {
            throw new ClassCastException( activity.toString() + " musi implementować interfejs:"+
                    OverviewFragment.OverviewFragmentActivityListener);
        }
    } */



}



