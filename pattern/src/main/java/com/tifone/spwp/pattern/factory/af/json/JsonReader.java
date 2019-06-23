package com.tifone.spwp.pattern.factory.af.json;

import com.tifone.spwp.pattern.factory.af.DataReader;

/**
 * Create by Tifone on 2019/6/23.
 */
public class JsonReader implements DataReader {

    private static JsonReader INSTANCE;
    private JsonReader() {}

    public static JsonReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonReader();
        }
        return INSTANCE;
    }


    @Override
    public void query() {

    }
}
