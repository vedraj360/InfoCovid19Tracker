
package com.vdx.infocovid19.Models.NewApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelAPI {

    @SerializedName("statewise")
    @Expose
    private ArrayList<States> state = null;

    public ArrayList<States> getState() {
        return state;
    }

    public void setState(ArrayList<States> state) {
        this.state = state;
    }


}
