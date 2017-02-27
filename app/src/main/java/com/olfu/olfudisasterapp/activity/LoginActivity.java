package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;
import com.olfu.olfudisasterapp.widgets.TextViewMed;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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
    }
}
