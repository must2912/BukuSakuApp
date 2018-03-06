package com.stematel.bukusaku.Model.User;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class SigninPost {

    @SerializedName("nis")
    private long nis;
    @SerializedName("password")
    private String password;
    @SerializedName("level")
    private int level;

    public SigninPost(long nis, String password, int level) {
        this.nis = nis;
        this.password = password;
        this.level = level;
    }

    public long getNis() {
        return nis;
    }

    public void setNis(long nis) {
        this.nis = nis;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
