package io.nuri.hangangalza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity implements RecyclerView.OnItemTouchListener {

    private Context mActivity;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;

        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CustomAdapter(DataManager.getInstance().getDatas(), R.layout.card_row, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(this);

        mGestureDetector =
                new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());
    }

    @SuppressLint("NewApi")
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            int position = mRecyclerView.getChildPosition(childView);

            Intent intent = new Intent(mActivity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_PARAM_ID, position);

//            startActivity(intent);

            //L 버전 이상인 경우만 동작한다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) mActivity,
                        new Pair<View, String>(
                                childView.findViewById(R.id.dataImage),
                                DetailActivity.VIEW_NAME_HEADER_IMAGE),

                        new Pair<View, String>(
                                childView.findViewById(R.id.dataName),
                                DetailActivity.VIEW_NAME_HEADER_TITLE)
                );
                startActivity(intent, activityOptions.toBundle());
            } else {
                startActivity(intent);
            }

            return super.onSingleTapConfirmed(e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}


    private void makeexception() {
        try {
            int num = 100;
            int result = 0;

            for (int i = 0; i < 10; i++) {
                result = num / (int) (Math.random() * 10);   // (num / 0)일때 ArithmeticException 예외발생
                System.out.println(result);
            }

        }catch (Exception e) {
            Log.d("argexcept", e.getMessage().toString());
        }
    }

    // 지금은 사용안함.
    private void makeexception2(){

        int num = 100;
        int result = 0;

        for (int i = 0; i < 10; i++) {
            result = num / (int) (Math.random() * 10);   // (num / 0)일때 ArithmeticException 예외발생
            System.out.println(result);
        }

    }
}
