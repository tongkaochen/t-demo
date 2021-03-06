package com.tifone.demo.pattern.factory.af;

import com.tifone.demo.pattern.factory.af.database.DatabaseFactory;
import com.tifone.demo.pattern.factory.af.json.JsonFactory;
import com.tifone.demo.pattern.factory.af.xml.XmlFactory;

/**
 * Create by Tifone on 2019/6/23.
 */
public class FactoryManager {
    public static IDataFactory createXmlFactory() {
        return new XmlFactory();
    }
    public static IDataFactory createJsonFactory() {
        return new JsonFactory();
    }
    public static IDataFactory createDatabaseFactory() {
        return new DatabaseFactory();
    }
}
