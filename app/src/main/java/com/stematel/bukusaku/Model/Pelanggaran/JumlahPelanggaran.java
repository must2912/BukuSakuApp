package com.stematel.bukusaku.Model.Pelanggaran;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/18/2018.
 */

public class JumlahPelanggaran {
    @SerializedName("pelanggaran")
    private int pelanggaran;
    @SerializedName("penghargaan")
    private int penghargaan;

    public JumlahPelanggaran(int pelanggaran, int penghargaan) {
        this.pelanggaran = pelanggaran;
        this.penghargaan = penghargaan;
    }

    public int getPelanggaran() {
        return pelanggaran;
    }

    public void setPelanggaran(int pelanggaran) {
        this.pelanggaran = pelanggaran;
    }

    public int getPenghargaan() {
        return penghargaan;
    }

    public void setPenghargaan(int penghargaan) {
        this.penghargaan = penghargaan;
    }
}
