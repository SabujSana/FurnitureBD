package com.example.sabuj.furniturebd.models;

import android.view.Display;

import java.io.Serializable;

public class ModelData implements Serializable{
    private String image;
    private String model;
    private String price;

    public ModelData() {
    }

    public ModelData(String image, String model, String price) {
        this.image = image;
        this.model = model;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

