package patrycja.retro;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;

/**
 * Created by Patrycja on 2016-03-03.
 */
public class DetalActivity extends AppCompatActivity implements AsyncResponse{
   TextView textView;
    Button back2;
  ListView listview;
    TextView tytul;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
       // setContentView(R.layout.activity_detal);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

       // elementview = getView();
        textView = (TextView) findViewById(R.id.textView);
        tytul = (TextView) findViewById(R.id.tytul);
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
            textView.setText(url);
            tytul.setText("Opis miasta");
         /*   Detal detalFragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);*/
            //String nowy = url.getText().toString();

            //textView.setText(url);


        }
    }



    public void goBack(View v){
        Intent n = new Intent(this, patrycja.retro.MainActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
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

