package com.example.abc.itmcollegealigarh.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MyModelResponse implements Parcelable {
  private ArrayList<MyModel> Sheet1;

    public ArrayList<MyModel> getSheet1() {
        return Sheet1;
    }

    public void setSheet1(ArrayList<MyModel> sheet1) {
        Sheet1 = sheet1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.Sheet1);
    }

    public MyModelResponse() {
    }

    protected MyModelResponse(Parcel in) {
        this.Sheet1 = in.createTypedArrayList(MyModel.CREATOR);
    }

    public static final Creator<MyModelResponse> CREATOR = new Creator<MyModelResponse>() {
        @Override
        public MyModelResponse createFromParcel(Parcel source) {
            return new MyModelResponse(source);
        }

        @Override
        public MyModelResponse[] newArray(int size) {
            return new MyModelResponse[size];
        }
    };
}
