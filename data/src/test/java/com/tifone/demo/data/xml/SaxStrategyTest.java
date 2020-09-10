package com.tifone.demo.data.xml;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Create by Tifone on 2019/6/23.
 */
public class SaxStrategyTest {

    @Test
    public void parse() {
        SaxStrategy saxStrategy = SaxStrategy.getInstance();
//        InputStream is = new FileInputStream();
//        saxStrategy.parse()
        File classPath = new File(getClass().getResource("").getFile());
        File parent = classPath;
        do {
            parent = parent.getParentFile();
        } while (!parent.toString().endsWith("t-spwp"));
        System.out.println(parent.toPath());
        // ""
        //new File("data/../../../../../../../../../../../../")
        // 读取xml文件内容
        File xml = new File(parent, "data/src/main/res/layout/activity_main.xml");
        InputStream is = null;
        try {
            is = new FileInputStream(xml);
            // 将内容传递给sax解析器
            saxStrategy.parse(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 等待处理结果
        // 核对读取结果是否正确
    }
}