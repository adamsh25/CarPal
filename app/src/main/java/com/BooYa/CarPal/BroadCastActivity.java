package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.*;

/**
 * Created by adam on 31/10/2014.
 */
public class BroadCastActivity extends Activity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broad_cast_activity_layout);
        ((Button)findViewById( R.id.btnCurrentLocation12 )).setOnClickListener(this);
        getActionBar().hide();
    }
    @Override
    public void onClick(View v)
    {
        if (v == findViewById( R.id.btnCurrentLocation12 ))
        {
            Intent intent = new Intent(this, DriverPanel.class);
            Context context = getApplicationContext();
            CharSequence text = "תודה, המסר שודר לכל עובדי החברה.";
            int duration = LENGTH_SHORT;

            Toast toast = makeText(context, text, duration);
            toast.show();
            startActivity(intent);
        }
    }
}