package com.tifone.spwp.data.xml;

import java.io.InputStream;

/**
 * Create by Tifone on 2019/6/23.
 */
public interface ParserStrategy<T> {
    T parse(InputStream is);
}
