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
public class EditTextMed extends EditText {

    public EditTextMed(Context context) {
        super(context);
        setTypeface(FontStyle.helveticaMed(context));
        setMaxLines(1);
    }

    public EditTextMed(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontStyle.helveticaMed(context));
        setMaxLines(1);
    }

    public EditTextMed(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontStyle.helveticaMed(context));
        setMaxLines(1);
    }

}