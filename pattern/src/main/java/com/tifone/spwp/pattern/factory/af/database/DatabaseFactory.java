package com.tifone.spwp.pattern.factory.af.database;

import com.tifone.spwp.pattern.factory.af.IDataFactory;
import com.tifone.spwp.pattern.factory.af.DataReader;
import com.tifone.spwp.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DatabaseFactory implements IDataFactory {
    @Override
    public DataReader getReader() {
        return DatabaseReader.getInstance();
    }

    @Override
    public DataWriter getWriter() {
        return DatabaseWriter.getInstance();
    }
}
