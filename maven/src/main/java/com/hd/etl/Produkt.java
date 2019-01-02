package com.hd.etl;

import org.jsoup.nodes.Document;

public class Produkt {
    private long productID;
    private String productType = "";
    private String brand;
    private String model;
    private String notes;

    public Produkt(Document product, long id){
        //get product id
        productID = id;

        //get product type
        productType = product.select("span[data-category-id]").select("span[itemprop=title]").last().text();

        //get product brand
        brand = product.select("meta[property=og:brand]").attr("content");

        //get product model
        model = product.select("strong[class=js_searchInGoogleTooltip]").first().text();

        //get product notes
        notes = product.select("div[class=ProductSublineTags]").text();
    }

    public long getProductID() {
        return productID;
    }

    public String getProductType() {
        return Encoder.replaceAllSymbols(productType);
    }

    public String getBrand() {
        return Encoder.replaceAllSymbols(brand);
    }

    public String getModel() {
        return Encoder.replaceAllSymbols(model);
    }

    public String getNotes() {
        return Encoder.replaceAllSymbols(notes);
    }

    public String toString(){
        StringBuilder product = new StringBuilder();

        product.append("Produkt: " + productID + "\r\n");
        product.append(productType + " -> " + brand + "\r\n");
        product.append(model + "\r\n");
        product.append(notes + "\r\n\r\n\r\n");

        return product.toString();
    }
}
