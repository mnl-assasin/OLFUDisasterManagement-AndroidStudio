package com.olfu.olfudisasterapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mleano on 2/28/2017.
 */

public class ResponseAnnouncement {

    @SerializedName("title")
    String title;

    @SerializedName("content")
    String content;

    @SerializedName("updated_at")
    String timeStamp;

    @SerializedName("scope")
    String scope;

    @SerializedName("image_full_url")
    String imageUrl;

    public ResponseAnnouncement(String title, String content, String timeStamp, String scope, String imageUrl) {
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
        this.scope = scope;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getScope() {
        return scope;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
