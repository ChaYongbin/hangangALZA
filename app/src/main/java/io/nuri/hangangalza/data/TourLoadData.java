package io.nuri.hangangalza.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chayongbin on 15. 10. 31..
 */
public class TourLoadData {

    private Context mContext;
    private String json;

    public TourLoadData(Context context) {
        mContext = context;
    }

    public ArrayList<TourData> getJsonData() {

        ArrayList<TourData> tourDataArrayList = new ArrayList<TourData>();

        JSONArray tourJsonArray;
        String name;
        String link;

        String json = getJson();
        TourData tourData;

        try {
            JSONObject tour = new JSONObject(json);
            tourJsonArray = tour.getJSONArray("tour");
            for (int i = 0; i < tourJsonArray.length(); i++) {
                JSONObject jsonObject = tourJsonArray.getJSONObject(i);
                name = jsonObject.getString("name");
                link = jsonObject.getString("link");

                tourData = new TourData(name, link);
                tourDataArrayList.add(tourData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tourDataArrayList;

    }

    public String getJson() {

        json = "{\n" +
                "\t\"tour\" : \n" +
                "\t[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\" : \"행주산성\",\n" +
                "\t\t\t\"link\" : \"http://m.visitkorea.or.kr/LocalAreaMain.do?method=getDetailDB&cid=125562\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        return json; }
}
