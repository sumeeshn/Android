package com.example.sumeesh.beacon1;

/**
 * Created by sumeesh on 08/09/16.
 */
public class Product {

    String product_Name, Region, img;
    int discount;
    float price;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_Name='" + product_Name + '\'' +
                ", Region='" + Region + '\'' +
                ", img='" + img + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}
