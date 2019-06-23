package com.tifone.spwp.pattern.factory.fm;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DatabaseManager implements DataManager {
    private static DatabaseManager INSTANCE;
    private DatabaseManager() {}

    public static DatabaseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseManager();
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
