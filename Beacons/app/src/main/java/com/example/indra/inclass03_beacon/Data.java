package com.example.indra.inclass03_beacon;

/**
 * Created by indra on 9/8/16.
 */
public class Data {

    @Override
    public String toString() {
        return "Data{" +
                "discount=" + discount +
                ", product_Name='" + product_Name + '\'' +
                ", Region='" + Region + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                '}';
    }

    String product_Name, Region, img;
    int discount;
    float price;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }
}
