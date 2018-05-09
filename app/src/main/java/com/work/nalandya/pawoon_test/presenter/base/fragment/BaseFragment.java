package com.work.nalandya.pawoon_test.presenter.base.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;


import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.callback.CallbackFragment;
import com.work.nalandya.pawoon_test.presenter.custom.typeface.TypeFaceTitle;
import com.work.nalandya.pawoon_test.presenter.manager.IntentManager;
import com.work.nalandya.pawoon_test.presenter.utils.Util;
import com.work.nalandya.pawoon_test.presenter.utils.UtilsLayout;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.StringRes;


public class BaseFragment<T> extends Fragment {

    protected static CallbackFragment callBackFragment;

    protected int idFrame;

    protected UtilsLayout utilsLayout;
    protected Util util;
    protected ProgressDialog progressDialog;


    protected T callback;

    public BaseFragment<T> setCallback(T callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utilsLayout = new UtilsLayout();
        util = new Util();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CallbackFragment) {
            callBackFragment = (CallbackFragment) activity;
        }
        idFrame = R.id.main_frame;

    }

    public void resultForm(int resultCode, int requestCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == IntentManager.Code.FORM) {
            isResultForm(data);
        }
    }

    public void isResultForm(Intent data) {

    }

    //    using this function for show fragment in activity
    protected void showFragment(int layout, Fragment fragment, boolean stateloss, String tag) {
        FragmentTransaction fragmentTransaction = fragmentTransaction().
                replace(layout, fragment, tag);
        if (stateloss) {
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransaction.commit();
        }
    }

    public void setTitleBar(String title) {
        SpannableString s = new SpannableString(title);
        s.setSpan(new TypeFaceTitle(getActivity(), "Lato-Regular.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(s);
    }

    protected void showFragmentNow(Fragment fragment) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack("FRAGMENT").commit();

    }

    protected void fragmentShow(Fragment fragment) {
        showFragment(idFrame, fragment, false, null);
    }

    protected FragmentTransaction fragmentTransaction() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getChildFragmentManager().beginTransaction();
        }
        return getChildFragmentManager().beginTransaction();
    }

    public void backPressed() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStack();
        } else {
            BaseFragment baseFragment = (BaseFragment) getChildFragmentManager().
                    findFragmentById(idFrame);
            if (!back(baseFragment)) {
                getActivity().finish();
            }
        }

    }

    public void refreshFragment() {
        Fragment frg = null;
        frg = getActivity().getSupportFragmentManager().findFragmentById(idFrame);
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

    private boolean back(BaseFragment baseFragment) {
        if (baseFragment != null) {
            baseFragment.backPressed();
            return true;
        } else {
            return false;
        }
    }

}
