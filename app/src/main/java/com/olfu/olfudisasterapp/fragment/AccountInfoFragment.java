package com.olfu.olfudisasterapp.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.ACTAdapter;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.data.AccountInformation;
import com.olfu.olfudisasterapp.data.Const;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;
import com.olfu.olfudisasterapp.util.FileHelper;
import com.olfu.olfudisasterapp.widgets.ButtonMed;
import com.olfu.olfudisasterapp.widgets.EditTextRoman;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountInfoFragment extends BaseFragment {

    String TAG = AccountInfoFragment.class.getSimpleName();

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
    @Bind(R.id.actCourse)
    AutoCompleteTextView actCourse;
    @Bind(R.id.tilCourse)
    TextInputLayout tilCourse;
    @Bind(R.id.btnSignup)
    ButtonMed btnSignup;

    int course = -1;

    Uri imageUri = null;


    public AccountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounting, container, false);
        ButterKnife.bind(this, view);

        initData();
        initListener();

        return view;
    }

    private void initData() {


        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.course)));
        ACTAdapter adapter = new ACTAdapter(getActivity(), R.layout.act_item, list);
        actCourse.setAdapter(adapter);

        AccountInformation info = EZSharedPreferences.getAccountInfo(getActivity());
        String firstName = info.getFirstName();
        String lastName = info.getLastName();
        int course = info.getCourse();
        String contactNum = info.getContactNum();

        etFirstName.setText(firstName);
        etLastName.setText(lastName);
        actCourse.setText(list.get(course - 1));
        etContactNum.setText(contactNum);


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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
        final Dialog dialog = new Dialog(getActivity());
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
        String lName = etLastName.getText().toString().trim();
        String fName = etFirstName.getText().toString().trim();
        String mName = "";
        String contactNum = etContactNum.getText().toString().trim();

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

        if (course == -1) {
            tilCourse.setError("Enter your Course");
            isValid = false;
        }

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put(Const.BODY_COURSE, FileHelper.createPartFromString(String.valueOf(course)));
        map.put(Const.BODY_LASTNAME, FileHelper.createPartFromString(lName));
        map.put(Const.BODY_FIRSTNAME, FileHelper.createPartFromString(fName));
        map.put(Const.BODY_MIDDLENAME, FileHelper.createPartFromString(mName));
        map.put(Const.BODY_CONTACTNUM, FileHelper.createPartFromString(contactNum));

        MultipartBody.Part body = FileHelper.prepareFilePart(getActivity(), Const.BODY_PROFILE_PICTURE, imageUri);
        String userId = EZSharedPreferences.getValue(getActivity(), EZSharedPreferences.KEY_ID);
        updateUser(userId, map, body);

    }

    private void updateUser(String userId, HashMap<String, RequestBody> map, MultipartBody.Part body) {

        startProgressDialog("Updating user....");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> call = api.postUserUpdate(userId, map, body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                stopProgressDialog();
                if (response.isSuccessful()) {
                    Log.d(TAG, "Update user successful");
                    reportResult("Update user successful");
                } else {
                    Log.d(TAG, "Update user failed");
                    reportResult("Update user failed");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "Update user onFailure");
                reportResult("Something went wrong please try again...");
            }
        });
    }

    private void reportResult(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initData();
                dialog.dismiss();

            }
        });
    }

}
