package com.tifone.demo.pattern.factory;

/**
 * Create by Tifone on 2019/6/22.
 */
public class StoreFactory {
    public static IStore createFileStore() {
        return new FileStore();
    }
    public static IStore createDatabaseStore() {
        return new DatabaseStore();
    }
    public static IStore createWebStore() {
        return new WebStore();
    }
}
