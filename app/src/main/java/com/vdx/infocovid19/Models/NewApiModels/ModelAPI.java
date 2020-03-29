
package com.vdx.infocovid19.Models.NewApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelAPI {
    @SerializedName("key_values")
    @Expose
    private ArrayList<KeyValue> keyValues = null;
    @SerializedName("statewise")
    @Expose
    private ArrayList<States> state = null;


    public ArrayList<KeyValue> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(ArrayList<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }

    public ArrayList<States> getState() {
        return state;
    }

    public void setState(ArrayList<States> state) {
        this.state = state;
    }


}
