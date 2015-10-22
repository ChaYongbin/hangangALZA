package io.nuri.hangangalza;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by chayongbin on 15. 10. 22..
 */
public class PagerFragment  extends Fragment {

    private PagerAdapter mCatsAdapter;

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

        return v;
    }

    public void setAdapter(PagerAdapter catsAdapter) {
        mCatsAdapter = catsAdapter;
    }
}
