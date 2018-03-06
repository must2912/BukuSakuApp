package com.stematel.bukusaku.Service;

import com.google.gson.annotations.SerializedName;
import com.stematel.bukusaku.Model.Peraturan.PasalResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class ResponseService<T> {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;
    @SerializedName("datalist")
    ArrayList<T> datalist;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ArrayList<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<T> datalist) {
        this.datalist = datalist;
    }
}

