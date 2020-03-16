package com.dewan.sbasket.service.impl;

import java.io.File;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupDocumentProvider {

    /**
     * Provides a Jsoup Document to be parse for products
     * @param url
     * @return Document
     */
    static Document getHtmlDocument(URL url) {
        Document doc = null;
        try {
            doc = Jsoup
                    .connect(url.toString())
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36")
                    .get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    static Document getHtmlDocument(String html) {
        return Jsoup.parse(html);
    }

    static Document getHtmlDocument(File html, String charset) {
        Document doc = null;
        try {
            doc = Jsoup.parse(html, charset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
}
