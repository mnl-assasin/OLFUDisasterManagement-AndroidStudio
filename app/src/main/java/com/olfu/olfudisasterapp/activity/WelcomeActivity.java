package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.widgets.ButtonMed;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends TranslucentActivity {

    @Bind(R.id.btnNext)
    ButtonMed btnSignup;
    @Bind(R.id.btnLogin)
    ButtonMed btnLogin;
    @Bind(R.id.layoutWelcome)
    RelativeLayout layoutWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnNext, R.id.btnLogin})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnNext:
                intent= new Intent(WelcomeActivity.this, SignupActivity.class);
                break;
            case R.id.btnLogin:
                intent= new Intent(WelcomeActivity.this, LoginActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
            finish();
        }
    }
}
