package com.mealer.mealer.Service;

public class ShopInfo {
    public String address;
    public double distance;
    public double lat;
    public double lng;

    public ShopInfo(String address, double distance, double lat, double lng){
        this.address = address;
        this.distance = distance;
        this.lat = lat;
        this.lng = lng;
    }
}
