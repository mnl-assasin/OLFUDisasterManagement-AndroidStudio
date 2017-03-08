package com.olfu.olfudisasterapp.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.fragment.AccountInfoFragment;
import com.olfu.olfudisasterapp.fragment.AnnouncementFragment;
import com.olfu.olfudisasterapp.fragment.HelpFragment;
import com.olfu.olfudisasterapp.fragment.PrecautionaryFragment;
import com.olfu.olfudisasterapp.fragment.SafeZoneFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainSPActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fragmentLayout)
    FrameLayout fragmentLayout;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    CircleImageView civProfilePic;
    TextView tvUsername;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sp);
        ButterKnife.bind(this);

        initialized();
    }

    private void initialized() {

        setupToolbar();
        setupInitialView();
        setupHeader();
        setupNavigationView();


    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupInitialView() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragmentLayout, new AnnouncementFragment()).commit();
    }

    private void setupHeader(){
        civProfilePic = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.civProfilePic);
        tvUsername = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvUsername);
        tvEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvEmail);

    }

    private void setupNavigationView() {

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
//                fragmentFrame.setTranslationX(slideOffset * drawerView.getWidth());
//                drawerLayout.bringChildToFront(fragmentFrame);
//                drawerLayout.requestLayout();
            }
        };


        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String title = null;
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment mFragment = null;

        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);
        //if(item.isChecked())


        switch (item.getItemId()) {
            case R.id.menu_announcement:
                mFragment = new AnnouncementFragment();
                break;
            case R.id.menu_disaster:
                mFragment = new PrecautionaryFragment();
                break;
            case R.id.menu_hotline:
                mFragment = new HelpFragment();
                break;
            case R.id.menu_safe_zone:
                mFragment = new SafeZoneFragment();
                break;
            case R.id.menu_account_info:
                mFragment = new AccountInfoFragment();
                break;

        }


        if (mFragment != null) {
            mFragmentTransaction.replace(R.id.fragmentLayout, mFragment);
            mFragmentTransaction.commit();
//            setTitle(title);
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
