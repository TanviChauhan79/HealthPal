package com.tanvi.healthpal.ui.scanqr.apis.entity.elements;

public class NutritionalInfoFromApi {
    private String code;
    private Product product;
    private int status;
    private String status_verbose;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_verbose() {
        return status_verbose;
    }

    public void setStatus_verbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }
}
