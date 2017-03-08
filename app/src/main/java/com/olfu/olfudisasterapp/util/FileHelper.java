package com.olfu.olfudisasterapp.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by mleano on 2/28/2017.
 */

public class FileHelper {

    public static MultipartBody.Part prepareFilePart(Context ctx, String partName, Uri fileUri) {

        if (fileUri == null)
            return null;

        File file = FileUtils.getFile(ctx, fileUri);
        Log.d("FileHelper", "File: " + file.getAbsolutePath());
        RequestBody requestFile = RequestBody.create(MediaType.parse(ctx.getContentResolver().getType(fileUri)), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public static RequestBody createPartFromString(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, filePathColumn, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);
        return path;
    }
}
