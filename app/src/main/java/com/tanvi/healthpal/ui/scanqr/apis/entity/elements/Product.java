package com.tanvi.healthpal.ui.scanqr.apis.entity.elements;

public class Product {
    private Nutrients nutrients;
    private String product_name;
    private String image_front_small_url;
    private String image_front_thumb_url;
    private String image_front_url;

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_front_small_url() {
        return image_front_small_url;
    }

    public void setImage_front_small_url(String image_front_small_url) {
        this.image_front_small_url = image_front_small_url;
    }

    public String getImage_front_thumb_url() {
        return image_front_thumb_url;
    }

    public void setImage_front_thumb_url(String image_front_thumb_url) {
        this.image_front_thumb_url = image_front_thumb_url;
    }

    public String getImage_front_url() {
        return image_front_url;
    }

    public void setImage_front_url(String image_front_url) {
        this.image_front_url = image_front_url;
    }
}
