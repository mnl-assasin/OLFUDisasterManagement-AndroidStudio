package com.olfu.olfudisasterapp.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * Created by mleano on 2/28/2017.
 */

public class BaseFragment extends Fragment {

    ProgressDialog pDialog;
    public void startProgressDialog(String message){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(message);
        pDialog.show();
    }

    public void stopProgressDialog(){
        pDialog.dismiss();
    }
}
