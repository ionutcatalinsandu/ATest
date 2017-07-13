package com.example.torridas.atest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Torridas on 13-Jul-17.
 */

 class ResultCollection {

    @SerializedName("items")
    private List<Result> resultList;

    public ResultCollection() {
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<Result> getResultList() {
        return resultList;
    }
}
