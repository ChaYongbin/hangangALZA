package io.nuri.hangangalza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends Activity {

    // Extra name for the ID parameter
    public static final String EXTRA_PARAM_ID = "detail:_id";
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";

    private ImageView mImageView;
    private TextView mHeaderTitle;
    private TextView mHeaderDesc;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Data data = DataManager.getInstance().getDataAtPosition( getIntent().getIntExtra(EXTRA_PARAM_ID, 0) );

        mImageView = (ImageView) findViewById(R.id.imageView1);
        mHeaderTitle = (TextView) findViewById(R.id.textView1);
        mHeaderDesc = (TextView) findViewById(R.id.textView2);

        mImageView.setImageResource( data.getImageResourceId( getApplicationContext() ) );
        mHeaderTitle.setText( data.getName() );
        mHeaderDesc.setText( data.getDescription() );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName(VIEW_NAME_HEADER_IMAGE);
            mHeaderTitle.setTransitionName(VIEW_NAME_HEADER_TITLE);
        }
    }

}
