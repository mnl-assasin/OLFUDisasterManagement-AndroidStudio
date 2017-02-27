package com.olfu.olfudisasterapp.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.olfu.olfudisasterapp.util.FontStyle;


/**
 * Created by mykelneds on 15/01/2017.
 */

@SuppressLint("AppCompatCustomView")
public class ButtonBold extends Button {

    public ButtonBold(Context context) {
        super(context);
        setTypeface(FontStyle.helveticaBold(context));
    }

    public ButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontStyle.helveticaBold(context));
    }

    public ButtonBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontStyle.helveticaBold(context));
    }

}