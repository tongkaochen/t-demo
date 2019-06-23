package com.tifone.spwp.pattern.factory.fm;

/**
 * Create by Tifone on 2019/6/23.
 */
public class FMClient {
    public void runXml() {
        DataManager manager = DataFactory.getXmlManager();
        manager.query();
        manager.delete();
        manager.insert();
        manager.modify();
    }
    public void runJson() {
        DataManager manager = DataFactory.getXmlManager();
        manager.query();
        manager.delete();
        manager.insert();
        manager.modify();
    }

    public void runDatabase() {
        DataManager manager = DataFactory.getXmlManager();
        manager.query();
        manager.delete();
        manager.insert();
        manager.modify();
    }
}
