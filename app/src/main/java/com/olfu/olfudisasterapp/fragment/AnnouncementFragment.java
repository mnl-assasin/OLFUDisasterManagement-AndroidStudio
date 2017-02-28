package com.olfu.olfudisasterapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.adapter.AnnouncementAdapter;
import com.olfu.olfudisasterapp.api.ApiClient;
import com.olfu.olfudisasterapp.api.ApiInterface;
import com.olfu.olfudisasterapp.api.ResponseAnnouncement;
import com.olfu.olfudisasterapp.data.EZSharedPreferences;
import com.olfu.olfudisasterapp.model.AnnouncementItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends BaseFragment {

    private final String TAG = AnnouncementFragment.class.getSimpleName();
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    List<AnnouncementItem> items;
    AnnouncementAdapter adapter;

    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_announcement, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void initData() {
        requestAnnouncement();
    }

    private void requestAnnouncement() {
        startProgressDialog("Checking for new Announcement");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ResponseAnnouncement>> call = api.getAnnouncement();
        call.enqueue(callback);
    }

    Callback<List<ResponseAnnouncement>> callback = new Callback<List<ResponseAnnouncement>>() {
        @Override
        public void onResponse(Call<List<ResponseAnnouncement>> call, Response<List<ResponseAnnouncement>> response) {
            stopProgressDialog();

            if (response.isSuccessful()) {
                List<ResponseAnnouncement> list = response.body();
                populateAnnouncement(list);
                Log.d(TAG, "Announcement Successful");
            } else
                Log.d(TAG, "Announcement Fail");
        }

        @Override
        public void onFailure(Call<List<ResponseAnnouncement>> call, Throwable t) {
            stopProgressDialog();
            Log.d(TAG, "onFailure Announcement");
            startProgressDialog("Checking for new Announcement");
            call.enqueue(callback);
        }
    };

    private void populateAnnouncement(List<ResponseAnnouncement> list) {

        items = new ArrayList<>();


        int scope = EZSharedPreferences.getIntValue(getActivity(), EZSharedPreferences.KEY_COURSE);
        for (int ctr = 0; ctr < list.size(); ctr++) {
            ResponseAnnouncement item = list.get(ctr);
            int listScope = Integer.parseInt(item.getScope());

            if (listScope == 0 || scope == listScope)
                items.add(0, new AnnouncementItem(item.getTitle(), item.getContent(),
                        item.getTimeStamp(), item.getImageUrl()));
        }

        Log.d(TAG, "Size: " + items.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new AnnouncementAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
