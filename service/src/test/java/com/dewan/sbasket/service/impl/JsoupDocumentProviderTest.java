package com.dewan.sbasket.service.impl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsoupDocumentProviderTest {

    private static final String SIMPLE_HTML = "<html><head><title>Test html parse</title></head>"
            + "<body><p>Parsed HTML into a doc.</p></body></html>";
    static final String TEST_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Before
    public void setup() {
    }

    @Test
    public void shouldConstructDocumentFromString() {
        Document doc = JsoupDocumentProvider.getHtmlDocument(SIMPLE_HTML);
        assertThat(doc, notNullValue());
        assertThat(doc.title(), is("Test html parse"));
    }

    @Test
    public void shouldConstructDocumentFromURL() {
        //Document doc = JsoupDocumentBuilder.getHtmlDocument(givenURL("https://www.google.com/"), proxy);
        Document doc = JsoupDocumentProvider.getHtmlDocument(givenURL(TEST_URL));
        assertThat(doc, notNullValue());
    }

    private URL givenURL(String url) {
        URL givenUrl = null;
        try {
            givenUrl = new URL(url);
        } catch (MalformedURLException urlException) {
            urlException.printStackTrace();
        }
        return givenUrl;
    }
}
