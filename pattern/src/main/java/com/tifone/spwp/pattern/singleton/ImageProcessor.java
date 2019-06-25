package com.tifone.spwp.pattern.singleton;

/**
 * Create by Tifone on 2019/6/25.
 */
public class ImageProcessor {
    private static volatile ImageProcessor INSTANCE_LAZY;
    private static ImageProcessor INSTANCE_HUNGRY;
    private  ImageProcessor(){}

    /**
     * 懒汉模式，采用volatile和synchronized保证线程安全
     * 访问时，每次都要检查线程同步，因此影响性能和消耗资源
     */
    public static synchronized ImageProcessor getInstanceLazy() {
        if (INSTANCE_LAZY == null) {
            INSTANCE_LAZY = new ImageProcessor();
        }
        return INSTANCE_LAZY;
    }

    /**
     * 饿汉模式
     * 在类加载时即创建对象，保证每次均能得到非空实例
     * 缺点：及时不适用该类，依然会存在对象
     */
    public static ImageProcessor getInstanceHungry() {
        return INSTANCE_HUNGRY;
    }
}
