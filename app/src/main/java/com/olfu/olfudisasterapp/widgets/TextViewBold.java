package com.olfu.olfudisasterapp.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.olfu.olfudisasterapp.util.FontStyle;

/**
 * Created by mykelneds on 15/01/2017.
 */

@SuppressLint("AppCompatCustomView")
public class TextViewBold extends TextView {

    public TextViewBold(Context context) {
        super(context);
        setTypeface(FontStyle.helveticaBold(context));
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontStyle.helveticaBold(context));
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontStyle.helveticaBold(context));
    }

}