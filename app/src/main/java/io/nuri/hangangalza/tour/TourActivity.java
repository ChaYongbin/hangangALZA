package io.nuri.hangangalza.tour;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import io.nuri.hangangalza.R;

/**
 * Created by chayongbin on 15. 10. 25..
 */
public class TourActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tour_layout);

        Intent intent = getIntent();
        String tourTitle = intent.getStringExtra("tourTitle");

    }
}
