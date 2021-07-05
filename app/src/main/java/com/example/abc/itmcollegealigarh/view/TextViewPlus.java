package com.example.abc.itmcollegealigarh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.utils.NumberUtils;

import java.util.logging.Logger;


public class TextViewPlus extends AppCompatTextView {

    public TextViewPlus(Context context) {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.TextViewPlus_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = TypeFaceProvider.getTypeFace(ctx, asset);
        } catch (Exception e) {
            Log.i("Could not get typeface" , e.getMessage());
            return false;
        }
        setTypeface(tf);
//        setLetterSpacing();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setLetterSpacing() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                setLetterSpacing(0.02f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDash() {
        try {
            setText("-");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCommaSeparatedText(String text) {
        try {
            if (NumberUtils.isNumericDecimal(text)) {
                setText(NumberUtils.getCommaSeparated(text));
            } else {
                setText(text);
            }
        } catch (Exception e) {
            try {
                setText(text);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setNumber(Object number) {
        try {
            setText(NumberUtils.getCommaSeparated(String.valueOf(number)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setCommaSeparatedText(float text) {
        try {
            if (NumberUtils.isNumericDecimal(String.valueOf(text))) {
                setText(NumberUtils.getCommaSeparated(text));
            } else {
                setNumber(text);
            }
        } catch (Exception e) {
            try {
                setNumber(text);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
