package com.example.abc.itmcollegealigarh.activity.placements;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.abc.itmcollegealigarh.R;

public class PlacementDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_detail_activity);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        ivNavigation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
        }
    }
}
