
package com.vdx.infocovid19.Models.NewApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class States {

    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("confirmed")
    @Expose
    private String confirmed;
    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("lastupdatedtime")
    @Expose
    private String lastupdatedtime;
    @SerializedName("recovered")
    @Expose
    private String recovered;
    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("deltaconfirmed")
    @Expose
    private String deltaconfirmed;

    @SerializedName("deltadeaths")
    @Expose
    private String deltadeaths;

    @SerializedName("deltarecovered")
    @Expose
    private String deltarecovered;

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(String deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public void setDeltadeaths(String deltadeaths) {
        this.deltadeaths = deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public void setDeltarecovered(String deltarecovered) {
        this.deltarecovered = deltarecovered;
    }

    private boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
