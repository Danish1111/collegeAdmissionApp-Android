package com.example.abc.itmcollegealigarh.activity.programs;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.itmcollegealigarh.R;
import com.example.abc.itmcollegealigarh.adapter.AdmissionListAdapter;
import com.example.abc.itmcollegealigarh.fragments.MyDialogFragment;
import com.example.abc.itmcollegealigarh.interfacee.RegisterAPI;
import com.example.abc.itmcollegealigarh.model.MyModel;
import com.example.abc.itmcollegealigarh.model.MyModelResponse;
import com.example.abc.itmcollegealigarh.view.TextViewPlus;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.google.android.gms.common.api.Api;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdmissionList extends AppCompatActivity implements View.OnClickListener {
    EditText etPassword;
    TextView tvSubmit;
    LinearLayout llRoot;
    private static String BASE_URL = "https://script.googleusercontent.com/macros/";
    private KProgressHUD kProgressHUD;
    private List<MyModel> myModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdmissionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fragment);
        etPassword = findViewById(R.id.et_password);
        llRoot = findViewById(R.id.root_layout);
        tvSubmit = findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);
        kProgressHUD = KProgressHUD.create(AdmissionList.this);
        kProgressHUD = KProgressHUD.create(AdmissionList.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AdmissionListAdapter(myModelList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_navigation:
                onBackPressed();
                break;
            case R.id.tv_submit:
                String text = String.valueOf(etPassword.getText());
                if (text == null) {
                    Snackbar snackbar1 = Snackbar.make(llRoot, "Please Enter Your Password!", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                    return;
                } else {
                    if (!text.equals("!Tm@liGaRh")) {
                        Snackbar snackbar1 = Snackbar.make(llRoot, "Invalid Password!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        return;
                    }
                    getList();
                }
                break;
        }

    }

    void getList() {
        kProgressHUD.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        //creating the api interface
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<MyModelResponse> call = api.getList();
        call.enqueue(new Callback<MyModelResponse>() {
            @Override
            public void onResponse(Call<MyModelResponse> call, Response<MyModelResponse> response) {
                MyModelResponse modelResponse = response.body();
                kProgressHUD.dismiss();
                if (modelResponse != null) {
                    if (modelResponse.getSheet1() != null) {
                        myModelList.clear();
                        myModelList.addAll(modelResponse.getSheet1());
                        mAdapter.notifyDataSetChanged();
                        tvSubmit.setVisibility(View.GONE);
                        etPassword.setVisibility(View.GONE);
                    }
                }

                //In this point we got our hero list
                //thats damn easy right ;)
//                ArrayList<MyModel> heroList = response.bo;

                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<MyModelResponse> call, Throwable t) {
                kProgressHUD.dismiss();
                Snackbar snackbar1 = Snackbar.make(llRoot, "Something went wrong", Snackbar.LENGTH_SHORT);
                snackbar1.show();
            }
        });
    }
}
