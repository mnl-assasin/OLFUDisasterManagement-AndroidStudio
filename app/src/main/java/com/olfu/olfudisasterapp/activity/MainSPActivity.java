package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.api.RequestLogin;
import com.olfu.olfudisasterapp.api.ResponseLogin;
import com.olfu.olfudisasterapp.data.AccountInformation;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;
import com.olfu.olfudisasterapp.fragment.AccountInfoFragment;
import com.olfu.olfudisasterapp.fragment.AnnouncementFragment;
import com.olfu.olfudisasterapp.fragment.HelpFragment;
import com.olfu.olfudisasterapp.fragment.PrecautionaryFragment;
import com.olfu.olfudisasterapp.fragment.SafeZoneFragment;
import com.olfu.olfudisasterapp.model.LoginItem;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSPActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = MainSPActivity.class.getSimpleName();

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
    @Bind(R.id.fabEmergency)
    FloatingActionButton fabEmergency;

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
        requestLogin();
        setupNavigationView();


    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void requestLogin() {

        LoginItem item = EZSharedPreferences.getLoginCredential(MainSPActivity.this);
        String username = item.getUsername();
        String password = item.getPassword();
        Log.d(TAG, username + " : " + password);

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = api.postLogin(new RequestLogin(username, password));
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {

                    ResponseLogin resp = response.body();
                    String id = resp.getId();
                    String userNum = resp.getUserNum();
                    int course = Integer.parseInt(resp.getCourse());
                    String email = resp.getEmail();
                    String lastName = resp.getLastName();
                    String firstName = resp.getFirstName();
                    String middleName = resp.getMiddleName();
                    String contactNum = resp.getContactNum();
                    int userType = Integer.parseInt(resp.getUserType());
                    String courseName = resp.getCourseName();
                    String profilePicture = resp.getProfilePicture();

                    AccountInformation information = new AccountInformation(id, userNum, course,
                            email, lastName, firstName, middleName, contactNum, userType, courseName, profilePicture);

                    EZSharedPreferences.setAccountInfo(MainSPActivity.this, information);
                    setupHeader();

                } else {
                    Log.d(TAG, "Login Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                setupHeader();
                Log.d(TAG, "onFailure: Login Failed");
            }
        });

    }

    private void setupInitialView() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragmentLayout, new AnnouncementFragment()).commit();
    }

    private void setupHeader() {
        civProfilePic = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.civProfilePic);
        tvUsername = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvUsername);
        tvEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvEmail);

        String link = EZSharedPreferences.getValue(MainSPActivity.this, EZSharedPreferences.KEY_PROFILEPICTURE);
        Log.d(TAG, "Link: " + link);
        Picasso.with(MainSPActivity.this).load(link).placeholder(R.drawable.img_profile_white).into(civProfilePic);
        tvUsername.setText(EZSharedPreferences.getValue(MainSPActivity.this, EZSharedPreferences.KEY_FIRSTNAME) + " " + EZSharedPreferences.getValue(MainSPActivity.this, EZSharedPreferences.KEY_LASTNAME));
        tvEmail.setText(EZSharedPreferences.getValue(MainSPActivity.this, EZSharedPreferences.KEY_EMAIL));

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
            case R.id.menu_signout:
                signOut();
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

    private void signOut() {
        EZSharedPreferences.setLogin(MainSPActivity.this, false);
        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
        finish();
    }

    @OnClick(R.id.fabEmergency)
    public void onClick() {
        Log.d(TAG, "FAB CLICK");
        startActivity(new Intent(getApplicationContext(), EmergencyActivity.class));
    }
}
