package com.stematel.bukusaku.Model.Upload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tofa-pc on 2/19/2018.
 */

public class FileResponse {

    @SerializedName("file")
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
