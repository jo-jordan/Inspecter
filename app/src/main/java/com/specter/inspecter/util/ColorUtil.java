package com.specter.inspecter.util;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;

import com.specter.inspecter.R;
import com.specter.inspecter.base.App;

import java.util.Random;

public class ColorUtil {
    public static int getColorById(@ColorRes int resId){
        return App.getApplication().getResources().getColor(resId);
    }

    public static int getRandomColor() {
        Random random = new Random();

        return Color.argb(random.nextInt(50), random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
