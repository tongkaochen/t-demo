package com.tifone.spwp.data.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Create by Tifone on 2019/6/23.
 */
public class DomStrategy implements ParserStrategy<List<MenuBean>> {
    private static DomStrategy INSTANCE;
    private DocumentBuilder mDocumentBuilder;
    private DomStrategy() {
        // 得到DocumentBuilderFactory的工厂实例，其实现为单例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 通过DocumentBuilder的工厂实例创建DocumentBuilder(Dom解析器)
        try {
            mDocumentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static DomStrategy getInstance() {
        if (INSTANCE == null) {
            synchronized (DomStrategy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DomStrategy();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public List<MenuBean> parse(InputStream is) {
        // 解析传入的文件输入流
        // 解析完成后返回Document实例，其保存了解析结果
        Document document = null;
        try {
            document = mDocumentBuilder.parse(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if (document == null) {
            return null;
        }
        return getDataFrom(document);
    }

    private void resolvePriceList(MenuBean bean, Node priceNode) {
        // 取出pricelist子节点
        NodeList priceNodeList = priceNode.getChildNodes();
        for (int priceIndex = 0; priceIndex < priceNodeList.getLength(); priceIndex++) {
            Node priceItem = priceNodeList.item(priceIndex);
            logd("priceItem: " + priceItem.getNodeName() + " type = " + priceItem.getNodeType());
            if (priceItem.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap priceMap = priceItem.getAttributes();
                // 得到所有price值，及其id
                Node priceIdNode = priceMap.getNamedItem("id");
                int priceId = 0;
                if (priceIdNode != null) {
                    priceId = Integer.valueOf(priceIdNode.getNodeValue());
                    logd("priceIdStr: " + priceId);
                }
                // add price to menubean
                String priceValue = priceItem.getTextContent();
                bean.addPrice(bean.createPrice(priceId, Integer.valueOf(priceValue)));
            }
        }
        // pricelist结束
    }

    private void resolveMaterialList(MenuBean bean, Node materialListNode) {
        // 取出materiallist子节点列表
        NodeList materialNodeList = materialListNode.getChildNodes();
        for (int materialId = 0; materialId < materialNodeList.getLength(); materialId++) {
            // 得到每个material节点
            Node materialNode = materialNodeList.item(materialId);
            logd("materialNode: " + materialNode);
            if (materialNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // 遍历每一个material
            NodeList materialItemList = materialNode.getChildNodes();
            NamedNodeMap idMap = materialNode.getAttributes();
            String idValue = idMap.getNamedItem("id").getNodeValue();
            String name = "";
            int id = Integer.valueOf(idValue);
            int price = 0;
            String usageType = "";
            int usageValue = 0;
            String comment = "";
            for (int materialIndex = 0; materialIndex < materialItemList.getLength(); materialIndex++) {
                Node materialItemNode = materialItemList.item(materialIndex);
                if (materialItemNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                // 取出material的name、price、usage和usage的type，comment
                String materialItemNodeName = materialItemNode.getNodeName();
                String materialItemValue = materialItemNode.getTextContent();
                logd("materialNodeName: " + materialItemNodeName + " value: " + materialItemValue);
                if ("name".equals(materialItemNodeName)) {
                    // material name
                    name = materialItemValue;
                } else if ("price".equals(materialItemNodeName)) {
                    // material price
                    price = Integer.valueOf(materialItemValue);
                } else if ("usage".equals(materialItemNodeName)
                        && materialItemNode.getNodeType() == Node.ELEMENT_NODE) {
                    // material usage
                    NamedNodeMap usageMap = materialItemNode.getAttributes();
                    usageType = usageMap.getNamedItem("type").getNodeValue();
                    logd("usageType: " + usageType);
                    usageValue = Integer.valueOf(materialItemValue);
                } else if ("comment".equals(materialItemNodeName)) {
                    comment = materialItemValue;
                }
            }
            MenuBean.Material material = bean.createMaterial(id,name,price, comment, usageType, usageValue);
            bean.addMaterial(material);
        }
    }

    private MenuBean resolveMenuItem(Node menuNode) {
        MenuBean bean = null;
        if (menuNode.getNodeType() == Node.ELEMENT_NODE) {
            // 获取节点的属性列表
            NamedNodeMap nodeMap = menuNode.getAttributes();
            // 取出id值
            Node menuIdNode = nodeMap.getNamedItem("id");
            String menuIdStr = menuIdNode.getNodeValue();
            // create bean
            bean = new MenuBean(menuIdStr);
            logd("menuIdStr: " + menuIdStr);
        } else {
            return null;
        }
        // 读取menu节点的子节点列表
        NodeList menuItemList = menuNode.getChildNodes();
        //  遍历menu子节点列表
        for (int itemId = 0; itemId < menuItemList.getLength(); itemId++) {
            if (menuItemList.item(itemId).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            // 取出节点name、date、count
            Element menuItem = (Element) menuItemList.item(itemId);
            String itemName = menuItem.getNodeName();
            logd("menuItem: " + itemName );
            String itemValue = menuItem.getTextContent();
            logd("menuItem: " + itemName + " value: " + itemValue);
            if ("name".equals(itemName)) {
                // name
                bean.name = itemValue;
            } else if ("date".equals(itemName)) {
                // date
                bean.date = Integer.valueOf(itemValue);
            } else if ("count".equals(itemName)) {
                // count
                bean.count = Integer.valueOf(itemValue);
            } else if ("pricelist".equals(itemName)) {
                resolvePriceList(bean, menuItem);
            } else if ("materiallist".equals(itemName)) {
                resolveMaterialList(bean, menuItem);
            }
        } // menu遍历结束
        return bean;
    }
    private List<MenuBean> resolveMenuList(Node node) {
        List<MenuBean> result = new ArrayList<>();
        Element menuListElement = (Element) node;
        // 获取所有menu子节点列表
        NodeList menuNodeList = menuListElement.getChildNodes();
        // 遍历每个menu子节点
        for (int menuId = 0; menuId < menuNodeList.getLength(); menuId++) {
            // 取出menu节点的信息, 取出id值
            Node menuNode = menuNodeList.item(menuId);
            logd("menuNode = " + menuNode.getNodeName() + " type = " + menuNode.getNodeType());
            if (menuNode.getNodeType() == Node.ELEMENT_NODE) {
                MenuBean bean = resolveMenuItem(menuNode);
                result.add(bean);
            }
        }
        return result;
    }
    private List<MenuBean> getDataFrom(Document document) {
        // 获得menulist根节点数据
        Node documentNode = document.getDocumentElement();
        NodeList documentNodeList = documentNode.getChildNodes();
        for (int i = 0; i < documentNodeList.getLength(); i++) {
            // 得到menulist根节点
            Node node = documentNodeList.item(i);
            logd("type: " + node.getNodeType());
            logd("Node: " + node.getNodeName());
            if ("menulist".equals(node.getNodeName())) {
                return resolveMenuList(node);
            }
        }
        return null;
    }
    private void logd(String msg) {
        //System.out.println("DomStrategy: " + msg);
    }
}
