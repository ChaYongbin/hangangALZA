package io.nuri.hangangalza;


import android.content.Context;

public class Data {
    public String name;
    public String description;
    public String imageName;


    public int getImageResourceId(Context context) {
        try {
            return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
