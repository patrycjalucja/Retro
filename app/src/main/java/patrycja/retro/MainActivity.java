package patrycja.retro;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import patrycja.retro.Detal;
import patrycja.retro.DetalActivity;
import patrycja.retro.Lista;
import patrycja.retro.R;


public class MainActivity extends AppCompatActivity implements Lista.OverviewFragmentActivityListener {
    public ArrayAdapter<String> adapter;
    public ArrayList<String> arrayList;
    String m;
    public EditText txtinput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        // items[1] = "ggg";
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.txtitem, arrayList){
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


                String nowy = txtinput.getText().toString();
                arrayList.add(nowy);
                adapter.notifyDataSetChanged();
                txtinput.setText("");

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                showToast("super");
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    doDetalu(adapter.getItem(arg2));
                }

            }

        });

    }

    void doDetalu(String arg) {


        Intent n = new Intent(this, DetalActivity.class);
        n.putExtra("msg", arg);
        startActivity(n);
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






}
