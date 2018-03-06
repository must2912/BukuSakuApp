package com.stematel.bukusaku.Model.tambahPoin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 3/4/2018.
 */

public class TambahPoinPost {
    @SerializedName("nis")
    @Expose
    private long nis;
    @SerializedName("kode_pelanggaran")
    @Expose
    private String kodePelanggaran;
    @SerializedName("poin")
    @Expose
    private int poin;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public long getNis() {
        return nis;
    }

    public void setNis(long nis) {
        this.nis = nis;
    }

    public String getKodePelanggaran() {
        return kodePelanggaran;
    }

    public void setKodePelanggaran(String kodePelanggaran) {
        this.kodePelanggaran = kodePelanggaran;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public TambahPoinPost(long nis, String kodePelanggaran, int poin, String detail, String tanggal) {
        this.nis = nis;
        this.kodePelanggaran = kodePelanggaran;
        this.poin = poin;
        this.detail = detail;
        this.tanggal = tanggal;
    }
}
