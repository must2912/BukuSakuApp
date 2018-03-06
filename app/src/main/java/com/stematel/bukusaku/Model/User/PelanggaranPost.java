package com.stematel.bukusaku.Model.User;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PelanggaranPost {
    @SerializedName("nis")
    private long nis;

    public long getNis() {
        return nis;
    }

    public void setNis(long nis) {
        this.nis = nis;
    }

    public PelanggaranPost(long nis) {
        this.nis = nis;
    }


}
