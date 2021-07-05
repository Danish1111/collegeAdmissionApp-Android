package com.example.abc.itmcollegealigarh.activity.infrastucture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.activity.aboutinstitution.SecretaryActivity;


public class InfrastructureActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation;
    private LinearLayout llLectureTheater, llLibrary, llDigitalResource, llCafeteria, llAuditorium, llGym, llTransport, llSports, llHostel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infrastructure_activity);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        llLectureTheater = (LinearLayout) findViewById(R.id.ll_lecture_theater);
        llLibrary = (LinearLayout) findViewById(R.id.ll_library);
        llDigitalResource = (LinearLayout) findViewById(R.id.ll_digital_resource);
        llCafeteria = (LinearLayout) findViewById(R.id.ll_cafeteria);
        llAuditorium = (LinearLayout) findViewById(R.id.ll_auditorium);
//        llGym = (LinearLayout) findViewById(R.id.ll_gym);
        llSports = (LinearLayout) findViewById(R.id.ll_sports);
        llTransport = (LinearLayout) findViewById(R.id.ll_transport);
        llHostel = (LinearLayout) findViewById(R.id.ll_hostel);

        llLectureTheater.setOnClickListener(this);
        llLibrary.setOnClickListener(this);
        llDigitalResource.setOnClickListener(this);
        llCafeteria.setOnClickListener(this);
        llAuditorium.setOnClickListener(this);
//        llGym.setOnClickListener(this);
        llSports.setOnClickListener(this);
        llTransport.setOnClickListener(this);
        llHostel.setOnClickListener(this);
        ivNavigation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
            case R.id.ll_lecture_theater:
                Intent intentLectureTheater = new Intent(InfrastructureActivity.this, LectureTheaterActivity.class);
                startActivity(intentLectureTheater);
                break;
            case R.id.ll_library:
                Intent intentLibrary = new Intent(InfrastructureActivity.this, LibraryActivity.class);
                startActivity(intentLibrary);
                break;
            case R.id.ll_digital_resource:
                Intent intentDigitalResource = new Intent(InfrastructureActivity.this, DigitalResourceActvity.class);
                startActivity(intentDigitalResource);

                break;
            case R.id.ll_cafeteria:
                Intent intentCafeteria = new Intent(InfrastructureActivity.this, CafeteriaActivity.class);
                startActivity(intentCafeteria);
                break;
            case R.id.ll_auditorium:
                Intent intentAuditorium = new Intent(InfrastructureActivity.this, AuditoriumActivity.class);
                startActivity(intentAuditorium);
                break;
//            case R.id.ll_gym:
//                Intent intentGym = new Intent(InfrastructureActivity.this, GymActivity.class);
//                startActivity(intentGym);
//                break;
            case R.id.ll_transport:
                Intent intentTransport = new Intent(InfrastructureActivity.this, TransportActivity.class);
                startActivity(intentTransport);
                break;
            case R.id.ll_sports:
                Intent intentsports = new Intent(InfrastructureActivity.this, SportsActvity.class);
                startActivity(intentsports);
                break;
            case R.id.ll_hostel:
                Intent intentHostel = new Intent(InfrastructureActivity.this, HostleActivity.class);
                startActivity(intentHostel);
                break;

        }
    }
}
