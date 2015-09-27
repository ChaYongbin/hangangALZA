package io.nuri.hangangalza;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class DataManager {

    private static String jsonData =
            "[{'Name':'반포대교','Desc':'반포대교','Image':'brg_banpo'}," +
                    "{'Name':'동호대교','Desc':'동호대교','Image':'brg_dongho'}," +
                    "{'Name':'동작대교','Desc':'동작대교','Image':'brg_dongzak'}," +
                    "{'Name':'한강대교','Desc':'한강대교','Image':'brg_hangang'}," +
                    "{'Name':'마포대교','Desc':'마포대교','Image':'brg_mapo'}," +
                    "{'Name':'성산대교','Desc':'성산대교','Image':'brg_sungsan'}," +
                    "{'Name':'성수대교','Desc':'성수대교','Image':'brg_sungsu'}]";

    private static DataManager mInstance;
    private List<Data> datas;

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }

        return mInstance;
    }

    public List<Data> getDatas() {
        if (datas == null) {
            datas = new ArrayList<Data>();

            try {
                JSONArray jArr = new JSONArray(jsonData);
                for (int i = 0; i < jArr.length(); ++i) {
                    JSONObject jObj = jArr.getJSONObject(i);

                    Data data = new Data();
                    data.name = jObj.getString("Name");
                    data.description = jObj.getString("Desc");
                    data.imageName = jObj.getString("Image")
                            .replaceAll("\\s+", "").toLowerCase();
                    data.description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. 	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

                    datas.add(data);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return datas;
    }

    public Data getDataAtPosition( int position ) {
        return datas.get( position );
    }
}
