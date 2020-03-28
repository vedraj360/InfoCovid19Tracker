
package com.vdx.infocovid19.Models;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("total")
    @Expose
    private Total total;
    @SerializedName("statewise")
    @Expose
    private ArrayList<Statewise> statewise = null;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
