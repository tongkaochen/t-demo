package com.tifone.demo.pattern.factory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create by Tifone on 2019/6/22.
 */
public class StoreFactoryTest {

    @Test
    public void test_fileStore() {
        IStore store = StoreFactory.createFileStore();
        assertEquals(store.getClass(), FileStore.class);
        store.saveAudio();
        store.saveImage();
        store.saveText();
        store.saveVideo();
    }

    @Test
    public void test_databaseStore() {
        IStore store = StoreFactory.createDatabaseStore();
        assertEquals(store.getClass(), DatabaseStore.class);
        store.saveAudio();
        store.saveImage();
        store.saveText();
        store.saveVideo();
    }

    @Test
    public void test_webStore() {
        IStore store = StoreFactory.createWebStore();
        assertEquals(store.getClass(), WebStore.class);
        store.saveAudio();
        store.saveImage();
        store.saveText();
        store.saveVideo();
    }

}