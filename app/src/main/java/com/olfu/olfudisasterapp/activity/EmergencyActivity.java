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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.ACTAdapter;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.builder.ToastBuilder;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;
import com.olfu.olfudisasterapp.util.FileHelper;
import com.olfu.olfudisasterapp.util.GPSTracker;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyActivity extends BaseActivity {

    String TAG = EmergencyActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ivImage)
    ImageView ivImage;
    @Bind(R.id.etTitle)
    EditTextRoman etTitle;
    @Bind(R.id.tilTitle)
    TextInputLayout tilTitle;
    @Bind(R.id.etContent)
    EditTextRoman etContent;
    @Bind(R.id.tilContent)
    TextInputLayout tilContent;
    @Bind(R.id.btnSubmit)
    ButtonMed btnSubmit;

    Uri imageUri = null;
    @Bind(R.id.actDisasterType)
    AutoCompleteTextView actDisasterType;
    @Bind(R.id.tilDisasterType)
    TextInputLayout tilDisasterType;

    int disasterType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initListener();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Report an Emergency");
    }

    private void initData() {

        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.disaster_type)));
        ACTAdapter adapter = new ACTAdapter(EmergencyActivity.this, R.layout.act_item, list);
        actDisasterType.setAdapter(adapter);

    }

    private void initListener() {

        etTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tilTitle.setErrorEnabled(false);
                return false;
            }
        });

        etContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tilContent.setErrorEnabled(false);
                return false;
            }
        });

        actDisasterType.setOnKeyListener(null);
        actDisasterType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((AutoCompleteTextView) view).showDropDown();
                tilDisasterType.setErrorEnabled(false);
                return false;
            }
        });

        actDisasterType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                disasterType = i;
            }
        });

    }

    @OnClick({R.id.ivImage, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivImage:
                onImageClick();
                break;
            case R.id.btnSubmit:
                onSubmitClick();
                break;
        }
    }

    private void onImageClick() {
        final Dialog dialog = new Dialog(EmergencyActivity.this);
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

    private void onSubmitClick() {
        GPSTracker gps = new GPSTracker(EmergencyActivity.this);
        double latitude = 0;
        double longitude = 0;
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            ToastBuilder.createLongToast(getApplicationContext(), "Location = " + latitude + " : " + longitude);
        } else {
            gps.showSettingsAlert();
        }

        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        String userId = EZSharedPreferences.getValue(EmergencyActivity.this, EZSharedPreferences.KEY_ID);

        boolean isValid = true;

        if (title.equals("")) {
            tilTitle.setError("Insert Title of yout report");
            isValid = false;
        }

        if (content.equals("")) {
            tilContent.setError("Insert description of your report");
            isValid = false;
        }

        if (disasterType == -1) {
            tilDisasterType.setError("Please select a disaster type");
            isValid = false;
        }


        if (isValid) {
            HashMap<String, RequestBody> map = new HashMap<>();
            map.put(Const.REPORT_TITLE, FileHelper.createPartFromString(title));
            map.put(Const.REPORT_CONTENT, FileHelper.createPartFromString(content));
            map.put(Const.REPORT_USERID, FileHelper.createPartFromString(userId));
            map.put(Const.REPORT_LATITUDE, FileHelper.createPartFromString(String.valueOf(latitude)));
            map.put(Const.REPORT_LONGITUDE, FileHelper.createPartFromString(String.valueOf(longitude)));
            map.put(Const.REPORT_CATEGORY, FileHelper.createPartFromString(String.valueOf(disasterType)));

            MultipartBody.Part body = FileHelper.prepareFilePart(EmergencyActivity.this, Const.REPORT_POSTIMAGE, imageUri);
            postReport(map, body);
        }


        Log.d(TAG, "title: " + title);
        Log.d(TAG, "content: " + content);
        Log.d(TAG, "userId: " + userId);


    }

    private void postReport(HashMap<String, RequestBody> map, MultipartBody.Part body) {
        startProgressDialog("Submitting report...");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = api.postReport(map, body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                stopProgressDialog();
                if (response.isSuccessful()) {
                    Log.d(TAG, "Report posting successful");
                    reportResult("Report Posted Successfully");
                } else {
                    Log.d(TAG, "Report posting failed");
                    reportResult("Something went wrong, please try again");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                stopProgressDialog();
                Log.d(TAG, "onFailure: Post Report");
            }
        });
    }

    private void reportResult(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmergencyActivity.this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
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
            String path = FileHelper.getRealPathFromURI(EmergencyActivity.this, uri);
            Log.d(TAG, "Image Path: " + path);
            Picasso.with(getApplicationContext()).load(uri).into(ivImage);
        }
    }


}
