package com.tifone.spwp.pattern.factory.af.xml;

import com.tifone.spwp.pattern.factory.af.DataReader;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlReader implements DataReader {

    private static XmlReader INSTANCE;
    private XmlReader() {}

    public static XmlReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlReader();
        }
        return INSTANCE;
    }

    @Override
    public void query() {

    }
}
