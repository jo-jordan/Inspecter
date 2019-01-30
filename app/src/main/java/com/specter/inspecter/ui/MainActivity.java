package com.specter.inspecter.ui;

import android.widget.FrameLayout;

import com.specter.inspecter.R;
import com.specter.inspecter.base.BaseActivity;
import com.specter.inspecter.custom.ImageSeekBar;
import com.specter.inspecter.ui.fragment.IndexFragment;

public class MainActivity extends BaseActivity {

    FrameLayout mContainer;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mContainer = findViewById(R.id.fl_main_container);


        if (findFragment(IndexFragment.class) == null)
            loadRootFragment(R.id.fl_main_container, IndexFragment.newInstance());
    }

    @Override
    protected int setResourceId() {
        return R.layout.activity_main;
    }
}
