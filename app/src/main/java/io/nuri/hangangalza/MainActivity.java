package io.nuri.hangangalza;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ViewPager mPager;
    PagerAdapter mAdapter;

    private BridgeLoadData bridgeLoadData;
    private ArrayList<BridgeData> bridgeDataArrayList = new ArrayList<BridgeData>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();

    }

    private void initPager(){

        // 페이저 등록
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setBackgroundColor(0xFF000000);

        ParallaxPagerTransformer pt = new ParallaxPagerTransformer((R.id.image));
        pt.setBorder(0);
        pt.setSpeed(1f);
        mPager.setPageTransformer(false, pt);


        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mAdapter.setPager(mPager); //only for this transformer

        bridgeLoadData = new BridgeLoadData(this);

        bridgeDataArrayList = bridgeLoadData.getJsonData();

        for (int i = 0; i < 24; i++) {
            String name = bridgeDataArrayList.get(i).getBridge_kr();
            String image = bridgeDataArrayList.get(i).getBridge_image();
            int id = bridgeDataArrayList.get(i).getBr_id();

            Bundle bundle = new Bundle();
            bundle.putString("image", image);
            bundle.putString("name", name);
            bundle.putString("id", String.valueOf(id));
            PagerFragment pagerFragment = new PagerFragment();
            pagerFragment.setArguments(bundle);

            mAdapter.add(pagerFragment);
        }

        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(mPager.getChildCount() + 240, false);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}