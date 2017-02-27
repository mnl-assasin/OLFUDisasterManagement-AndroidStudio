package com.olfu.olfudisasterapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mykelneds on 10/02/2017.
 */

public class ACTAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> list;
    List<String> listAll;
    List<String> listSuggestion;

    int layoutResourceId;

    public ACTAdapter(Context context, int layoutResourceId, List<String> list) {
        super(context, layoutResourceId, list);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = new ArrayList<>(list);
        listAll = new ArrayList<>(list);
        listSuggestion = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public String getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        String string = getItem(position);
        TextView tv = (TextView) convertView.findViewById(R.id.tvItem);
        tv.setText(string);

        return super.getView(position, convertView, parent);
    }
}
