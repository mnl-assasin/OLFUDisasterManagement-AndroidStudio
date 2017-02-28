package com.olfu.olfudisasterapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.model.AnnouncementItem;
import com.olfu.olfudisasterapp.util.TimeHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mleano on 2/28/2017.
 */

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private Context ctx;
    private List<AnnouncementItem> items;

    public AnnouncementAdapter(Context ctx, List<AnnouncementItem> items) {
        this.ctx = ctx;
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView container;
        TextView tvTitle;
        ImageView ivImage;
        TextView tvContent;
        TextView tvTimeStamp;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.container);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvTimeStamp);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnnouncementItem item = items.get(position);

        holder.tvTitle.setText(item.getTitle());

        String imageUrl = item.getImageUrl();
        if (!imageUrl.equals(""))
            Picasso.with(ctx).load(imageUrl).into(holder.ivImage);

        holder.tvContent.setText(item.getContent());

        String timeStamp = item.getTimeStamp();
        Log.d("Announcement", "TimeStamp: " + timeStamp);
        holder.tvTimeStamp.setText(TimeHelper.getTimeAgo(TimeHelper.getTimeMillis(timeStamp)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
