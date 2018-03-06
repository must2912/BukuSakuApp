package com.stematel.bukusaku.Model.User;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PasswordPost {

    @SerializedName("nis")
    Long nis;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("level")
    int level;

    public PasswordPost(Long nis, String email, String password, int level) {
        this.nis = nis;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public Long getNis() {
        return nis;
    }

    public void setNis(Long nis) {
        this.nis = nis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
