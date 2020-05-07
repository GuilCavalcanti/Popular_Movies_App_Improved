package com.guil.popularmoviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailers implements Parcelable {

    private String key;
    private String name;
    private String type;
    private String site;

    public Trailers(String key, String name, String type, String site) {
        this.key = key;
        this.name = name;
        this.type = type;
        this.site = site;
    }

    public Trailers() {
    }

    protected Trailers(Parcel in) {
        key = in.readString();
        name = in.readString();
        type = in.readString();
        site = in.readString();
    }

    public static final Creator<Trailers> CREATOR = new Creator<Trailers>() {
        @Override
        public Trailers createFromParcel(Parcel in) {
            return new Trailers(in);
        }

        @Override
        public Trailers[] newArray(int size) {
            return new Trailers[size];
        }
    };

    public String getWatchKey() {
        return key;
    }

    public void setWatchKey(String watchKey) {
        this.key = watchKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Trailers{" +
                "watchKey='" + key + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(site);
    }
}