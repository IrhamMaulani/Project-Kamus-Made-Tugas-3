package com.example.user.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModel implements Parcelable {

    private int id;
    private String kalimat;
    private String arti;

    public KamusModel() {
    }

    public KamusModel(String kalimat,String arti){
        this.kalimat = kalimat;
        this.arti = arti;
    }

    public KamusModel(int id,String kalimat,String arti){
        this.id = id;
        this.kalimat = kalimat;
        this.arti = arti;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKalimat() {
        return kalimat;
    }

    public void setKalimat(String kalimat) {
        this.kalimat = kalimat;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kalimat);
        dest.writeString(this.arti);
    }


    protected KamusModel(Parcel in) {
        this.id = in.readInt();
        this.kalimat = in.readString();
        this.arti = in.readString();
    }

    public static final Parcelable.Creator<KamusModel> CREATOR = new Parcelable.Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}
