package com.work.nalandya.pawoon_test.presenter.utils;

import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;


@EBean
public class UtilsLayout {
    public String getBodyText(TextView textView) {
        return textView.getText().toString();
    }

    public String getText(EditText editText){ return editText.getText().toString(); }
}
