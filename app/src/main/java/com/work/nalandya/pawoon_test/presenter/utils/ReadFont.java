package com.work.nalandya.pawoon_test.presenter.utils;

import android.content.Context;
import android.graphics.Typeface;

public class ReadFont {
    private final Context context;
    String fontName;

    public ReadFont(Context context) {
        this.context = context;
    }

    public Typeface bold() {
        return font("Lato-Bold.ttf");
    }

    public Typeface boldItalic() {
        return font("Lato-BoldItalic.ttf");
    }

    public Typeface italic() {
        return font("Lato-Italic.ttf");
    }

    public Typeface light() {
        return font("Lato-Light.ttf");
    }

    public Typeface lightItalic() {
        return font("Lato-LightItalic.ttf");
    }

    public Typeface regular() {
        return font("Lato-Regular.ttf");
    }


    private Typeface font(String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
        return font;
    }
}
