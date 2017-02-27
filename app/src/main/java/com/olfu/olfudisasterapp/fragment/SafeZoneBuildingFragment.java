package com.olfu.olfudisasterapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.widgets.TouchImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SafeZoneBuildingFragment extends Fragment {


    @Bind(R.id.tivFloor)
    TouchImageView tivFloor;

    public int background[][] = {{R.drawable.ams_1, R.drawable.ams_2, R.drawable.ams_3, R.drawable.ams_4, R.drawable.ams_5},
            {R.drawable.bed_lg, R.drawable.bed_ug, R.drawable.bed_2, R.drawable.bed_3},
            {R.drawable.cas_ug, R.drawable.cas_2, R.drawable.cas_3, R.drawable.cas_4, R.drawable.cas_5},
            {R.drawable.gsb_ug, R.drawable.gsb_2, R.drawable.gsb_3, R.drawable.gsb_4, R.drawable.gsb_5},
            {R.drawable.kcb_ug, R.drawable.kcb_2, R.drawable.kcb_3},
            {R.drawable.mb_ug, R.drawable.mb_2, R.drawable.mb_3, R.drawable.mb_4, R.drawable.mb_5, R.drawable.mb_6, R.drawable.mb_7, R.drawable.mb_8, R.drawable.mb_9, R.drawable.mb_10, R.drawable.mb_11},
            {R.drawable.pjp_ug, R.drawable.pjp_2, R.drawable.pjp_3, R.drawable.pjp_4, R.drawable.pjp_5},
            {R.drawable.ppb_ug, R.drawable.ppb_2, R.drawable.ppb_3, R.drawable.ppb_4, R.drawable.ppb_5, R.drawable.ppb_6},
            {R.drawable.sbh_ug, R.drawable.sbh_2, R.drawable.sbh_3, R.drawable.sbh_4, R.drawable.sbh_5},
            {R.drawable.sjb_ug, R.drawable.sjb_2, R.drawable.sjb_3, R.drawable.sjb_4},
            {R.drawable.slh_ug, R.drawable.slh_2, R.drawable.slh_3, R.drawable.slh_4, R.drawable.slh_5},
            {R.drawable.smdp_ug, R.drawable.smdp_2, R.drawable.smdp_3, R.drawable.smdp_4, R.drawable.smdp_5, R.drawable.smdp_6},
    };

    public SafeZoneBuildingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_safe_zone_building, container, false);
        ButterKnife.bind(this, view);

        initFloor();
        return view;
    }

    private void initFloor() {
        int building = getArguments().getInt(Const.SZ_BUILDING, 0);
        int floor = getArguments().getInt(Const.SZ_FLOOR, 0);
        tivFloor.setImageResource(background[building][floor]);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
