package com.yinli.shazamfeedreader.utils;

import android.util.Xml;


import com.yinli.shazamfeedreader.data.Tag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * RandomCat
 * Created by Yin Li on 13/05/15.
 * Copyright (c) 2015 Yin Li. All rights reserved.
 */
public class XMLHelper {

    private static final String ns = null;

    public List<Tag> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Tag> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Tag> entries = new ArrayList<Tag>();

        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the image tag
            if (name.equals("image")) {
                entries.add(readTag(parser));
            }
        }
        return entries;
    }


    private Tag readTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "image");
        String url = null;
        String id = null;
        String source_url = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("url")) {
                url = readUrl(parser);
            } else if (name.equals("id")) {
                id = readId(parser);
            } else if (name.equals("source_url")) {
                source_url = readSource(parser);
            }
        }
//        return new Tag(id, url, source_url);
        return null;
    }

    private String readUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "url");
        String res = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "url");
        return res;
    }

    private String readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "id");
        String res = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "id");
        return res;
    }

    private String readSource(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "source_url");
        String res = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "source_url");
        return res;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}
