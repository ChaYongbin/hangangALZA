package io.nuri.hangangalza.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.nuri.hangangalza.R;

import static io.nuri.hangangalza.R.color.white;

/**
 * Created by chayongbin on 15. 10. 25..
 */
public class BridgeTourListAdapter extends BaseAdapter {

    private ArrayList<String> tourList;
    private LayoutInflater layoutInflater;
    private Context context;

    public BridgeTourListAdapter(Context context, ArrayList<String> tourList) {
        layoutInflater = LayoutInflater.from(context);
        this.tourList = tourList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tourList.size();
//        return 3;
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

        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) convertView;

        textView.setText(" " + tourList.get(position));
        String color = "#FFFFFF";
        textView.setTextColor(Color.parseColor(color));

        return convertView;
    }
}
