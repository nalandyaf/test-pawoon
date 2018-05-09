package com.work.nalandya.pawoon_test.presenter.custom.edittext;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;


import com.work.nalandya.pawoon_test.presenter.utils.ReadFont;

public class CustomEditTextLightItalic extends EditText {
    private ReadFont readFont;

    public CustomEditTextLightItalic(Context context) {
        super(context);
        setFont(context);
    }

    public CustomEditTextLightItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);

    }

    public CustomEditTextLightItalic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditTextLightItalic(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFont(context);

    }


    protected void setFont(Context context) {
        readFont = new ReadFont(context);
        setTypeface(readFont.lightItalic());
    }


}
