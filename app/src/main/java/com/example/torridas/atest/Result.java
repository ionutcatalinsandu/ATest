package com.example.torridas.atest;

import android.database.CursorJoiner;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Torridas on 11-Jul-17.
 */

 class Result {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public Result() {
    }

    public Result(long id, String name, String url) {

        this.id = id;
        this.name = name;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
