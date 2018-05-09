package com.work.nalandya.pawoon_test.view.fragment;


import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.base.fragment.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    @AfterViews
    protected void init() {

    }

    @Override
    public void backPressed() {

    }
}
