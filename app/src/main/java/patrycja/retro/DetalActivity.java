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
public class DetalActivity extends AppCompatActivity implements AsyncResponse {
    TextView textView;
    Button back2;
  //  ListView listview;
    TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
     /*   textView = (TextView) findViewById(R.id.textView);
        title = (TextView) findViewById(R.id.title);
        back2 = (Button) findViewById(R.id.back); */
    //    listview = (ListView) findViewById(R.id.listview);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        final Context context = this;


        // pobieramy dane wysłane przez aktywność główną
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString("msg");
            Detal detalFragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);
          /* getFragmentManager().beginTransaction().replace(R.id.fragment,
                    new Detal()).commit(); */
            detalFragment.settText(url);
//            textView.setText(url);
  //          title.setText("Opis miasta");

        }
    }


    public void goBack(View v) {
        Intent n = new Intent(this, patrycja.retro.MainActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();

    }

    @Override
    public void processFinish(String s) {

    }


}

