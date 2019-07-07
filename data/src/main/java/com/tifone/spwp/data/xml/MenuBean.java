package com.tifone.spwp.data.xml;

import android.view.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Create by Tifone on 2019/6/23.
 */
public class MenuBean {
    public String id;
    public int date;
    public String name;
    public int count;
    private List<Price> prices;
    private List<Material> materials;

    public MenuBean(String id) {
        prices = new ArrayList<>();
        materials = new ArrayList<>();
        this.id = id;

    }
    public MenuBean(String id, int date) {
        this(id);
        this.date = date;
    }

    public void addPrice(Price price) {
        int findId = -1;
        for (Price item : prices) {
            if (item.date == price.date) {
                findId = prices.indexOf(item);
                break;
            }
        }
        if (findId != -1) {
            prices.remove(findId);
        }
        prices.add(price);
        Collections.sort(prices, new Comparator<Price>() {
            @Override
            public int compare(Price o1, Price o2) {
                return o1.date - o2.date;
            }
        });
    }

    public Price createPrice(int id, int price) {
        return new Price(id, price);
    }
    public Material createMaterial(int id, String name, int price,
                                    String comment, String usageType, int usageValue) {
        return new Material(id,name, price, comment, usageType, usageValue);
    }

    public void addMaterial(Material material) {
        int findId = -1;
        for (Material item : materials) {
            if (item.id == material.id) {
                findId = materials.indexOf(item);
                break;
            }
        }
        if (findId != -1) {
            materials.remove(findId);
        }
        materials.add(material);
        Collections.sort(materials, new Comparator<Material>() {
            @Override
            public int compare(Material o1, Material o2) {
                return o1.id - o2.id;
            }
        });
    }
    public List<Material> getMaterials() {
        return materials;
    }

    class Price {
        int date;
        int value;
        Price(int date, int value) {
            this.date = date;
            this.value = value;
        }
    }
    class Material {
        int id;
        String name;
        int price;
        Usage usage;
        String comment;
        Material(int id, String name, int price, String comment, String usageType, int usageValue) {
            this.id = id;
            this.name = name;
            this.price = price;
            usage = new Usage(usageType, usageValue);
            this.comment = comment;
        }
    }
    class Usage {
        String type;
        int value;
        Usage(String type, int value) {
            this.type = type;
            this.value = value;
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Menu:[\n")
                .append("\tid:")
                .append(id)
                .append("\n\t name:")
                .append(name)
                .append("\n\t date:")
                .append(date)
                .append("\n\t count:")
                .append(count);
        builder.append("\n\t [prices: ");
        for (Price price : prices) {
            builder.append("\n\t\t date:")
                    .append(price.date)
                    .append("\n\t\t value:")
                    .append(price.value)
                    .append("]\n");
        }
        if (prices.size() == 0) {
            builder.append("]");
        }
        builder.append("\n\t [materials: ");
        for (Material material : materials) {
            builder.append("\n\t\t id:")
                    .append(material.id)
                    .append("\n\t\t name:")
                    .append(material.name)
                    .append("\n\t\t price:")
                    .append(material.price)
                    .append("\n\t\t [usage type:")
                    .append(material.usage.type)
                    .append("\n\t\t\t value:")
                    .append(material.usage.value)
                    .append("]")
                    .append("\n\t\t comment:")
                    .append(material.comment)
                    .append("]\n");
        }
        builder.append("]");
        return builder.toString();
    }
}
