package com.example.abc.itmcollegealigarh.activity.core;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.abc.itmcollegealigarh.activity.infrastucture.InfrastructureActivity;
import com.example.abc.itmcollegealigarh.activity.aboutinstitution.AboutActivity;
import com.example.abc.itmcollegealigarh.activity.map.MapActivity;
import com.example.abc.itmcollegealigarh.activity.placements.PlacementDetailActivity;
import com.example.abc.itmcollegealigarh.activity.programs.AdmissionActivity;
import com.example.abc.itmcollegealigarh.activity.programs.ProgramActivity;
import com.example.abc.itmcollegealigarh.adapter.CustomAdapter;
import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.viewpager.ViewPagerCustomDuration;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPagerCustomDuration viewPager;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 4000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 7000; // time in milliseconds between successive task executions.
    public static final int NUM_PAGES = 4;
    private LinearLayout llAboutInstitution, llPrograms, llInfrastucture, llPlacements, llAdmission, llLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer[] imageId = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.vision};
        llAboutInstitution = (LinearLayout) findViewById(R.id.ll_about_institution);
        llInfrastucture = (LinearLayout) findViewById(R.id.ll_infrastucture);
        llPlacements = (LinearLayout) findViewById(R.id.ll_placements);
        llPlacements.setOnClickListener(this);
        llInfrastucture.setOnClickListener(this);
        llPrograms = (LinearLayout) findViewById(R.id.ll_programs);
        llPrograms.setOnClickListener(this);
        llAboutInstitution.setOnClickListener(this);
        llAdmission = (LinearLayout) findViewById(R.id.ll_admission);
        llAdmission.setOnClickListener(this);
        llLocation = (LinearLayout) findViewById(R.id.ll_location);
        llLocation.setOnClickListener(this);
        viewPager = (ViewPagerCustomDuration) findViewById(R.id.viewPager);
        PagerAdapter adapter = new CustomAdapter(MainActivity.this, imageId);
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler(MainActivity.this.getMainLooper());
        final Runnable Update = new Runnable() {

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_about_institution:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_programs:
                Intent intentPrograms = new Intent(MainActivity.this, ProgramActivity.class);
                startActivity(intentPrograms);
                break;
            case R.id.ll_infrastucture:
                Intent intentInfrastructure = new Intent(MainActivity.this, InfrastructureActivity.class);
                startActivity(intentInfrastructure);
                break;
            case R.id.ll_placements:
                Intent intentPlacement = new Intent(MainActivity.this, PlacementDetailActivity.class);
                startActivity(intentPlacement);
                break;

            case R.id.ll_admission:
                Intent intentadmission = new Intent(MainActivity.this, AdmissionActivity.class);
                startActivity(intentadmission);
                break;
            case R.id.ll_location:
                Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(mapIntent);
                break;

        }

    }
}
