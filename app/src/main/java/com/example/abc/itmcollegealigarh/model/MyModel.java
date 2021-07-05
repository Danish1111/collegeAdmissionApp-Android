package com.example.abc.itmcollegealigarh.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyModel implements Parcelable {
    private String name;
    private String mobile;
    private String email;
    private String course;
    private String stream;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.mobile);
        dest.writeString(this.email);
        dest.writeString(this.course);
        dest.writeString(this.stream);
    }

    public MyModel() {
    }

    protected MyModel(Parcel in) {
        this.name = in.readString();
        this.mobile = in.readString();
        this.email = in.readString();
        this.course = in.readString();
        this.stream = in.readString();
    }

    public static final Parcelable.Creator<MyModel> CREATOR = new Parcelable.Creator<MyModel>() {
        @Override
        public MyModel createFromParcel(Parcel source) {
            return new MyModel(source);
        }

        @Override
        public MyModel[] newArray(int size) {
            return new MyModel[size];
        }
    };
}
