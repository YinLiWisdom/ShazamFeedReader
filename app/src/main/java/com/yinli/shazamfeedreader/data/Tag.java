package com.yinli.shazamfeedreader.data;

import org.parceler.Parcel;

/**
 * ShazamFeedReader
 * Created by Yin Li on 14/06/15.
 * Copyright (c) 2015 Yin Li. All rights reserved.
 */
@Parcel
public class Tag {

    private String title;
    private String artist;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
