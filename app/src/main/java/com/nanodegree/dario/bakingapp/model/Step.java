package com.nanodegree.dario.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dariomartin on 1/8/17.
 */

public class Step implements Parcelable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }


    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        public Step createFromParcel(Parcel source) {
            Step step = new Step();
            step.id = source.readInt();
            step.shortDescription = source.readString();
            step.description = source.readString();
            step.videoURL = source.readString();
            step.thumbnailURL = source.readString();
            return step;
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);
    }
}
