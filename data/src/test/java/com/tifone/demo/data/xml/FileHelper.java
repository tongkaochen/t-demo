package com.tifone.demo.data.xml;

import java.io.File;

/**
 * Create by Tifone on 2019/6/29.
 */
public class FileHelper {
    public final File getRawFile(String fileName) {
        File classPath = new File(getClass().getResource("").getFile());
        File parent = classPath;
        do {
            parent = parent.getParentFile();
        } while (!parent.toString().endsWith("t-spwp"));
        System.out.println(parent.toPath());
        return new File(parent, "data/src/main/res/raw/"+fileName);
    }
}
