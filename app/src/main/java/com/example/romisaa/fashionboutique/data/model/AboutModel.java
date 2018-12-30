package com.example.romisaa.fashionboutique.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AboutModel implements Parcelable {

    String description;
    String page;
    String number;

    public AboutModel() {
    }

    public AboutModel(String description, String page, String number) {
        this.description = description;
        this.page = page;
        this.number = number;
    }

    protected AboutModel(Parcel in) {
        description = in.readString();
        page = in.readString();
        number = in.readString();
    }

    public static final Creator<AboutModel> CREATOR = new Creator<AboutModel>() {
        @Override
        public AboutModel createFromParcel(Parcel in) {
            return new AboutModel(in);
        }

        @Override
        public AboutModel[] newArray(int size) {
            return new AboutModel[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(page);
        dest.writeString(number);
    }
}
