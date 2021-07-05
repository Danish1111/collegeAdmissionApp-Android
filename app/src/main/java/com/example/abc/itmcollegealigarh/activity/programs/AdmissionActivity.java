package com.example.abc.itmcollegealigarh.activity.programs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.interfacee.RegisterAPI;
import com.example.abc.itmcollegealigarh.view.TextViewPlus;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdmissionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivNavigation, ivMore;
    private TextViewPlus tvReadMoreBtech;
    private EditText etName, etMobile, etEmail, etCourse, etStream;
    private TextView tvSubmit;
    public static final String BASE_URL = "https://script.google.com/macros/s/";
    private static Retrofit retrofit = null;
    private final String id = "1FDefu9XbEg8vGE0DMdMMCmEaufH22cPxvSGeVYyin6k";
    private KProgressHUD kProgressHUD;
    LinearLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admission_activity);
        etName = findViewById(R.id.et_name);
        tvSubmit = findViewById(R.id.tv_submit);
        etMobile = findViewById(R.id.et_mobile_no);
        etEmail = findViewById(R.id.et_email);
        etCourse = findViewById(R.id.et_course);
        etStream = findViewById(R.id.et_stream);
        ivNavigation = (ImageView) findViewById(R.id.iv_navigation);
        ivMore = (ImageView) findViewById(R.id.iv_more);
        ivMore.setOnClickListener(this);
        ivNavigation.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        rootLayout = findViewById(R.id.root_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        kProgressHUD = KProgressHUD.create(AdmissionActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();


        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
            case R.id.tv_submit:
                submit();
                break;
            case R.id.iv_more:
                Intent intent = new Intent(AdmissionActivity.this,AdmissionList.class);
                startActivity(intent);
                break;
        }


    }

    private void submit() {
        String name = String.valueOf(etName.getText());
        String mobile = String.valueOf(etMobile.getText());

//                int mobile = Integer.parseInt(String.valueOf(etMobile.getText()));
        String email = String.valueOf(etEmail.getText());
        String course = String.valueOf(etCourse.getText());
        String stream = String.valueOf(etStream.getText());

        if (name != null && name.equals("")) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Your Name!", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }
        if (mobile == null) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Valid Mobile Number !", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }

        if (mobile.length() != 10) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Valid Mobile Number !", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }

        if (email.equals("")) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Your Email!", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }

        if (course.equals("")) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Your course!", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }
        if (stream.equals("")) {
            Snackbar snackbar1 = Snackbar.make(rootLayout, "Please Enter Your Stream!", Snackbar.LENGTH_SHORT);
            snackbar1.show();
            return;
        }
        kProgressHUD.show();
        RegisterAPI service = retrofit.create(RegisterAPI.class);
        Call<ResponseBody> call = service.insertUser(name, mobile + "", email, course, stream, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                kProgressHUD.dismiss();
                new iOSDialogBuilder(AdmissionActivity.this)
                        .setTitle("Success")
                        .setSubtitle("Successfully post your query")
                        .setBoldPositiveLabel(true)
                        .setCancelable(false)
                        .setPositiveListener(getString(R.string.ok), new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                dialog.dismiss();
                                finish();

                            }
                        })
                        .build().show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                kProgressHUD.dismiss();
                new iOSDialogBuilder(AdmissionActivity.this)
                        .setTitle("Error !")
                        .setSubtitle("Something went wrong ! May be your internet connection is not available")
                        .setBoldPositiveLabel(true)
                        .setCancelable(false)
                        .setPositiveListener(getString(R.string.ok), new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                dialog.dismiss();

                            }
                        })
                        .build().show();
            }
        });
    }
}
