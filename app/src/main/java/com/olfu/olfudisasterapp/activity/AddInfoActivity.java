package com.olfu.olfudisasterapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.ACTAdapter;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.builder.ToastBuilder;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.util.FileHelper;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInfoActivity extends BaseActivity {

    String TAG = AddInfoActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @Bind(R.id.etLastName)
    EditTextRoman etLastName;
    @Bind(R.id.tilLastName)
    TextInputLayout tilLastName;
    @Bind(R.id.etFirstName)
    EditTextRoman etFirstName;
    @Bind(R.id.tilFirstName)
    TextInputLayout tilFirstName;
    @Bind(R.id.etContactNum)
    EditTextRoman etContactNum;
    @Bind(R.id.tilContactNum)
    TextInputLayout tilContactNum;
    @Bind(R.id.etStudNum)
    EditTextRoman etStudNum;
    @Bind(R.id.tilStudNum)
    TextInputLayout tilStudNum;
    @Bind(R.id.actCourse)
    AutoCompleteTextView actCourse;
    @Bind(R.id.tilCourse)
    TextInputLayout tilCourse;
    @Bind(R.id.btnSignup)
    ButtonMed btnSignup;

    int accountType;
    String email;
    String username;
    String password;
    int course = -1;

    Uri imageUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initListener();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    private void initData() {

        Bundle b = getIntent().getExtras();
        if (b != null) {
            accountType = b.getInt(Const.ACCOUNT_TYPE);
            email = b.getString(Const.EMAIL);
            username = b.getString(Const.USERNAME);
            password = b.getString(Const.PASSWORD);

            if (accountType == Const.TYPE_PARENT)
                tilCourse.setVisibility(View.INVISIBLE);
            else if (accountType == Const.TYPE_STUDENT)
                tilCourse.setVisibility(View.VISIBLE);
        }


        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.course)));
        ACTAdapter adapter = new ACTAdapter(AddInfoActivity.this, R.layout.act_item, list);
        actCourse.setAdapter(adapter);

    }

    private void initListener() {
        etLastName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilLastName.setErrorEnabled(false);
                return false;
            }
        });

        etFirstName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilFirstName.setErrorEnabled(false);
                return false;
            }
        });

        etStudNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilStudNum.setErrorEnabled(false);
                return false;
            }
        });

        etContactNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tilContactNum.setErrorEnabled(false);
                return false;
            }
        });

        actCourse.setOnKeyListener(null);
        actCourse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((AutoCompleteTextView) view).showDropDown();
                tilCourse.setErrorEnabled(false);
                return false;
            }
        });

        actCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Position: " + position);
                course = position + 1;
                Log.d(TAG, "course: " + course);
            }
        });
    }

    @OnClick({R.id.civProfilePic, R.id.btnSignup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civProfilePic:
                onProfilePicClick();
                break;
            case R.id.btnSignup:
                onSignupClick();
                break;
        }
    }

    private void onProfilePicClick() {
        final Dialog dialog = new Dialog(AddInfoActivity.this);
        dialog.setTitle("Choose Action");
        dialog.setContentView(R.layout.dialog_image_chooser);
        dialog.show();

        LinearLayout containerCamera = (LinearLayout) dialog.findViewById(R.id.containerCamera);
        containerCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onCameraClick();
            }


        });
        LinearLayout containerFiles = (LinearLayout) dialog.findViewById(R.id.containerFiles);
        containerFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onFilesClick();
            }
        });
    }

    private void onCameraClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Const.REQUEST_TAKE_IMAGE);
    }


    private void onFilesClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Const.REQUEST_PICK_IMAGE);
    }

    private void onSignupClick() {

        /**
         * email
         * username
         * passsword
         * accountType
         * course
         */
        String lName = etLastName.getText().toString().trim();
        String fName = etFirstName.getText().toString().trim();
        String mName = "";
        String contactNum = etContactNum.getText().toString().trim();
        String studNum = etStudNum.getText().toString().trim();

        boolean isValid = true;

        if (lName.equals("")) {
            tilLastName.setError("Enter your Last Name");
            isValid = false;
        }

        if (fName.equals("")) {
            tilFirstName.setError("Enter your First Name");
            isValid = false;
        }

        if (contactNum.equals("")) {
            tilContactNum.setError("Enter your Contact Number");
            isValid = false;
        }

        if (studNum.equals("")) {
            tilStudNum.setError("Enter your Student Number");
            isValid = false;
        }

        if (course == -1 && accountType == Const.TYPE_STUDENT) {
            tilCourse.setError("Enter your Course");
            isValid = false;
        }

        if (isValid) {
            Log.d(TAG, "Last Name: " + lName);
            Log.d(TAG, "Middle Name: " + mName);
            Log.d(TAG, "First Name: " + fName);
            Log.d(TAG, "Contact Num: " + contactNum);
            Log.d(TAG, "Student Num: " + studNum);
            Log.d(TAG, "Course: " + course);
            Log.d(TAG, "Email: " + email);
            Log.d(TAG, "Username: " + username);
            Log.d(TAG, "Password: " + password);
            Log.d(TAG, "AccountType: " + accountType);

            HashMap<String, RequestBody> map = new HashMap<>();
            map.put(Const.BODY_STUD_ID, FileHelper.createPartFromString(studNum));
            map.put(Const.BODY_USERNAME, FileHelper.createPartFromString(username));
            map.put(Const.BODY_PASSWORD, FileHelper.createPartFromString(password));
            map.put(Const.BODY_COURSE, FileHelper.createPartFromString(String.valueOf(course)));
            map.put(Const.BODY_EMAIL, FileHelper.createPartFromString(email));
            map.put(Const.BODY_LASTNAME, FileHelper.createPartFromString(lName));
            map.put(Const.BODY_FIRSTNAME, FileHelper.createPartFromString(fName));
            map.put(Const.BODY_MIDDLENAME, FileHelper.createPartFromString(mName));
            map.put(Const.BODY_CONTACTNUM, FileHelper.createPartFromString(contactNum));
            map.put(Const.BODY_USERTYPE, FileHelper.createPartFromString(String.valueOf(accountType)));

            MultipartBody.Part body = FileHelper.prepareFilePart(AddInfoActivity.this, Const.BODY_PROFILE_PICTURE, imageUri);
            registerUser(map, body);
        }
    }

    private void registerUser(HashMap<String, RequestBody> map, MultipartBody.Part body) {
        startProgressDialog("Signing up...");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = api.postRegister(map, body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                stopProgressDialog();

                if (response.isSuccessful()) {
                    Log.d(TAG, "Sign up Successful");
                    reportResult("Sign up successful");
                } else {
                    try {
                        String error = response.errorBody().string().toString();
                        Log.d(TAG, "Error: " + error);
                        ToastBuilder.createLongToast(AddInfoActivity.this, "Something went wrong please try again");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                stopProgressDialog();
                Log.d(TAG, "onFailure");
            }
        });
    }

    private void reportResult(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddInfoActivity.this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == Const.REQUEST_PICK_IMAGE || requestCode == Const.REQUEST_TAKE_IMAGE) && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            File f = new File(uri.getPath());
            File f2 = new File(uri.toString());
            Log.d(TAG, f.getAbsolutePath() + " : " + f2.getAbsolutePath());

            imageUri = uri;
            String path = FileHelper.getRealPathFromURI(AddInfoActivity.this, uri);
            Log.d(TAG, "Image Path: " + path);
            Picasso.with(getApplicationContext()).load(uri).into(civProfilePic);
        }
    }


}
