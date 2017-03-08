package com.olfu.olfudisasterapp.model;

/**
 * Created by mleano on 2/28/2017.
 */

public class AnnouncementItem {

    String title;
    String content;
    String timeStamp;
    String imageUrl;

    public AnnouncementItem(String title, String content, String timeStamp, String imageUrl) {
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
