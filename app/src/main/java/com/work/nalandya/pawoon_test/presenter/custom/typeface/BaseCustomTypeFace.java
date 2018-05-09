package com.work.nalandya.pawoon_test.presenter.custom.typeface;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.work.nalandya.pawoon_test.presenter.utils.ReadFont;


public class BaseCustomTypeFace extends MetricAffectingSpan {
    protected ReadFont readFont;
    protected Typeface typeface;

    public BaseCustomTypeFace(Context context) {
        readFont = new ReadFont(context);
    }


    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(typeface);
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(typeface);
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);

    }
}
