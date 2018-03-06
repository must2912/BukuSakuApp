package com.stematel.bukusaku.Model.Peraturan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/20/2018.
 */

public class PagePost {
    @SerializedName("pageid")
    private int pageid;

    public PagePost(int pageid) {
        this.pageid = pageid;
    }

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }
}
