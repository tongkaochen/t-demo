package com.tifone.spwp.pattern.factory.af;

/**
 * Create by Tifone on 2019/6/23.
 */
public class AFClient {
    public void runXml() {
        IDataFactory xmlFactory = FactoryManager.createXmlFactory();
        DataWriter xmlWriter = xmlFactory.getWriter();
        DataReader xmlReader = xmlFactory.getReader();
        xmlWriter.delete();
        xmlWriter.insert();
        xmlWriter.modify();
        xmlReader.query();
    }
    public void runJson() {
        IDataFactory jsonFactory = FactoryManager.createJsonFactory();
        DataWriter jsonWriter = jsonFactory.getWriter();
        DataReader jsonReader = jsonFactory.getReader();
        jsonWriter.delete();
        jsonWriter.insert();
        jsonWriter.modify();
        jsonReader.query();
    }

    public void runDatabase() {
        IDataFactory databaseFactory = FactoryManager.createDatabaseFactory();
        DataWriter databaseWriter = databaseFactory.getWriter();
        DataReader databaseReader = databaseFactory.getReader();
        databaseWriter.delete();
        databaseWriter.insert();
        databaseWriter.modify();
        databaseReader.query();
    }

}
