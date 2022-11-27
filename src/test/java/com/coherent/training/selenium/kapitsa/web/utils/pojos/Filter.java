package com.coherent.training.selenium.kapitsa.web.utils.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("shop")
    private String shop;
    @JsonProperty("type")
    private String type;
    @JsonProperty("color")
    private String color;
    @JsonProperty("pickUpInOneClick")
    private String pickUpInOneClick;

    public Filter() {
    }

    public Filter(String vendor, String shop, String type, String color, String pickUpInOneClick) {
        this.vendor = vendor;
        this.shop = shop;
        this.type = type;
        this.color = color;
        this.pickUpInOneClick = pickUpInOneClick;
    }

    public String getVendor() {
        return vendor;
    }

    public String getShop() {
        return shop;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getPickUpInOneClick() {
        return pickUpInOneClick;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPickUpInOneClick(String pickUpInOneClick) {
        this.pickUpInOneClick = pickUpInOneClick;
    }
}
