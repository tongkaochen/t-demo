package com.tifone.spwp.pattern.factory.af.database;

import com.tifone.spwp.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DatabaseWriter implements DataWriter {

    private static DatabaseWriter INSTANCE;
    private DatabaseWriter() {}

    public static DatabaseWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseWriter();
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
