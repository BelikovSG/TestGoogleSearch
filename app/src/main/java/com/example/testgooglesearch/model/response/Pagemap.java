package com.example.testgooglesearch.model.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagemap {
    @SerializedName("website")
    @Expose
    private List<Website> website = null;

    public List<Website> getWebsite() {
        return website;
    }

    public void setWebsite(List<Website> website) {
        this.website = website;
    }

}