package com.olfu.olfudisasterapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.activity.PrecautionEarthquakeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrecautionaryFragment extends Fragment {


    @Bind(R.id.ivFire)
    ImageView ivFire;
    @Bind(R.id.ivFlood)
    ImageView ivFlood;
    @Bind(R.id.ivEarthquake)
    ImageView ivEarthquake;

    public PrecautionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_precautionary, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ivFire, R.id.ivFlood, R.id.ivEarthquake})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivFire:
                break;
            case R.id.ivFlood:
                break;
            case R.id.ivEarthquake:
                startActivity(new Intent(getActivity(), PrecautionEarthquakeActivity.class));
                break;
        }
    }
}
