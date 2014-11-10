package com.BooYa.CarPal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;


public class GroupInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider);
        //TextSliderView textSliderView = new TextSliderView(this);
        //textSliderView
               // .description("Game of Thrones")
               // .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        Bitmap bit = BitmapFactory.decodeResource(null, R.drawable.adam);
        DefaultSliderView dsv1 = new DefaultSliderView(this);
        dsv1.image(R.drawable.rony);
        sliderShow.addSlider(dsv1);
        DefaultSliderView dsv2 = new DefaultSliderView(this);
        dsv1.image(R.drawable.alertroni);
        sliderShow.addSlider(dsv2);
        DefaultSliderView dsv3 = new DefaultSliderView(this);
        dsv1.image(R.drawable.beeri_circle);
        sliderShow.addSlider(dsv3);
        //sliderShow.addSlider(textSliderView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_info, menu);
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
}
