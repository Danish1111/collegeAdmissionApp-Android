package com.example.abc.itmcollegealigarh.activity.programs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.abc.itmcollegealigarh.R;

public class ProgramActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation;
    private LinearLayout llBtechMtech, llMba, llPolyTech, llSecrateryMessage, lltreasurerMessage, llDirectorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_activity_layout);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        llBtechMtech = (LinearLayout) findViewById(R.id.ll_btech_mtech);
        llMba = (LinearLayout) findViewById(R.id.ll_mba);
        llPolyTech = (LinearLayout) findViewById(R.id.ll_polytech);
        llPolyTech.setOnClickListener(this);
        llMba.setOnClickListener(this);
        llBtechMtech.setOnClickListener(this);
        ivNavigation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
            case R.id.ll_btech_mtech:
                Intent intentBtech = new Intent(ProgramActivity.this, ProgramBtechActivity.class);
                startActivity(intentBtech);
                break;
            case R.id.ll_mba:
                Intent intentMba = new Intent(ProgramActivity.this, MbaDetailActivity.class);
                startActivity(intentMba);
                break;
            case R.id.ll_polytech:
                Intent intentPolytechnic = new Intent(ProgramActivity.this, PolytechnicDetailActivity.class);
                startActivity(intentPolytechnic);
                break;
        }
    }
}
