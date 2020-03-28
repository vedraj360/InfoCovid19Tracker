
package com.vdx.infocovid19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("lastRefreshed")
    @Expose
    private String lastRefreshed;
    @SerializedName("lastOriginUpdate")
    @Expose
    private String lastOriginUpdate;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String getLastOriginUpdate() {
        return lastOriginUpdate;
    }

    public void setLastOriginUpdate(String lastOriginUpdate) {
        this.lastOriginUpdate = lastOriginUpdate;
    }

}
