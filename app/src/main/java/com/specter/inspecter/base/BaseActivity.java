package com.specter.inspecter.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResourceId());

        initContext();
        initView();
        initData();
        initListener();
        App.getApplication().addActivity(this);
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected void initContext(){
        mContext = getApplicationContext();
    }

    protected abstract int setResourceId();
}
