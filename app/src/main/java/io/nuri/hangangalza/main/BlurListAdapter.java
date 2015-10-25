package io.nuri.hangangalza.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

import io.nuri.hangangalza.R;
import io.nuri.hangangalza.tour.TourActivity;
import io.nuri.hangangalza.utils.ImageUtils;

/**
 * Created by chayongbin on 15. 10. 19..
 */
public class BlurListAdapter extends BaseAdapter {

    public static final String API_KEY = "AIzaSyD92Bhijy3QI__bFdrdTbbdLXrXvsmX14Y";

    private LayoutInflater layoutInflater;
    private LinearLayout linearLayout;
    private ListView listView;
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
        return 1;
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
            convertView = layoutInflater.inflate(R.layout.activity_page_layout,
                    parent, false);

            // 페이지 뷰를 전체적으로 셋팅하는 항목들.

            linearLayout = (LinearLayout) convertView.findViewById(R.id.bridge_name);
            linearLayout.setMinimumHeight(screenHeight);
            convertView.setId(R.id.id0);

            createToBridgeName(convertView, name);
            createToBridgeInfo(convertView, "0");
            createToBridgeMap(convertView, "37.5980835", "126.8098462");

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("행주나루터");
            arrayList.add("강서한강공원");
            arrayList.add("행주산성");
            createToBridgeTourList(convertView, arrayList);

            createToBridgeHistory(convertView, "1");

            createToBikeMap(convertView, "37.5980835", "126.8098462");

        }

        return convertView;

    }

    private void createToBridgeName(View view, String name){
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(name);
    }

    private void createToBridgeInfo(View view, String info){
        info = "1978년에 서울특별시와 경기도 고양시를 연결하는 너비 10m, 길이 1,400m의 단순 장대교량이 건설되었다. 이 교량의 상부구조는 포스트텐셔닝 공법을 이용한 29.8m의 단순 PSC 거더로 이루어졌으며, 특히 유심부 구간은 주운(舟運) 등을 위하여 지간장 40m의 강판형으로 시공하였다. " +
                "하부구조의 기초공은 우물통 공법을 채택하였고, 교각공은 유속의 영향을 가장 적게 받는 지름 2.5m로 구성된 T형 교각으로 시공하였다. 서울의 서부지역과 경기도 서북부를 연결하는 이 다리는 한강횡단교량으로는 천호대교에 이어 열 번째로 가설되었다.";
        TextView textView = (TextView) view.findViewById(R.id.tv_bridge_info_content);
        textView.setText(info);

    }

    private void createToBridgeMap(View view, String lat, String lng){

        ImageView imageView = (ImageView) view.findViewById(R.id.img_bridge_static_map);

        String url = "https://maps.googleapis.com/maps/api/staticmap" +
                "?center=" + lat + "," + lng +
                "&zoom=13" +
                "&size=300x200" +
                "&maptype=roadmap" +
                "&key=" + API_KEY;

        Glide.with(mContext).load(url).into(imageView);

    }

    private void createToBridgeTourList(View view, ArrayList<String> tourList) {

        listView = (ListView) view.findViewById(R.id.tour_list);
        listView.setAdapter(new BridgeTourListAdapter(mContext, tourList));

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = 100 * tourList.size();
        listView.setLayoutParams(params);

        listView.setOnItemClickListener(new ListViewItemClickListener(tourList));

    }

    private void createToBridgeHistory(View view, String history){
        history = "1978년에 서울특별시와 경기도 고양시를 연결하는 너비 10m, 길이 1,400m의 단순 장대교량이 건설되었다. 이 교량의 상부구조는 포스트텐셔닝 공법을 이용한 29.8m의 단순 PSC 거더로 이루어졌으며, 특히 유심부 구간은 주운(舟運) 등을 위하여 지간장 40m의 강판형으로 시공하였다. " +
                "하부구조의 기초공은 우물통 공법을 채택하였고, 교각공은 유속의 영향을 가장 적게 받는 지름 2.5m로 구성된 T형 교각으로 시공하였다. 서울의 서부지역과 경기도 서북부를 연결하는 이 다리는 한강횡단교량으로는 천호대교에 이어 열 번째로 가설되었다.";
        TextView textView = (TextView) view.findViewById(R.id.tv_bridge_history_content);
        textView.setText(history);
    }

    private void createToBikeMap(View view, String lat, String lng){

        ImageView imageView = (ImageView) view.findViewById(R.id.img_bridge_static_map_bike);

        String url = "https://maps.googleapis.com/maps/api/staticmap" +
                "?center=" + lat + "," + lng +
                "&zoom=13" +
                "&size=300x200" +
                "&maptype=roadmap" +
                "&markers=color:red%7C" + lat + "," + lng +
                "&key=" + API_KEY;

        Glide.with(mContext).load(url).into(imageView);

    }


    private class ListViewItemClickListener implements AdapterView.OnItemClickListener{
        ArrayList<String> tourList;

        public ListViewItemClickListener(ArrayList<String> tourList){
            this.tourList = tourList;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(mContext, TourActivity.class);
            intent.putExtra("tourTitle", tourList.get(position));
            mContext.startActivity(intent);


        }
    }


    // 리스트를 유지하는 부분
    private View getSecondView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);
        }

        TextView txt = (TextView) convertView;
        txt.setText("Position :" + position);

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
