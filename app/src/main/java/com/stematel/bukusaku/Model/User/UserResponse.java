package com.stematel.bukusaku.Model.User;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class UserResponse implements Parcelable {

    public long getNis() {
        return nis;
    }

    public void setNis(long nis) {
        this.nis = nis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    @SerializedName("nis")
    private long nis;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("email")
    private String email;
    @SerializedName("jurusan")
    private String jurusan;
    @SerializedName("angkatan")
    private String angkatan;
    @SerializedName("poin")
    private int poin;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.nis);
        dest.writeString(this.nama);
        dest.writeString(this.kelas);
        dest.writeString(this.email);
        dest.writeString(this.jurusan);
        dest.writeString(this.angkatan);
        dest.writeInt(this.poin);
        dest.writeInt(this.status);
    }

    public UserResponse() {
    }

    protected UserResponse(Parcel in) {
        this.nis = in.readLong();
        this.nama = in.readString();
        this.kelas = in.readString();
        this.jurusan = in.readString();
        this.angkatan = in.readString();
        this.poin = in.readInt();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<UserResponse> CREATOR = new Parcelable.Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel source) {
            return new UserResponse(source);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };
}

