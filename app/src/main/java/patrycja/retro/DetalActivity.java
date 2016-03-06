package patrycja.retro;

import android.app.Activity;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

/**
 * Created by Patrycja on 2016-03-03.
 */
public class DetalActivity extends AppCompatActivity implements AsyncResponse{
   TextView textView;
    Button back2;
   Activity elementview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_detal);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

       // elementview = getView();
        textView = (TextView) findViewById(R.id.textView);
        back2 = (Button) findViewById(R.id.back);

        // jeżeli użytkownik będzie w orientacji landscape, należy zamknąć
        // aktywność
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_detal);

        // pobieramy dane wysłane przez aktywność główną
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString("msg");
            Detal detalFragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);
            //String nowy = url.getText().toString();
            PostResponseAsyncTask task = new PostResponseAsyncTask(DetalActivity.this, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    try{

                        textView.setText(s);
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
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

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

