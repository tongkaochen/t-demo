package com.tifone.demo.pattern.factory.af.database;

import com.tifone.demo.pattern.factory.af.DataReader;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DatabaseReader implements DataReader {
    private static DatabaseReader INSTANCE;
    private DatabaseReader() {}

    public static DatabaseReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseReader();
        }
        return INSTANCE;
    }

    @Override
    public void query() {

    }
}
