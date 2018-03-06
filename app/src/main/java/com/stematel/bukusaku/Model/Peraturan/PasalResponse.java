package com.stematel.bukusaku.Model.Peraturan;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PasalResponse implements Serializable {
    @SerializedName("no")
    private String no;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("kode")
    private String kode;
    @SerializedName("poin")
    private String poin;
    @SerializedName("keterangan")
    private String keterangan;

    public PasalResponse(){}

    public PasalResponse(String no, String jenis, String kategori, String kode, String poin, String keterangan) {
        this.no = no;
        this.jenis = jenis;
        this.kategori = kategori;
        this.kode = kode;
        this.poin = poin;
        this.keterangan = keterangan;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
