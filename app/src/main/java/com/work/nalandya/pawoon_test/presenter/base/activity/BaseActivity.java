package com.work.nalandya.pawoon_test.presenter.base.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;


import com.work.nalandya.pawoon_test.presenter.base.fragment.BaseFragment;
import com.work.nalandya.pawoon_test.presenter.custom.typeface.TypeFaceTitle;
import com.work.nalandya.pawoon_test.presenter.utils.Util;
import com.work.nalandya.pawoon_test.presenter.utils.UtilsLayout;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;


@EActivity
public class BaseActivity extends AppCompatActivity {

    @Bean
    protected UtilsLayout utilsLayout;
    @Bean
    protected Util util;

    protected int idFrame;

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //    using this function fragment transaction for transaction fragment in activity
    protected FragmentTransaction fragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
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

    protected SpannableString makeTitleActionBar(String title) {
        SpannableString spannableString = new SpannableString(title);
        spannableString.setSpan(new TypeFaceTitle(this, "Lato-Regular.ttf"), 0, title.length(),
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    @Override
    public void onBackPressed() {
        BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().
                findFragmentById(idFrame);

        if (!back(baseFragment)) {
            super.onBackPressed();
        }
    }

    private boolean back(BaseFragment baseFragment) {
        if (baseFragment != null) {
            baseFragment.backPressed();
            return true;
        } else {
            return false;
        }

    }

    public void refreshFragment() {
        Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentById(idFrame);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }


}
