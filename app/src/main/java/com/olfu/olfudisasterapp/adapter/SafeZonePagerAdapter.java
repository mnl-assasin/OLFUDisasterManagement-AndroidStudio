package com.olfu.olfudisasterapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.fragment.SafeZoneBuildingFragment;

/**
 * Created by mykelneds on 26/02/2017.
 */

public class SafeZonePagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int building;

    public SafeZonePagerAdapter(FragmentManager fm, CharSequence[] titles, int building) {
        super(fm);
        this.titles = titles;
        this.building = building;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(Const.SZ_BUILDING, building);
        args.putInt(Const.SZ_FLOOR, position);
        SafeZoneBuildingFragment fragment = new SafeZoneBuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
