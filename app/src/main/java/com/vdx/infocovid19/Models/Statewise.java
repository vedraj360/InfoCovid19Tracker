
package com.vdx.infocovid19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Statewise {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("confirmed")
    @Expose
    private long confirmed;
    @SerializedName("recovered")
    @Expose
    private long recovered;
    @SerializedName("deaths")
    @Expose
    private long deaths;
    @SerializedName("active")
    @Expose
    private long active;

    private boolean expanded = false;

    private long diff;

    public long getDiff() {
        return diff;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

}
