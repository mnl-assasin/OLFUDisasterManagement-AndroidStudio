package com.olfu.olfudisasterapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;

import java.util.List;

/**
 * Created by mykelneds on 25/02/2017.
 */

public class SafeZoneAdapter extends ArrayAdapter<String> {

    Context ctx;
    int layoutResourceId;
    List<String> list;

    public SafeZoneAdapter(Context ctx, int layoutResourceId, List<String> list) {
        super(ctx, layoutResourceId, list);
        this.ctx = ctx;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        LinearLayout container = (LinearLayout) convertView.findViewById(R.id.container);
        TextView tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        ImageView ivIndicator = (ImageView) convertView.findViewById(R.id.ivIndicator);

        tvItem.setText(list.get(position));

        return convertView;
    }
}
