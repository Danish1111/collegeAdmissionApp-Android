package com.example.abc.itmcollegealigarh.activity.programs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.view.TextViewPlus;

public class ProgramBtechActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation;
    private TextViewPlus tvReadMoreBtech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_btech_activity);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        ivNavigation.setOnClickListener(this);
        tvReadMoreBtech = (TextViewPlus) findViewById(R.id.tv_read_more_btech);
        tvReadMoreBtech.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
            case R.id.tv_read_more_btech:
                Intent intent = new Intent(ProgramBtechActivity.this,BtechDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
