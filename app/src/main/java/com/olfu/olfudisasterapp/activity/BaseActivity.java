package com.olfu.olfudisasterapp.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mleano on 2/28/2017.
 */

public class BaseActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    public void startProgressDialog(String message){
        pDialog = new ProgressDialog(BaseActivity.this);
        pDialog.setMessage(message);
        pDialog.show();
    }

    public void stopProgressDialog(){
        pDialog.dismiss();
    }
}
