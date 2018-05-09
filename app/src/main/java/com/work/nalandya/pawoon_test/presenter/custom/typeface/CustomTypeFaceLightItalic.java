package com.work.nalandya.pawoon_test.presenter.custom.typeface;

import android.content.Context;


public class CustomTypeFaceLightItalic extends BaseCustomTypeFace {
    public CustomTypeFaceLightItalic(Context context) {
        super(context);
        typeface = readFont.lightItalic();
    }
}
