package com.tifone.spwp.pattern.factory.af.xml;

import com.tifone.spwp.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlWriter implements DataWriter {

    private static XmlWriter INSTANCE;
    private XmlWriter() {}

    public static XmlWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlWriter();
        }
        return INSTANCE;
    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void modify() {

    }
}
