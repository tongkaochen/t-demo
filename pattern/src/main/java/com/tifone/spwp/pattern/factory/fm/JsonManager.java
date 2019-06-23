package com.tifone.spwp.pattern.factory.fm;

/**
 * Create by Tifone on 2019/6/23.
 */
public class JsonManager implements DataManager {

    private static JsonManager INSTANCE;
    private JsonManager() {}

    public static JsonManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonManager();
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
