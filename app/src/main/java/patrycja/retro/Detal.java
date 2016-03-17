package patrycja.retro;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Detal extends Fragment {
    Button back;
    TextView textView;
    TextView title;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater
                .inflate(R.layout.detal, container, false);
        return view;
    }

    public void settText(String txt, boolean parent) {
        back = (Button) getView().findViewById(R.id.back);
        if (parent) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                getView().setVisibility(View.VISIBLE);
                back.setVisibility(Button.INVISIBLE);

            } else {
                getView().setVisibility(View.INVISIBLE);


            }
        }
        textView = (TextView) getView().findViewById(R.id.textView);
        title = (TextView) getView().findViewById(R.id.title);
        title.setText("Description");

        textView.setText(txt);
    }

}


