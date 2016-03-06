package patrycja.retro;

import android.app.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

/**
 * Created by Patrycja on 2016-03-03.
 */
public class DetalActivity extends AppCompatActivity implements AsyncResponse{
   TextView textView;
    Button back2;
  ListView listview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
       // setContentView(R.layout.activity_detal);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

       // elementview = getView();
        textView = (TextView) findViewById(R.id.textView);
        back2 = (Button) findViewById(R.id.back);
        listview = (ListView) findViewById(R.id.listview);
        // jeżeli użytkownik będzie w orientacji landscape, należy zamknąć
        // aktywność
      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        final Context context = this;


        // pobieramy dane wysłane przez aktywność główną
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString("msg");
            getFragmentManager().beginTransaction().replace(R.id.fragment,
                    new Detal()).commit();
         /*   Detal detalFragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);*/
            //String nowy = url.getText().toString();
            PostResponseAsyncTask task = new PostResponseAsyncTask(DetalActivity.this, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    try{
                    Gson gson = new Gson();
                       //String escaped = s.replaceAll(""", '~');
                       // String n = escaped.replaceAll("~", "'");
                     Summary dane = gson.fromJson("{\n" +
                             "   \"results\" : [\n" +
                             "      {\n" +
                             "         \"address_components\" : [\n" +
                             "            {\n" +
                             "               \"long_name\" : \"Gosprzydowa\",\n" +
                             "               \"short_name\" : \"Gosprzydowa\",\n" +
                             "               \"types\" : [ \"locality\", \"political\" ]\n" +
                             "            },\n" +
                             "            {\n" +
                             "               \"long_name\" : \"Gnojnik\",\n" +
                             "               \"short_name\" : \"Gnojnik\",\n" +
                             "               \"types\" : [ \"administrative_area_level_3\", \"political\" ]\n" +
                             "            },\n" +
                             "            {\n" +
                             "               \"long_name\" : \"brzeski\",\n" +
                             "               \"short_name\" : \"brzeski\",\n" +
                             "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n" +
                             "            },\n" +
                             "            {\n" +
                             "               \"long_name\" : \"małopolskie\",\n" +
                             "               \"short_name\" : \"małopolskie\",\n" +
                             "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                             "            },\n" +
                             "            {\n" +
                             "               \"long_name\" : \"Polska\",\n" +
                             "               \"short_name\" : \"PL\",\n" +
                             "               \"types\" : [ \"country\", \"political\" ]\n" +
                             "            }\n" +
                             "         ],\n" +
                             "         \"formatted_address\" : \"Gosprzydowa, Polska\",\n" +
                             "         \"geometry\" : {\n" +
                             "            \"location\" : {\n" +
                             "               \"lat\" : 49.87201959999999,\n" +
                             "               \"lng\" : 20.5822672\n" +
                             "            },\n" +
                             "            \"location_type\" : \"APPROXIMATE\",\n" +
                             "            \"viewport\" : {\n" +
                             "               \"northeast\" : {\n" +
                             "                  \"lat\" : 49.88639999999999,\n" +
                             "                  \"lng\" : 20.614282\n" +
                             "               },\n" +
                             "               \"southwest\" : {\n" +
                             "                  \"lat\" : 49.85763490000001,\n" +
                             "                  \"lng\" : 20.5502524\n" +
                             "               }\n" +
                             "            }\n" +
                             "         },\n" +
                             "         \"place_id\" : \"ChIJhxcV114gFkcR-7nnsQfq0gI\",\n" +
                             "         \"types\" : [ \"locality\", \"political\" ]\n" +
                             "      }\n" +
                             "   ],\n" +
                             "   \"status\" : \"OK\"\n" +
                             "}\n" +
                             "\n", Summary.class);
                       // showToast(s);
                            //s = dane.toString();
                            textView.setText(s.toString());
                       // textView.setText(dane);
                    }catch(NullPointerException e){
                        showToast(s);
                    }
                }
            });
            task.execute("http://maps.googleapis.com/maps/api/geocode/json?address=" + url + "&sensor=true");
            //textView.setText(url);


        }
    }



    public void goBack(View v){
        //Intent n = new Intent(this, patrycja.retro.MainActivity.class);
      //  n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(n);
       //TODO czyści listę, usunąć to
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();

    }
 /*   public View getView(int position, View convertView, ViewGroup parent) {
        // Set view in case it's null
        View elementView = convertView;
        if (elementView == null) {
            elementView = getLayoutInflater().inflate(R.layout.activity_detal, parent, false);
            Log.i("INFLATING VIEW", "true");
        }
        return elementView;
    } */
        @Override
    public void processFinish(String s) {

    }



}

