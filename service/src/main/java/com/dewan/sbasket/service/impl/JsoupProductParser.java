package com.dewan.sbasket.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.dewan.sbasket.domain.Product;
import com.dewan.sbasket.service.api.ProductParserService;
import com.dewan.sbasket.service.api.ProductParserService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupProductParser implements ProductParserService {

    @Override
    public List<Product> parseProducts(String productsUrl) {
        List<Product> products = null;
        try {
            URL url = new URL(productsUrl);
            Document document = JsoupDocumentProvider.getHtmlDocument(url);
            products = getProductsFromDocument(document);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    private List<Product> getProductsFromDocument(Document document) {
        Elements elements = document.getElementsByClass("product");
        List<Product> products = new ArrayList<>();
        for(Element elm : elements) {
            products.add(getProduct(elm));
        }
        return products;
    }

    private Product getProduct(Element productElement) {
        Element productInfo = productElement
                .select("div.productNameAndPromotions").first().select("a").first();
        String title = productInfo.text();
        String href = productInfo.attr("abs:href");
        BigDecimal unitPrice = getUnitPrice(productElement);
        ProdExtraInfo extraInfo = getExtraInfo(href);
        Product product = new Product(title, unitPrice, extraInfo.getDescription(), extraInfo.getKcal());
        return product;
    }

    private BigDecimal getUnitPrice(Element productElement) {
        String unitPrice = productElement
                .select("p.pricePerUnit").first().text().substring(1);
        BigDecimal bigUnitPrice = null;
        if(!unitPrice.isEmpty()) {
            bigUnitPrice = BigDecimal.valueOf(Double.valueOf(unitPrice.substring(0, unitPrice.indexOf("/"))));
        }
        return bigUnitPrice;
    }

    ProdExtraInfo getExtraInfo(File productDetails) {
        Document document = JsoupDocumentProvider.getHtmlDocument(productDetails, null);
        return getExtraInfo(document);
    }

    ProdExtraInfo getExtraInfo(String productUrl) {
        ProdExtraInfo info = null;
        Document doc = null;
        try {
            doc = JsoupDocumentProvider.getHtmlDocument(new URL(productUrl));
            info = getExtraInfo(doc);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return info;
    }

    private ProdExtraInfo getExtraInfo(Document doc) {
        String description = doc.getElementsByClass("productText").first().select("p").text().trim();
        Elements nutritionTableElements = doc.select("table.nutritionTable");
        String kcal = "";
        if(nutritionTableElements != null && nutritionTableElements.first() != null) {
            kcal = nutritionTableElements.first().select("tr").get(2).select("td").first().text();
        }
        int numKcal = 0;
        if(!kcal.isEmpty()) {
            if(kcal.contains("kcal")) {
                kcal = kcal.substring(0, kcal.indexOf("kcal"));
            }
            numKcal = Integer.valueOf(kcal);
        }
        ProdExtraInfo info = new ProdExtraInfo(description, numKcal);
        return info;
    }

    List<Product> getProducts(File html) {
        Document document = JsoupDocumentProvider.getHtmlDocument(html, null);
        return getProductsFromDocument(document);
    }

    class ProdExtraInfo {
        int kcal;
        String description;
        ProdExtraInfo(String description, int kcal) {
            this.kcal = kcal;
            this.description = description;
        }
        public int getKcal() {
            return kcal;
        }

        public String getDescription() {
            return description;
        }
    }
}
