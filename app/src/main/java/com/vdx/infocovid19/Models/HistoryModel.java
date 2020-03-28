
package com.vdx.infocovid19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private HistoryData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HistoryData getData() {
        return data;
    }

    public void setData(HistoryData data) {
        this.data = data;
    }

}
