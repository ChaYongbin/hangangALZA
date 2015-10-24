package io.nuri.hangangalza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;

import io.nuri.hangangalza.main.MainActivity;

/**
 * Created by chayongbin on 15. 10. 23..
 */
public class StartActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        StartActivity.this.startActivity(intent);
        StartActivity.this.finish();
    }

}
