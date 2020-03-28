
package com.vdx.infocovid19.Models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryData {

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("lastRefreshed")
    @Expose
    private String lastRefreshed;
    @SerializedName("history")
    @Expose
    private ArrayList<History> history = null;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

}
