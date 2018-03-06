package com.stematel.bukusaku.Model.Pelanggaran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tofa-pc on 2/4/2018.
 */

public class PelanggaranResponse implements Serializable{
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("pelanggaran")
    @Expose
    private String pelanggaran;
    @SerializedName("poin")
    @Expose
    private String poin;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("timdis")
    @Expose
    private String timdis;

    public PelanggaranResponse(String kode, String jenis, String kategori, String pelanggaran, String poin, String tanggal, String timdis) {
        this.kode = kode;
        this.jenis = jenis;
        this.kategori = kategori;
        this.pelanggaran = pelanggaran;
        this.poin = poin;
        this.tanggal = tanggal;
        this.timdis = timdis;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getPelanggaran() {
        return pelanggaran;
    }

    public void setPelanggaran(String pelanggaran) {
        this.pelanggaran = pelanggaran;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTimdis() {
        return timdis;
    }

    public void setTimdis(String timdis) {
        this.timdis = timdis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
