package com.specter.inspecter.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.specter.inspecter.R;
import com.specter.inspecter.util.ColorUtil;
import com.specter.inspecter.util.SIzeUnitUtil;

public class ImageSeekBar extends View {
    // When I touch the thumb circle the bar will calculate the progress and thumb location

    private int mMax;
    private int mMin;
    private float mProgress;
    private int mTextSize;
    private int mBackgroundTextSize;
    private int mTextColor;
    private int mBackgroundTextColor;
    private Drawable mDrawable;
    private Drawable mBackgroundDrawable;
    private Drawable mThumbDrawable;
    private int mWidth;
    private int mHeight;
    private int mThumbWidth;

    public ImageSeekBar(Context context) {
        this(context, null);
    }

    public ImageSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageSeekBar, defStyleAttr, 0);
        mMax = a.getInteger(R.styleable.ImageSeekBar_iMax, 20);
        mMin = a.getInteger(R.styleable.ImageSeekBar_iMin, 1);
        mProgress = a.getFloat(R.styleable.ImageSeekBar_iProgress, 1);
        mTextSize = a.getDimensionPixelSize(R.styleable.ImageSeekBar_iTextSize, (int) SIzeUnitUtil.convertDpToPixel(20f));
        mBackgroundTextSize = a.getDimensionPixelSize(R.styleable.ImageSeekBar_iBackgroundTextSize, (int) SIzeUnitUtil.convertDpToPixel(20f));
        mTextColor = a.getColor(R.styleable.ImageSeekBar_iTextColor, ColorUtil.getColorById(R.color.colorWhite));
        mBackgroundTextColor = a.getColor(R.styleable.ImageSeekBar_iBackgroundTextColor, ColorUtil.getColorById(R.color.colorPrimaryDark));
        mDrawable = a.getDrawable(R.styleable.ImageSeekBar_iDrawable);
        mBackgroundDrawable = a.getDrawable(R.styleable.ImageSeekBar_iBackgroundDrawable);
        mThumbDrawable = a.getDrawable(R.styleable.ImageSeekBar_iThumbDrawable);



        a.recycle();
    }



    private void updateDrawable() {
    }
}
