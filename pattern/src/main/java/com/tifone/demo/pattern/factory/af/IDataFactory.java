package com.tifone.demo.pattern.factory.af;

/**
 * Create by Tifone on 2019/6/23.
 */
public interface IDataFactory {
    DataReader getReader();
    DataWriter getWriter();
}
