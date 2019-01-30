package com.specter.inspecter.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;

import com.specter.inspecter.R;
import com.specter.inspecter.base.BaseFragment;
import com.specter.inspecter.custom.ImageSeekBar;
import com.specter.inspecter.data.DataServer;
import com.specter.inspecter.ui.entity.IndexImageMultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "IndexFragment";

    ImageSeekBar mTsbIndicator;
    LinearLayoutManager mLinearLayoutManager;

    TextView mTvUserName;
    TextView mTvPostTime;
    TextView mTvPostLocation;
    TextView mTvUserLikes;
    TextView mTvUserComments;

    SnapHelper mSnapHelper = new PagerSnapHelper();


    private boolean isDragSeekBar;

    public static BaseFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = mSnapHelper.findSnapView(mLinearLayoutManager);
                    int pos = mLinearLayoutManager.getPosition(centerView);
                    centerView.performClick();
                    Log.d("Snapped Item Position:",""+pos);
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                isDragSeekBar = false;
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }
        });

        mAdapter.setOnItemChildClickListener((view, adapter, position) -> {
            Log.d(TAG, "now position-outer: " + (position + 1));
            startAllAnimator();
            IndexImageMultipleItemEntity entity = (IndexImageMultipleItemEntity)mAdapter.getItem(position);
            mTvUserName.setText(entity.getUserName());
            mTvPostTime.setText(entity.getPostTime());
            mTvPostLocation.setText(entity.getPostLocation());
            mTvUserLikes.setText(entity.getLikeNumber());
            mTvUserComments.setText(entity.getCommentNumber());

            if (!isDragSeekBar) {
                Log.d(TAG, "now position-inner: " + (position + 1));
                mTsbIndicator.onProgressChanged(mTsbIndicator, position + 1, true);
            }
        });
        mTsbIndicator.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        isDragSeekBar = true;
        if(i <= 0)
            i = 1;
        Log.d(TAG, "seek progress: " + i);
        mRecyclerView.smoothScrollToPosition(i - 1);
        mTsbIndicator.onProgressChanged(seekBar, i, b);
    }

    @Override
    protected void initData() {
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {
        mTsbIndicator = findView(R.id.isb_indicator);
        mRecyclerView = findView(R.id.rv_images);
        mAdapter = new IndexImageListAdapter(DataServer.getImageData());
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mTvUserName = findView(R.id.tv_user_name);
        mTvPostTime = findView(R.id.tv_post_time);
        mTvPostLocation = findView(R.id.tv_post_location);
        mTvUserLikes = findView(R.id.tv_user_likes);
        mTvUserComments = findView(R.id.tv_user_comments);

        initAnimator(mTvUserName, mTvPostTime, mTvPostLocation, mTvUserLikes, mTvUserComments);


        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        mTsbIndicator.setMax(mAdapter.getItemCount());
        mTsbIndicator.setProgress(1);
    }

    @Override
    protected int setResourceId() {
        return R.layout.fragment_index;
    }

    private List<ObjectAnimator> mObjectAnimatorList;

    private void initAnimator(View... views) {
        mObjectAnimatorList = new ArrayList<>();
        for (View view : views) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);
            objectAnimator.setRepeatCount(0);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.setDuration(500);
            objectAnimator.addUpdateListener((animator) -> {
                view.setScaleX((Float) animator.getAnimatedValue());
                view.setScaleY((Float) animator.getAnimatedValue());
            });
            mObjectAnimatorList.add(objectAnimator);
        }
    }

    private void startAllAnimator() {
        for (ObjectAnimator objectAnimator : mObjectAnimatorList) {
            objectAnimator.start();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
