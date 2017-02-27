package com.olfu.olfudisasterapp.builder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by mykelneds on 27/01/2017.
 */

public class DialogBuilder {

    public static AlertDialog.Builder getAlertDialog(Context ctx) {
        return new AlertDialog.Builder(ctx);
    }

    public static void alertDialog(Context ctx, String message) {
        AlertDialog.Builder builder = getAlertDialog(ctx);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void alertDialog(Context ctx, String title, String message) {
        AlertDialog.Builder builder = getAlertDialog(ctx);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void alertDialog(Context ctx, String title, String message, String positiveBtn, DialogInterface.OnClickListener positiveOnClick) {
        AlertDialog.Builder builder = getAlertDialog(ctx);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveBtn, positiveOnClick);
        builder.show();
    }
}
