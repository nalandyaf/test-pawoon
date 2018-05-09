package com.work.nalandya.pawoon_test.presenter.custom.typeface;

import android.content.Context;

public class CustomTypeFaceBoldItalic extends BaseCustomTypeFace {
    public CustomTypeFaceBoldItalic(Context context) {
        super(context);
        typeface = readFont.boldItalic();
    }
}
