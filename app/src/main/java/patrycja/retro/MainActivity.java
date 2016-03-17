package patrycja.retro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements List.OverviewFragmentActivityListener {
    private static String temp;
    View detal;
    View list;
    View linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listaFragment);
        detal = findViewById(R.id.detalFragment);
        linear = findViewById(R.id.linear);

        if (new Configuration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            detal.setVisibility(View.VISIBLE);

        } else {
            detal.setVisibility(View.INVISIBLE);
            list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));


        }

    }

    public void onItemSelected(String msg) {
        temp = msg;


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            detal.setVisibility(View.VISIBLE);

            list.setLayoutParams(new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.MATCH_PARENT));
        } else {
            detal.setVisibility(View.INVISIBLE);
            list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));

        }
        Detal fragment = (Detal) getFragmentManager()
                .findFragmentById(R.id.detalFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.settText(msg, true);
        }

        Intent intent = new Intent(getApplicationContext(),
                DetalActivity.class);
        intent.putExtra("msg", msg);
        startActivity(intent);

    }

    public void onConfigurationChanged(Configuration newConfig) {
        onItemSelected(temp);
        if (new Configuration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detal.setVisibility(View.VISIBLE);
        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}