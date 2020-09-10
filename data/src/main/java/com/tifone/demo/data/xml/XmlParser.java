package com.tifone.demo.data.xml;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlParser {

    private static XmlParser INSTANCE;
    private XmlParser() {}

    public static XmlParser getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlParser();
        }
        return INSTANCE;
    }

}
