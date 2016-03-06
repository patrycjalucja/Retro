package patrycja.retro;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Patrycja on 2016-03-03.
 */
public class Lista extends Fragment {

    private OverviewFragmentActivityListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // przypisujemy layout do fragmentu
        View view = inflater.inflate(R.layout.lista, container,
                false);



        // definiujemy listener dla poszczególnych elementów (buttonów)
   /*     View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (v.getId()) {
                  case R.id.button1:
                        updateDetail("Szczegółowe informacje o elemencie pierwszym.");
                        break;

                    default:
                        break;
                }
            }
        };

        // przypisujemy elementom clickListener
        Button button1 = (Button) view.findViewById(R.id.button1);


        button1.setOnClickListener(clickListener);
*/
        return view;
    }




    // interfejs, który będzie implementować aktywność
    public interface OverviewFragmentActivityListener {
        public void onItemSelected(String msg);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OverviewFragmentActivityListener) {
            listener = (OverviewFragmentActivityListener) activity;
        } else {
            throw new ClassCastException( activity.toString() + " musi implementować interfejs bla bla"
                   );
        }
    }

    // metoda wysyła dane do aktywności
    public void updateDetail(String msg) {
        listener.onItemSelected(msg);
    }



}
