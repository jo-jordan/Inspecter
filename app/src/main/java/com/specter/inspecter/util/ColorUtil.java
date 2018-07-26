package com.specter.inspecter.util;

import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;

import com.specter.inspecter.R;
import com.specter.inspecter.base.App;

public class ColorUtil {
    public static int getColorById(@ColorRes int resId){
        return App.getApplication().getResources().getColor(resId);
    }
}
