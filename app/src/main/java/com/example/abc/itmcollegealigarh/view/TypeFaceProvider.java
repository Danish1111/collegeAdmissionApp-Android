package com.example.abc.itmcollegealigarh.view;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypeFaceProvider {

//    public static final String TYPEFACE_FOLDER = "fonts";
//    public static final String TYPEFACE_EXTENSION = ".ttf";

    private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<String, Typeface>(
            4);

    public static Typeface getTypeFace(Context context, String fileName) {
        Typeface tempTypeface = sTypeFaces.get(fileName);

        if (tempTypeface == null) {
//            String fontPath = new StringBuilder(TYPEFACE_FOLDER).append('/').append(fileName).append(TYPEFACE_EXTENSION).toString();
            String fontPath = new StringBuilder(fileName).toString();
            tempTypeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            sTypeFaces.put(fileName, tempTypeface);
        }
        return tempTypeface;
    }
}
