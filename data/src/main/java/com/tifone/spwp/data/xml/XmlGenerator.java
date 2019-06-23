package com.tifone.spwp.data.xml;

/**
 * Create by Tifone on 2019/6/23.
 */
public class XmlGenerator {
    private static XmlGenerator INSTANCE;
    private XmlGenerator() {}

    public static XmlGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlGenerator();
        }
        return INSTANCE;
    }
}
