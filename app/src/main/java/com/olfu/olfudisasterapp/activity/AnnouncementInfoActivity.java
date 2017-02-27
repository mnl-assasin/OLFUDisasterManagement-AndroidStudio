package com.olfu.olfudisasterapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olfu.olfudisasterapp.R;
import com.olfu.olfudisasterapp.data.Const;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnnouncementInfoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvMessage)
    TextView tvMessage;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.activity_announcement_info)
    LinearLayout activityAnnouncementInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_info);
        ButterKnife.bind(this);

        initData();
    }


    private void initData() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String title = extras.getString(Const.ANN_TTILE);
            String message = extras.getString(Const.ANN_MESSAGE);
            String date = extras.getString(Const.ANN_DATE);

            setTitle(title);
            tvMessage.setText(message);
            tvDate.setText(date);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
