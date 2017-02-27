package com.olfu.olfudisasterapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.ACTAdapter;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;
import com.olfu.olfudisasterapp.widgets.TextViewMed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.actAccType)
    AutoCompleteTextView actAccType;
    @Bind(R.id.tilAccType)
    TextInputLayout tilAccType;

    @Bind(R.id.etEmail)
    EditTextRoman etEmail;
    @Bind(R.id.tilEmail)
    TextInputLayout tilEmail;

    @Bind(R.id.etUsername)
    EditTextRoman etUsername;
    @Bind(R.id.tilUsername)
    TextInputLayout tilUsername;

    @Bind(R.id.etPassword)
    EditTextRoman etPassword;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;

    @Bind(R.id.btnNext)
    ButtonMed btnNext;

    @Bind(R.id.tvLogin)
    TextViewMed tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initListener();
    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorText));
        toolbar.setTitle("Sign up");
    }

    private void initData() {
        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.account_type)));
        ACTAdapter adapter = new ACTAdapter(SignupActivity.this, R.layout.act_item, list);
        actAccType.setAdapter(adapter);
    }

    private void initListener() {

        actAccType.setKeyListener(null);
        actAccType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((AutoCompleteTextView) view).showDropDown();
                tilAccType.setErrorEnabled(false);
                return false;
            }
        });

        etUsername.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilUsername.setErrorEnabled(false);
                return false;
            }
        });

        etEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilEmail.setErrorEnabled(false);
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


    @OnClick({R.id.btnNext, R.id.tvLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                onNextClick();
                break;
            case R.id.tvLogin:
                onLoginClick();
                break;
        }
    }

    private void onNextClick() {

        String accType = actAccType.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        int accountType = -1;
        boolean isValid = true;

        if (accType.equals("")) {
            tilAccType.setError("Select your account type");
            isValid = false;
        }

        if (accType.equals(Const.PARENT))
            accountType = Const.TYPE_PARENT;
        if (accType.equals(Const.STUDENT))
            accountType = Const.TYPE_STUDENT;

        if (username.equals("")) {
            tilUsername.setError("Please enter a username");
            isValid = false;
        }

        if (email.equals("")) {
            tilEmail.setError("Please enter your email");
            isValid = false;
        }

        if (password.equals("")) {
            tilPassword.setError("Please enter a password");
            isValid = false;
        }

        if (isValid) {
            Bundle extras = new Bundle();
            extras.putInt(Const.ACCOUNT_TYPE, accountType);
            extras.putString(Const.USERNAME, username);
            extras.putString(Const.PASSWORD, password);
            extras.putString(Const.EMAIL, email);

            startActivity(new Intent(getApplicationContext(), AddInfoActivity.class).putExtras(extras));
        }

    }

    private void onLoginClick() {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

}
