package com.olfu.olfudisasterapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.SafeZonePagerAdapter;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.sliding.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SafeZoneBuildingActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    SlidingTabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    SafeZonePagerAdapter adapter;

    CharSequence title[][] = {{"UG", "2nd", "3rd", "4th", "5th"},
            {"LG", "UG", "2nd", "3rd", "4th"},
            {"UG", "2nd", "3rd", "4th", "5th"},
            {"UG", "2nd", "3rd", "4th", "5th"},
            {"UG", "2nd", "3rd"},
            {"UG", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th"},
            {"UG", "2nd", "3rd", "4th", "5th"},
            {"UG", "2nd", "3rd", "4th", "5th", "6th"},
            {"UG", "2nd", "3rd", "4th", "5th"},
            {"UG", "2nd", "3rd", "4th"},
            {"UG", "2nd", "3rd", "4th", "5th"},
            {"UG", "2nd", "3rd", "4th", "5th", "6th"}};
    int building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone_building);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        setupPager();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        building = extras.getInt(Const.SZ_BUILDING, 0);
        setTitle(extras.getString(Const.SZ_BUILDING_NAME));
    }

    private void setupPager() {
        adapter = new SafeZonePagerAdapter(getSupportFragmentManager(), title[building], building);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
    }
}
