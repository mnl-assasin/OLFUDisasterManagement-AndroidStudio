package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.api.RequestLogin;
import com.olfu.olfudisasterapp.api.ResponseLogin;
import com.olfu.olfudisasterapp.data.AccountInformation;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;
import com.olfu.olfudisasterapp.widgets.TextViewMed;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.etUsername)
    EditTextRoman etUsername;
    @Bind(R.id.tilUsername)
    TextInputLayout tilUsername;
    @Bind(R.id.etPassword)
    EditTextRoman etPassword;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;
    @Bind(R.id.btnLogin)
    ButtonMed btnLogin;
    @Bind(R.id.tvSignUp)
    TextViewMed tvSignUp;
    @Bind(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initToolbar();
        initListener();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorText));
        toolbar.setTitle("Login");
    }

    private void initListener() {
        etUsername.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilUsername.setErrorEnabled(false);
                return false;
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilPassword.setErrorEnabled(false);
                return false;
            }
        });
    }

    @OnClick({R.id.btnLogin, R.id.tvSignUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                onLoginClick();
                break;
            case R.id.tvSignUp:
                onSignupClick();
                break;
        }
    }

    private void onSignupClick() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }


    //
    private void onLoginClick() {

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        boolean isValid = true;

        if (username.equals("")) {
            tilUsername.setError("Enter your username");
            isValid = false;
        }

        if (password.equals("")) {
            tilPassword.setError("Enter your password");
            isValid = false;
        }

        if (isValid)
            requestLogin(username, password);
    }

    private void requestLogin(final String username, final String password) {
        startProgressDialog("Logging in...");

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = api.postLogin(new RequestLogin(username, password));
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                stopProgressDialog();
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

                    EZSharedPreferences.setLogin(LoginActivity.this, true);
                    EZSharedPreferences.setLoginCredential(LoginActivity.this, username, password);
                    EZSharedPreferences.setAccountInfo(LoginActivity.this, information);
                    startActivity(new Intent(getApplicationContext(), MainSPActivity.class));
                    finish();

                } else {
                    Log.d(TAG, "Login Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                stopProgressDialog();
                Log.d(TAG, "onFailure: Login Failed");
            }
        });

    }
}
