package com.stematel.bukusaku.Model.tambahPoin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 3/4/2018.
 */

public class VerifPost {
    @SerializedName("kode")
    String kode;

    public VerifPost(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
