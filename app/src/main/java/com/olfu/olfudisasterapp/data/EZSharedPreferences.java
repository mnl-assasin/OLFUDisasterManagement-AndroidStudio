package com.olfu.olfudisasterapp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mykelneds on 28/01/2017.
 */

public class EZSharedPreferences {

    private final static String USER_PREFERENCES = "OLFUDA";

    private final static String IS_LOGIN = "isLogin";
    private final static String ACCOUNT_TYPE = "accountType";


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

    public static int getAccountType(Context ctx){
        return getSharedPref(ctx).getInt(ACCOUNT_TYPE, 0);
    }

    /**
     * S E T T E R
     */
}
