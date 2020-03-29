
package com.vdx.infocovid19.Models.NewApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyValue {

    @SerializedName("confirmeddelta")
    @Expose
    private String confirmeddelta;
    @SerializedName("counterforautotimeupdate")
    @Expose
    private String counterforautotimeupdate;
    @SerializedName("deceaseddelta")
    @Expose
    private String deceaseddelta;
    @SerializedName("lastupdatedtime")
    @Expose
    private String lastupdatedtime;
    @SerializedName("recovereddelta")
    @Expose
    private String recovereddelta;
    @SerializedName("statesdelta")
    @Expose
    private String statesdelta;

    public String getConfirmeddelta() {
        return confirmeddelta;
    }

    public void setConfirmeddelta(String confirmeddelta) {
        this.confirmeddelta = confirmeddelta;
    }

    public String getCounterforautotimeupdate() {
        return counterforautotimeupdate;
    }

    public void setCounterforautotimeupdate(String counterforautotimeupdate) {
        this.counterforautotimeupdate = counterforautotimeupdate;
    }

    public String getDeceaseddelta() {
        return deceaseddelta;
    }

    public void setDeceaseddelta(String deceaseddelta) {
        this.deceaseddelta = deceaseddelta;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getRecovereddelta() {
        return recovereddelta;
    }

    public void setRecovereddelta(String recovereddelta) {
        this.recovereddelta = recovereddelta;
    }

    public String getStatesdelta() {
        return statesdelta;
    }

    public void setStatesdelta(String statesdelta) {
        this.statesdelta = statesdelta;
    }

}
