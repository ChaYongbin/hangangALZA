package io.nuri.hangangalza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by chayongbin on 15. 10. 22..
 */
public class PagerFragment  extends Fragment {

    public static final int BACKGROUND_SHIFT = 200;

    private PagerAdapter mCatsAdapter;

    private ImageView blurredImageView;
    private ImageView nonBlurImageView;

    private ListView listView;
    private ScrollView scrollView;
    private View titleView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView image = (ImageView) v.findViewById(R.id.image);

        Context context = image.getContext();

        int imageFile = getResources().getIdentifier(getArguments().getString("image"),
                "drawable", context.getPackageName());

        int imageNoFile = getResources().getIdentifier("no_img",
                "drawable", context.getPackageName());

        if (imageFile == 0) {
            Glide.with(this).load(imageNoFile).into(image);
        } else {
            Glide.with(this).load(imageFile).into(image);
        }

        TextView text = (TextView)v.findViewById(R.id.name);
        text.setText(getArguments().getString("name"));

        TextView hear = (TextView)v.findViewById(R.id.textView);
        hear.setText(getArguments().getString("id") + "/24");

//        listenToScroll();

        return v;
    }

    @SuppressLint("NewApi")
    private void listenToScroll() {

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                View v = view.findViewById(R.id.id0);
                if (v != null) {
                    int scrollY = -v.getTop();

                    if (scrollY < 512 && scrollY >= 0) {

                        float val = 0;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            val = scrollY * (1f / 512f);
                            blurredImageView.setAlpha(val);
                        } else {
                            val = scrollY / 2;
                            blurredImageView.setAlpha((int) val);

                        }
                    }
                    if (scrollY < BACKGROUND_SHIFT * 2
                            && scrollY >= 0) {
                        scrollView.scrollTo(0, scrollY / 3);
                    }
                }

            }
        });
    }

    public void setAdapter(PagerAdapter catsAdapter) {
        mCatsAdapter = catsAdapter;
    }
}
