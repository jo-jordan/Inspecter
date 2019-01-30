package com.specter.inspecter.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;

import com.specter.inspecter.R;
import com.specter.inspecter.util.ColorUtil;
import com.specter.inspecter.util.SizeUnitUtil;

public class ImageSeekBar extends AppCompatSeekBar implements SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "ImageSeekBar";

    private static final long mTextScaleDuration = 500;

    private Paint mProgressTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    private Paint mBackTextPaint = new Paint();
    AnimatorSet mAnimatorSetText = new AnimatorSet();
    ObjectAnimator mTextSizeAnimator;
    ObjectAnimator mTextAlphaAnimator;

    Rect mProgressTextRect = new Rect();
    Rect mBackgroundTextRect = new Rect();

    private float mTextScaleX;
    private float mTextScaleY;

    private int seekBarMinValue;


    public ImageSeekBar(Context context) {
        this(context, null);
    }

    public ImageSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ImageSeekBar, defStyleAttr, 0);

        seekBarMinValue = a.getInteger(R.styleable.ImageSeekBar_iSeekMin, 1);

        a.recycle();
        this.setOnSeekBarChangeListener(this);
        init();
    }

    private void init() {
        initTextPaint();
        initTextAnimator();
        initText();
    }

    private void initText() {
        mTextScaleX = 1;
        mTextScaleY = 1;
    }

    private void initTextPaint() {
        mProgressTextPaint.setAntiAlias(true);
        mProgressTextPaint.setTextSize(SizeUnitUtil.convertDpToPixel(20.0f));
        mProgressTextPaint.setColor(ColorUtil.getColorById(R.color.colorWhite));
        mProgressTextPaint.setTextAlign(Paint.Align.CENTER);
        //BlurMaskFilter blurMaskFilter = new BlurMaskFilter(5,BlurMaskFilter.Blur.NORMAL);
        //mProgressTextPaint.setMaskFilter(blurMaskFilter);

        mBackTextPaint.setAntiAlias(true);
        mBackTextPaint.setTextSize(SizeUnitUtil.convertDpToPixel(20.0f));
        mBackTextPaint.setColor(ColorUtil.getColorById(R.color.colorPrimaryDark));
        mBackTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    /* Every time the progress updated animation will set on.
     * For example 1 -> 2: 1 will be get smaller and smaller also blur
     * at the same time 2 will be bigger
     *  */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int progress = getProgress();
        Drawable thumb = getThumb();
        Rect rect1 = thumb.getBounds();
        String backgroundText = "" + getMax();
        int yPos = (int) ((getHeight() / 2) - ((mProgressTextPaint.descent() + mProgressTextPaint.ascent()) / 2));

        if (progress < seekBarMinValue) {

        } else {
            String progressText = "" + progress;
            mProgressTextPaint.getTextBounds(progressText, 0, progressText.length(), mProgressTextRect);
            mBackTextPaint.getTextBounds(backgroundText, 0, backgroundText.length(), mBackgroundTextRect);
            int thumbWidth = rect1.right - rect1.left;
            int progressTextXPos = (rect1.left + rect1.right) / 2;

            canvas.save();
            canvas.scale(mTextScaleX, mTextScaleY, progressTextXPos, (rect1.top + rect1.bottom) / 2);
            canvas.drawText(progressText, progressTextXPos, yPos, mProgressTextPaint);
            Log.d(TAG,
                    "rect1 right: " + rect1.right +
                            ", mBackgroundTextRect.left: " + mBackgroundTextRect.left +
                            ", progressText: " + progressText +
                            ", backgroundText: " + backgroundText);
            canvas.restore();
        }

        int seekBarWidth = getWidth();
        int backgroundTextXPos = seekBarWidth - (mBackgroundTextRect.right + mBackgroundTextRect.left) / 2 - (int) SizeUnitUtil.convertDpToPixel(6.0f);
        if (rect1.right < backgroundTextXPos) {
            canvas.drawText(backgroundText, backgroundTextXPos, yPos, mBackTextPaint);
        }
    }


    private void initTextAnimator() {
        // TextSize animation
        //mTextSizeAnimator = ObjectAnimator.ofFloat(mProgressTextPaint, "textSize", SIzeUnitUtil.convertDpToPixel(0.0f), SIzeUnitUtil.convertDpToPixel(1.0f));
        mTextSizeAnimator = ObjectAnimator.ofFloat(this, "textScaleX", 0.0f, 1.0f);
        mTextSizeAnimator.setRepeatCount(0);

        //Text alpha animation
        mTextAlphaAnimator = ObjectAnimator.ofInt(mProgressTextPaint, "alpha", 0, 255);
        mTextAlphaAnimator.setRepeatCount(0);

        mAnimatorSetText.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorSetText.setDuration(mTextScaleDuration);
        mTextAlphaAnimator.addUpdateListener((valueAnimator) -> {
            Log.d(TAG, "valueAnimator alpha value: " + valueAnimator.getAnimatedValue());
            mProgressTextPaint.setAlpha((Integer) valueAnimator.getAnimatedValue());

            invalidate();
        });

        mTextSizeAnimator.addUpdateListener((valueAnimator) -> {
            Log.d(TAG, "valueAnimator text size value: " + valueAnimator.getAnimatedValue());
            //mProgressTextPaint.setTextSize((Float) valueAnimator.getAnimatedValue());
            mTextScaleX = (Float) valueAnimator.getAnimatedValue();
            mTextScaleY = (Float) valueAnimator.getAnimatedValue();

            invalidate();
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            setProgress(i);
            mTextAlphaAnimator.start();
            mTextSizeAnimator.start();
//            mAnimatorSetText.playTogether(mTextSizeAnimator, mTextAlphaAnimator);
//            mAnimatorSetText.start();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public float getTextScaleX() {
        return mTextScaleX;
    }

    public void setTextScaleX(float mTextScaleX) {
        this.mTextScaleX = mTextScaleX;
    }

    public float getTextScaleY() {
        return mTextScaleY;
    }

    public void setTextScaleY(float mTextScaleY) {
        this.mTextScaleY = mTextScaleY;
    }
}
