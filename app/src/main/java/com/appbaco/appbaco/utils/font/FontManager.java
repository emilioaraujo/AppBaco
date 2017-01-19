package com.appbaco.appbaco.utils.font;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by SMP on 19/01/2017.
 */

public class FontManager {
    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
