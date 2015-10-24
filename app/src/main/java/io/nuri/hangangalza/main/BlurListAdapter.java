package io.nuri.hangangalza.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import io.nuri.hangangalza.R;
import io.nuri.hangangalza.utils.ImageUtils;

/**
 * Created by chayongbin on 15. 10. 19..
 */
public class BlurListAdapter extends BaseAdapter {


    protected GoogleMap mGoogleMap;

    private LayoutInflater layoutInflater;
    private int screenHeight;
    private Context mContext;

    private String name;

    public BlurListAdapter(Activity context, String name) {
        layoutInflater = LayoutInflater.from(context);
        screenHeight = ImageUtils.getScreenHeight(context);
        this.name = name;
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 0:
                return getFirstView(position, convertView, parent, name);
            case 1:
                return getSecondView(position, convertView, parent);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    private View getFirstView(int position, View convertView, ViewGroup parent, String name) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.first_page_layout,
                    parent, false);

            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = screenHeight;
            convertView.setLayoutParams(params);
            convertView.setId(R.id.id0);

            TextView textView = (TextView) convertView.findViewById(R.id.name);

            textView.setText(name);
        }

        return convertView;
    }

    private View getSecondView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);
        }

        TextView txt = (TextView) convertView;
        txt.setText("Position :" + position);

        return convertView;
    }

    private View getThirdView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.bridge_info_layout, parent, false);

            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = screenHeight;
            convertView.setLayoutParams(params);
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
