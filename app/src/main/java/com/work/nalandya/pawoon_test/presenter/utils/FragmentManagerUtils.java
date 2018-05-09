package com.work.nalandya.pawoon_test.presenter.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.work.nalandya.pawoon_test.presenter.base.fragment.BaseFragment;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Getter;
import lombok.Setter;



@EBean
public class FragmentManagerUtils<T> {
    @RootContext
    protected Activity activity;
    @Setter
    @Getter
    private int layoutFragment;

    @Setter
    @Getter
    protected FragmentManager fragmentManager;

    @Setter
    private String className;

    @Setter
    private T callback;


    private FragmentTransaction fragmentTransaction() {
        return fragmentManager.beginTransaction();
    }

    public void showFragment(int layout, Fragment fragment, boolean stateloss, String tag,
                             boolean backstack) {
        FragmentTransaction fragmentTransaction = fragmentTransaction().
                replace(layout, fragment, tag);
        if (backstack)
            fragmentTransaction.addToBackStack(className);
        if (stateloss) {
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransaction.commit();
        }
    }

    public void showFragment(Fragment fragment, boolean backstack) {
        showFragment(getLayoutFragment(), fragment, true, null, backstack);
    }


    public void showFragmentUsingCallBack(BaseFragment fragment) {
        showFragment(getLayoutFragment(), setCallBackFragment(fragment, callback), true,
                fragment.getClass().getName(), true);
    }

    public void showFragmentUsingCallBackNoBackStack(BaseFragment fragment) {
        showFragment(getLayoutFragment(), setCallBackFragment(fragment, callback), true, null, false);
    }


    public <T> BaseFragment setCallBackFragment(BaseFragment baseFragment, T t) {
        baseFragment.setCallback(t);
        return baseFragment;
    }

    public void backpressed() {
        BaseFragment baseFragment = (BaseFragment) fragmentManager.findFragmentById
                (getLayoutFragment());
        back(baseFragment);
    }

    public void back(BaseFragment baseFragment) {
        if (baseFragment != null) {
            baseFragment.backPressed();
        } else {
            baseFragmentNull();
        }
    }

    public void baseFragmentNull() {
        activity.finish();
    }


}
