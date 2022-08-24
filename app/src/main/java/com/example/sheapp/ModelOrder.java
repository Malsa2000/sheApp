package com.example.sheapp;

public class ModelOrder {
    String Image ,price , count , color ,name ,wight;
    int id;
    ModelOrder(){}

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWight() {
        return wight;
    }

    public void setWight(String wight) {
        this.wight = wight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelOrder(String image, String price, String count, String color, String name, String wight, int id) {
        Image = image;
        this.price = price;
        this.count = count;
        this.color = color;
        this.name = name;
        this.wight = wight;
        this.id = id;
    }
}
