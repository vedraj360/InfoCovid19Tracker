package com.vdx.infocovid19.Utils;

import android.content.Context;
import android.graphics.Typeface;

public class Helper {

    public static Typeface getFontSb(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "arsb.ttf");
    }

    public static Typeface getFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "ar.ttf");
    }
}
