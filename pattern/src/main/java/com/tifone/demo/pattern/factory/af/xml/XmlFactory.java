package com.tifone.demo.pattern.factory.af.xml;

import com.tifone.demo.pattern.factory.af.IDataFactory;
import com.tifone.demo.pattern.factory.af.DataReader;
import com.tifone.demo.pattern.factory.af.DataWriter;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlFactory implements IDataFactory {
    @Override
    public DataReader getReader() {
        return XmlReader.getInstance();
    }

    @Override
    public DataWriter getWriter() {
        return XmlWriter.getInstance();
    }
}
