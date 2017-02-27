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
public class EditTextRoman extends EditText {

    public EditTextRoman(Context context) {
        super(context);
        setTypeface(FontStyle.helveticaRoman(context));
        setMaxLines(1);
    }

    public EditTextRoman(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontStyle.helveticaRoman(context));
        setMaxLines(1);
    }

    public EditTextRoman(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontStyle.helveticaRoman(context));
        setMaxLines(1);
    }

}