package com.tifone.spwp.pattern.factory.af.json;

import com.tifone.spwp.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class JsonWriter implements DataWriter {

    private static JsonWriter INSTANCE;
    private JsonWriter() {}

    public static JsonWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonWriter();
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
