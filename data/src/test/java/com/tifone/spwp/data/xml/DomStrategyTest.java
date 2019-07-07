package com.tifone.spwp.data.xml;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by Tifone on 2019/6/29.
 */
public class DomStrategyTest {

    @Test
    public void parse() {
        DomStrategy strategy = DomStrategy.getInstance();
        File testResource = new FileHelper().getRawFile("cooking_menu.xml");
        String[] expect = new String[] {"青椒炒蛋", "腐竹炒肉"};
        try {
            InputStream inputStream = new FileInputStream(testResource);
            List<MenuBean> result = strategy.parse(inputStream);
            assertNotNull(result);
            int i = 0;
            for (MenuBean bean : result) {
                System.out.println(bean);
                System.out.println(bean.getMaterials().size());
                assertEquals(bean.name, expect[i++]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("fail for error");
        }
    }
}