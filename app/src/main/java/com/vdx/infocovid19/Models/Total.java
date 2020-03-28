
package com.vdx.infocovid19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total {

    @SerializedName("confirmed")
    @Expose
    private Long confirmed;
    @SerializedName("recovered")
    @Expose
    private Long recovered;
    @SerializedName("deaths")
    @Expose
    private Long deaths;
    @SerializedName("active")
    @Expose
    private Long active;

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

}
