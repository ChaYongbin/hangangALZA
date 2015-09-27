package io.nuri.hangangalza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by balancer on 15. 3. 26..
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Data> datas;
    private int rowLayout;
    private Context mContext;

    public CustomAdapter(List<Data> datas, int rowLayout, Context context) {
        this.datas = datas;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    //SuppressLint는 에러가 날수 있으니 주의하세요 라고 다른 개발자에게 메세지를 주는 annotation이다.
    //새로운 API이니 주의하라는 의미이다.
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Data data = datas.get(i);
        viewHolder.dataName.setText(data.name);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.dataImage.setImageDrawable(mContext.getDrawable(data.getImageResourceId(mContext)));
        } else {
            viewHolder.dataImage.setImageResource(data.getImageResourceId(mContext));
        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dataName;
        public ImageView dataImage;

        public ViewHolder(View itemView) {
            super(itemView);
            dataName = (TextView) itemView.findViewById(R.id.dataName);
            dataImage = (ImageView)itemView.findViewById(R.id.dataImage);
        }

    }
}
