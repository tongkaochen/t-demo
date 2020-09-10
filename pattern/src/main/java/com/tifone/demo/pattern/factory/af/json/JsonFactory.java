package com.tifone.demo.pattern.factory.af.json;

import com.tifone.demo.pattern.factory.af.IDataFactory;
import com.tifone.demo.pattern.factory.af.DataReader;
import com.tifone.demo.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class JsonFactory implements IDataFactory {
    @Override
    public DataReader getReader() {
        return JsonReader.getInstance();
    }

    @Override
    public DataWriter getWriter() {
        return JsonWriter.getInstance();
    }
}
