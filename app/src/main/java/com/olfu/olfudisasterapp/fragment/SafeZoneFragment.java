package com.olfu.olfudisasterapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.activity.SafeZoneBuildingActivity;
import com.olfu.olfudisasterapp.adapter.SafeZoneAdapter;
import com.olfu.olfudisasterapp.data.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SafeZoneFragment extends Fragment {
    String TAG = SafeZoneFragment.class.getSimpleName();

    @Bind(R.id.listView)
    ListView listView;

    public SafeZoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_safe_zone, container, false);
        ButterKnife.bind(this, view);

        initData();
        initListener();

        return view;
    }

    private void initData() {
        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.buildings)));
        SafeZoneAdapter adapter = new SafeZoneAdapter(getActivity(), R.layout.safezone_item, list);
        listView.setAdapter(adapter);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Pos: " + i);
                Bundle extras = new Bundle();

                extras.putString(Const.SZ_BUILDING_NAME, adapterView.getItemAtPosition(i).toString());
                extras.putInt(Const.SZ_BUILDING, i);
                startActivity(new Intent(getActivity(), SafeZoneBuildingActivity.class).putExtras(extras));
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
