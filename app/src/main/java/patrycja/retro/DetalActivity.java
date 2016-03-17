package patrycja.retro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.kosalgeek.genasync12.AsyncResponse;

public class DetalActivity extends AppCompatActivity implements AsyncResponse {
    Button back2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        back2 = (Button) findViewById(R.id.back);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        back2.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         goBack();
                                     }
                                 }
        );


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString("msg");
            Detal detalFragment = (Detal) getFragmentManager()
                    .findFragmentById(R.id.detalFragment);
            detalFragment.settText(url, false);

        }
    }


    public void goBack() {
        Intent n = new Intent(this, patrycja.retro.MainActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
    }



    @Override
    public void processFinish(String s) {

    }


}
