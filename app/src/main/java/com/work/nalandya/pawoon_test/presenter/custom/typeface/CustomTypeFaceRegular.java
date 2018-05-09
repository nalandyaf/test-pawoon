package com.work.nalandya.pawoon_test.presenter.custom.typeface;

import android.content.Context;


public class CustomTypeFaceRegular extends BaseCustomTypeFace {
    public CustomTypeFaceRegular(Context context) {
        super(context);
        typeface = readFont.regular();
    }
}
