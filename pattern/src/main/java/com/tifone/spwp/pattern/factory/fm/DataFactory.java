package com.tifone.spwp.pattern.factory.fm;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DataFactory {
    public static DataManager getXmlManager() {
        return XmlManager.getInstance();
    }
    public static DataManager getJsonManager() {
        return JsonManager.getInstance();
    }
    public static DataManager getDatabaseManager() {
        return DatabaseManager.getInstance();
    }
}
