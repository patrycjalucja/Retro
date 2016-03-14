package patrycja.retro;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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


public class MainActivity extends AppCompatActivity implements List.OverviewFragmentActivityListener {
    public ArrayAdapter<String> adapter;
    public ArrayList<String> arrayList;
    String temp = "";
    public EditText txtinput;
    public ArrayList<String> list = new ArrayList<String>();
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.txtitem, arrayList) {
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
        txtinput = (EditText) findViewById(R.id.txtinput);
        Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                temp = txtinput.getText().toString();
                String t = temp;
                PostResponseAsyncTask task = new PostResponseAsyncTask(MainActivity.this, new AsyncResponse() {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                toDetal(adapter.getItem(arg2));


            }

        });

    }

    void toDetal(String arg) {


        Intent n = new Intent(this, DetalActivity.class);

        int a = arrayList.indexOf(arg);
        if (list.get(a).isEmpty()) {

            showToast("Nie ma takiego miasta");

        } else {
            n.putExtra("msg", list.get(a));
            startActivity(n);
        }
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // ta metoda pochodzi z OverviewFragmentActivityListener

    public void onItemSelected(String msg) {
     /*   Detal fragment = (Detal) getFragmentManager()
                .findFragmentById(R.id.detalFragment);
        // jezeli fragment istnieje w tej aktywnosci to znaczy, ze jestesmy w trybie landscape
        if (fragment != null && fragment.isInLayout()) {
            fragment.settText(msg);
        } else { */
        // w trybie portrait wywolujemy druga aktywnosc
        Intent intent = new Intent(getApplicationContext(),
                DetalActivity.class);
        intent.putExtra("msg", msg);
        startActivity(intent);
        //  }
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
