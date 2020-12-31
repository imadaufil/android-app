package com.example.mysteriousapp.data.api.model;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("url")
    private String url;

    @SerializedName("caption")
    private String caption;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("format")
    private String format;

    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getFormat() {
        return format;
    }
}
