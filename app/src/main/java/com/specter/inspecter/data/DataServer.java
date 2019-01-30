package com.specter.inspecter.data;

import com.specter.inspecter.R;
import com.specter.inspecter.base.App;
import com.specter.inspecter.ui.entity.IndexImageMultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataServer {

    private static String[] names = new String[]{"georgeharrison", "davidbowie", "johnlenon", "michaeljackson", "marilynmanson", "jakiechen", "jaychou", "wangguojun"};
    private static String[] times = new String[]{"30 min", "32 sec", "1 hour"};
    private static String[] locations = new String[]{"Changde, China", "New York, US", "Chicago, US", "Prague"};

    public static List<IndexImageMultipleItemEntity> getImageData() {
        List<IndexImageMultipleItemEntity> list = new ArrayList<>();

        String[] demoPicUrl = App.getApplication().getApplicationContext().getResources().getStringArray(R.array.demo_pic_url);
        //String[] demoVideoUrl = App.getApplication().getApplicationContext().getResources().getStringArray(R.array.demo_video_url);

        Random random = new Random();

        for (int i = 0; i < demoPicUrl.length; i++) {
            list.add(new IndexImageMultipleItemEntity(
                    IndexImageMultipleItemEntity.INDEX_TYPE_IMAGE,
                    "",
                    names[random.nextInt(8)],
                    times[random.nextInt(3)],
                    locations[random.nextInt(4)],
                    demoPicUrl[i],
                    "",
                    random.nextInt(900000) + "",
                    "" + random.nextInt(500000)
            ));
        }

        return list;
    }
}
