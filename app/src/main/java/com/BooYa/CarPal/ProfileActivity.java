package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.InputStream;
import java.sql.Driver;
import java.util.ArrayList;

public class ProfileActivity extends Activity {
    // Values for email and password at the time of the login attempt.
    private String mEmail;
    private String mPassword;

    // UI references.
    private EditText txtStreet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txtStreet);

        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier("streets",
                        "raw", getPackageName()));

        // Get the string array
        ArrayList<Street> streets = StreetsSearcher.GetStreets(ins);
        ArrayList<String> streetNames = new ArrayList<String>();
        for (Street street : streets) {
            streetNames.add(street.getStreet_name());
        }
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, streetNames.toArray(new String[streets.size()]));
        textView.setAdapter(adapter);

        Button clickButton = (Button) findViewById(R.id.btnSave);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DriverPanel.class));
            }
        });
    }
}