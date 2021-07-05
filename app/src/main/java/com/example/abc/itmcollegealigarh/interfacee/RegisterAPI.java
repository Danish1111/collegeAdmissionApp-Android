package com.example.abc.itmcollegealigarh.interfacee;

import com.example.abc.itmcollegealigarh.model.MyModel;
import com.example.abc.itmcollegealigarh.model.MyModelResponse;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterAPI {
    @POST("AKfycbzqTnbhZYQSZLdbG5MjU-cTe1P2zH19LiOKYVMUNg7SZvONRUI/exec")
    public Call<ResponseBody> insertUser(
            @Query("name") String name,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("course") String course,
            @Query("stream") String stream,
            @Query("id") String id);

    @GET("echo?user_content_key=zemR-Erb-i6d1CFj_Z7RimwgE5qShvkR26v4FpTf3UnGuvcQac27ZNycKNnUGqTwML5X19gGsLvMNdIueOr30doPAQ93phF4OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUCvmTBJgtwcqi8xZ4sd2GdZlkCWYh_qlE8HAMZxrzes4ayBw4-EWseSxMeJ3sm2er5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva")
    Call<MyModelResponse> getList();

}
