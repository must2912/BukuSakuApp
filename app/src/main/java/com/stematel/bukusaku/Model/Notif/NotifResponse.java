package com.stematel.bukusaku.Model.Notif;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/20/2018.
 */

public class NotifResponse {

    /**
     * tanggal : 2017-05-02
     * aksi : poin ini 1-40 k12
     * tpoin : 13
     * timdis : unggulzb
     * status : 1
     */

    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("aksi")
    private String aksi;
    @SerializedName("tpoin")
    private int tpoin;
    @SerializedName("timdis")
    private String timdis;
    @SerializedName("status")
    private String status;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAksi() {
        return aksi;
    }

    public void setAksi(String aksi) {
        this.aksi = aksi;
    }

    public int getTpoin() {
        return tpoin;
    }

    public void setTpoin(int tpoin) {
        this.tpoin = tpoin;
    }

    public String getTimdis() {
        return timdis;
    }

    public void setTimdis(String timdis) {
        this.timdis = timdis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
