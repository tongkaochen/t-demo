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
        if (mParser == null) {
            return null;
        }
        try {
            mParser.parse(is, mHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
    class XmlContentHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }
}
