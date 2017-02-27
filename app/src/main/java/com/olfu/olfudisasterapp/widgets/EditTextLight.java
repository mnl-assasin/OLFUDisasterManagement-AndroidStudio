package com.olfu.olfudisasterapp.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.olfu.olfudisasterapp.util.FontStyle;

/**
 * Created by mykelneds on 15/01/2017.
 */

@SuppressLint("AppCompatCustomView")
public class EditTextLight extends EditText {

    public EditTextLight(Context context) {
        super(context);
        setTypeface(FontStyle.helveticaLight(context));
        setMaxLines(1);
    }

    public EditTextLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontStyle.helveticaLight(context));
        setMaxLines(1);
    }

    public EditTextLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontStyle.helveticaLight(context));
        setMaxLines(1);
    }

}