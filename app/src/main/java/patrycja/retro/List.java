package patrycja.retro;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by Patrycja on 2016-03-03.
 */
public class List extends Fragment {
    public ArrayList<String> arrayList;
    ListView listView;
     Button add;
    //String temp;
    EditText txtinput;
    ArrayList<String> list = new ArrayList<String>();
    private OverviewFragmentActivityListener listener;
    public ArrayAdapter<String> adapter;
    String temp = new String () ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lista, container,
                false);
        arrayList = new ArrayList<String>();
        add = (Button) view.findViewById(R.id.add);
        listView = (ListView) view.findViewById(R.id.listview);
        txtinput = (EditText) view.findViewById(R.id.txtinput);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.lista_item, R.id.txtitem, arrayList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.parseColor("#78a8d4"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#b9d2e9"));
                }

                return view;
            }
        };
        listView.setAdapter(adapter);
      
//       listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                temp = txtinput.getText().toString();
                String t = temp;
                PostResponseAsyncTask task = new PostResponseAsyncTask(getActivity(), new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        try {
                            try {
                                list.add(findData(s));
                                arrayList.add(temp);
                                adapter.notifyDataSetChanged();
                            } catch (StringIndexOutOfBoundsException u) {


                                showToast("Nie ma takiego miasta");
                            }

                        } catch (NullPointerException e) {
                            showToast(s);
                        }
                    }
                });
                try {
                    t = URLEncoder.encode(t, "UTF-8");
                } catch (UnsupportedEncodingException x) {

                }
                task.execute("http://maps.googleapis.com/maps/api/geocode/json?address=" + t + "&sensor=true");


                txtinput.setText("");


            }
        });




        //listView = (ListView) findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                toDetal(adapter.getItem(arg2));


            }

        });

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
11

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
            throw new ClassCastException(activity.toString() + " musi implementować interfejs"
            );
        }
    }

    // metoda wysyła dane do aktywności
    public void updateDetail(String msg) {
        listener.onItemSelected(msg);
    }

    void toDetal(String arg) {
        int a = arrayList.indexOf(arg);

        updateDetail(list.get(a));


        /*int a = arrayList.indexOf(arg);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent n = new Intent(getActivity(), DetalActivity.class);
                n.putExtra("msg", list.get(a));
                startActivity(n);

        }
        else{

               // MainActivity.messg.putString("msg", list.get(a));
            Detal fragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);
            // jezeli fragment istnieje w tej aktywnosci to znaczy, ze jestesmy w trybie landscape
            if (fragment != null && fragment.isInLayout()) {
                fragment.settText(list.get(a));
            }
                //  Fragment detal = new Fragment();
              /* Bundle bundle = new Bundle();
                bundle.putString("msg", list.get(a));
                .setArguments(bundle);
                Detal fragment = (Detal) getFragmentManager()
                        .findFragmentById(R.id.detalFragment);
                // jezeli fragment istnieje w tej aktywnosci to znaczy, ze jestesmy w trybie landscape
                if (fragment != null && fragment.isInLayout()) {
                    fragment.settText(list.get(a));
                } */


       // } */
    }


    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

    }


    public String findData(String s) {
        String str = "long_name";
        String result = "";
        for (int i = 0; i < 5; i++) {
            int where = s.indexOf(str);
            s = s.substring(where + 13);
            int where2 = s.indexOf(",");
            String temp = s.substring(1, where2 - 1);


            switch (i) {
                case 0: {
                    result = result + "\n " + "Miasto: " + temp;
                    break;
                }
                case 1: {
                    result = result + "\n " + "Gmina: " + temp;
                    break;
                }
                case 2: {
                    result = result + "\n " + "Powiat: " + temp;
                    break;
                }
                case 3: {
                    result = result + "\n " + "Województwo: " + temp;
                    break;
                }
                case 4: {
                    result = result + "\n " + "Kraj: " + temp;
                    break;
                }
                default:
                    break;
            }

        }

        return result;

    }
}

