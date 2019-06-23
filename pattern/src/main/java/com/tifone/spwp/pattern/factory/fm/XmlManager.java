package com.tifone.spwp.pattern.factory.fm;

import com.tifone.spwp.pattern.factory.af.DataReader;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlManager implements DataManager {

    private static XmlManager INSTANCE;
    private XmlManager() {}

    public static XmlManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlManager();
        }
        return INSTANCE;
    }

    @Override
    public void query() {

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
