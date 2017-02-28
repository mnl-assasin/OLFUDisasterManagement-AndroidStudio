package com.olfu.olfudisasterapp.data;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.olfu.olfudisasterapp.model.LoginItem;

/**
 * Created by mykelneds on 28/01/2017.
 */

public class EZSharedPreferences {

    private final static String USER_PREFERENCES = "OLFUDA";

    public final static String IS_LOGIN = "setLogin";

    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "password";

    public final static String KEY_ID = "id";
    public final static String KEY_USERNUMBER = "userNumber";
    public final static String KEY_COURSE = "course";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_LASTNAME = "lastName";
    public final static String KEY_FIRSTNAME = "firstName";
    public final static String KEY_MIDDLENAME = "middleName";
    public final static String KEY_CONTACTNUM = "contactNumber";
    public final static String KEY_USERTYPE = "userType";
    public final static String KEY_COURSENAME = "courseName";
    public final static String KEY_PROFILEPICTURE = "profilePicture";

    public static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void dropSharedPref(Context ctx) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * G E T T E R
     */

    public static boolean isLogin(Context ctx) {
        return getSharedPref(ctx).getBoolean(IS_LOGIN, false);
    }


    public static LoginItem getLoginCredential(Context ctx) {
        return new LoginItem(getSharedPref(ctx).getString(KEY_USERNAME, null), getSharedPref(ctx).getString(KEY_PASSWORD, null));
    }

    public static AccountInformation getAccountInfo(Context ctx) {
        String id = getSharedPref(ctx).getString(KEY_ID, null);
        String userNum = getSharedPref(ctx).getString(KEY_USERNUMBER, null);
        int course = getSharedPref(ctx).getInt(KEY_COURSE, 0);
        String email = getSharedPref(ctx).getString(KEY_EMAIL, null);
        String lastName = getSharedPref(ctx).getString(KEY_LASTNAME, null);
        String firstName = getSharedPref(ctx).getString(KEY_FIRSTNAME, null);
        String middleName = getSharedPref(ctx).getString(KEY_MIDDLENAME, null);
        String contactNum = getSharedPref(ctx).getString(KEY_CONTACTNUM, null);
        int userType = getSharedPref(ctx).getInt(KEY_USERTYPE, 0);
        String courseName = getSharedPref(ctx).getString(KEY_COURSENAME, null);
        String profilePicture = getSharedPref(ctx).getString(KEY_PROFILEPICTURE, null);

        return new AccountInformation(id, userNum, course, email, lastName, firstName,
                middleName, contactNum, userType, courseName, profilePicture);
    }

    public static String getValue(Context ctx, String KEY) {
        return getSharedPref(ctx).getString(KEY, "");
    }

    public static int getIntValue(Context ctx, String KEY) {
        return getSharedPref(ctx).getInt(KEY, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean getManifestReadStorage(Context ctx) {
        return getSharedPref(ctx).getBoolean(Manifest.permission.READ_EXTERNAL_STORAGE, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean getManifestWriteStorage(Context ctx) {
        return getSharedPref(ctx).getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, false);
    }


    /**
     * S E T T E R
     */

    public static void setLogin(Context ctx, boolean isLogin) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.apply();

    }

    public static void setLoginCredential(Context ctx, String username, String password) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static void setAccountInfo(Context ctx, AccountInformation info) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putString(KEY_ID, info.getId());
        editor.putString(KEY_USERNUMBER, info.getUserNum());
        editor.putInt(KEY_COURSE, info.getCourse());
        editor.putString(KEY_EMAIL, info.getEmail());
        editor.putString(KEY_LASTNAME, info.getLastName());
        editor.putString(KEY_FIRSTNAME, info.getFirstName());
        editor.putString(KEY_MIDDLENAME, info.getMiddleName());
        editor.putString(KEY_CONTACTNUM, info.getContactNum());
        editor.putInt(KEY_USERTYPE, info.getUserType());
        editor.putString(KEY_COURSENAME, info.getCourseName());
        editor.putString(KEY_PROFILEPICTURE, info.getProfilePicture());
        editor.apply();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setManifestReadStorage(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putBoolean(Manifest.permission.READ_EXTERNAL_STORAGE, status);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setManifestWriteStorage(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE, status);
        editor.apply();
    }
}
