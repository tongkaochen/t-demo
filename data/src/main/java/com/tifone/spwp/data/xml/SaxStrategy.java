package com.tifone.spwp.data.xml;

/**
 * Create by Tifone on 2019/6/23.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 */
public class SaxStrategy implements ParserStrategy {
    private static SaxStrategy INSTANCE;
    private SAXParser mParser;
    private XmlContentHandler mHandler;
    private SaxStrategy() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            mParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        mHandler = new XmlContentHandler();
    }
    public static SaxStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SaxStrategy();
        }
        return INSTANCE;
    }

    @Override
    public MenuBean parse(InputStream is) {
        logd("parse start");
        if (mParser == null || is == null) {
            return null;
        }
//        try {
//            int length = 0;
//            char[] data = new char[1024];
//            int d = 0;
//            while ((d=is.read()) != -1) {
//                data[length++] = (char)d;
//            }
//            System.out.println(new String(data));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            mParser.parse(is, mHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        logd("parse end");
        return null;
    }
    class XmlContentHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            logd("startDocument");
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            logd("endDocument");
        }

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            logd("startElement uri:" + uri + " localName: " + localName +
                    " qName:" + qName + " attributes:" + attributes.getValue(qName));
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            logd("characters ch:" + new String(ch, start, length) + " start: " + start +
                    " length:" + length);
        }
    }
    private void logd(String msg) {
        //LogUtil.logd(this, msg);
        System.out.println("SaxStrategy: " + msg);
    }
}
