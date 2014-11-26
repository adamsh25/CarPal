package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class EditTextActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        String prevText = this.getIntent().getStringExtra("prevText");
        ((TextView)(this.findViewById(R.id.editTextGeneric))).setText(prevText);
        this.findViewById(R.id.edit_text_generic_ok_button).setOnClickListener(this);
        this.findViewById(R.id.edit_text_generic_cancel_button).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnToPreviousIntent(View view, boolean isOk)
    {
        Intent returnIntent = new Intent();
        if(isOk) { // User Confirmed Update - Clicked Ok.
            String txt = ((EditText)findViewById(R.id.editTextGeneric)).getText().toString();
            returnIntent.putExtra("changedText", txt);
            setResult(RESULT_OK, returnIntent);
        }
        else
        {
            setResult(RESULT_CANCELED, returnIntent);
        }

        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.edit_text_generic_cancel_button:
                returnToPreviousIntent(view, false);
                break;
            case R.id.edit_text_generic_ok_button:
                returnToPreviousIntent(view, true);
                break;

        }
    }
}
