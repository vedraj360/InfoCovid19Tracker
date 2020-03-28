
package com.vdx.infocovid19.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("lastRefreshed")
    @Expose
    private String lastRefreshed;
    @SerializedName("total")
    @Expose
    private Total total;
    @SerializedName("statewise")
    @Expose
    private ArrayList<Statewise> statewise = null;

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

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public ArrayList<Statewise> getStatewise() {
        return statewise;
    }

    public void setStatewise(ArrayList<Statewise> statewise) {
        this.statewise = statewise;
    }

}
