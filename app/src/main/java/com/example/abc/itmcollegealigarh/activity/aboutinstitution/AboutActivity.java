package com.example.abc.itmcollegealigarh.activity.aboutinstitution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.abc.itmcollegealigarh.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation;
    private LinearLayout llTheCampus, llOurMission, llChairmanMessage, llSecrateryMessage, lltreasurerMessage, llDirectorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity_layout);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        llOurMission = (LinearLayout) findViewById(R.id.ll_our_mission);
        llChairmanMessage = (LinearLayout) findViewById(R.id.ll_chairman_message);
        llSecrateryMessage = (LinearLayout) findViewById(R.id.ll_secretary_message);
        lltreasurerMessage = (LinearLayout) findViewById(R.id.ll_treasurer_message);
        llDirectorMessage = (LinearLayout) findViewById(R.id.ll_director_message);
        llTheCampus = (LinearLayout) findViewById(R.id.ll_the_campus);
        llTheCampus.setOnClickListener(this);
        llDirectorMessage.setOnClickListener(this);
        lltreasurerMessage.setOnClickListener(this);
        llSecrateryMessage.setOnClickListener(this);
        llChairmanMessage.setOnClickListener(this);
        llOurMission.setOnClickListener(this);
        ivNavigation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;

            case R.id.ll_the_campus:
                Intent intentTheCampus = new Intent(AboutActivity.this, TheCampusActivity.class);
                startActivity(intentTheCampus);
                break;
            case R.id.ll_our_mission:
                Intent intentOurVision = new Intent(AboutActivity.this, OurVisionMissionActivity.class);
                startActivity(intentOurVision);
                break;
            case R.id.ll_chairman_message:
                Intent intentChairmanMessage = new Intent(AboutActivity.this, ChairmanMessageActivity.class);
                startActivity(intentChairmanMessage);

                break;
            case R.id.ll_secretary_message:
                Intent intentSecreteryMessage = new Intent(AboutActivity.this, SecretaryActivity.class);
                startActivity(intentSecreteryMessage);
                break;
            case R.id.ll_treasurer_message:
                Intent intentTreasurerMessage = new Intent(AboutActivity.this, TreasurerMessageActivity.class);
                startActivity(intentTreasurerMessage);
                break;
            case R.id.ll_director_message:
                Intent directorMessage = new Intent(AboutActivity.this, DirectorMessageActivity.class);
                startActivity(directorMessage);
                break;

        }
    }
}
