package com.specter.inspecter.ui.fragment;

import android.os.Bundle;

import com.specter.inspecter.R;
import com.specter.inspecter.base.BaseFragment;

public class IndexFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setResourceId() {
        return R.layout.fragment_index;
    }
}
