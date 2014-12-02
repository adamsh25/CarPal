package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SplashScreenActivity extends Activity {
    private RelativeLayout mSplashLayout;
    private Button mSignInBtn;
    private EditText mEmail;
    private EditText mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSplashLayout = (RelativeLayout)findViewById(R.id.splashLayout);
        mSignInBtn = (Button)findViewById(R.id.signinbtn);
        mSignInBtn.setVisibility(View.INVISIBLE);

        mEmail = (EditText)findViewById(R.id.splashEmail);
        mEmail.setVisibility(View.INVISIBLE);
        mPass = (EditText)findViewById(R.id.splashPassword);
        mPass.setVisibility(View.INVISIBLE);

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmail.getText().toString().equals("demo") && mPass.getText().toString().equals("demo")) {
                    startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        new SplashTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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

    private class SplashTask extends AsyncTask<Void,Void,Void > {
        @Override
        protected void onPreExecute() {

        }
        /* (non-Javadoc)
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void param) {
            mSplashLayout.setBackgroundResource(R.drawable.openscreen2);
            mSignInBtn.setVisibility(View.VISIBLE);
            mEmail.setVisibility(View.VISIBLE);
            mPass.setVisibility(View.VISIBLE);
        }
    }
}
