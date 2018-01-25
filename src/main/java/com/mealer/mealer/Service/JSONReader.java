package com.mealer.mealer.Service;


import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import com.mealer.mealer.Model.Shop;
import com.mealer.mealer.Repository.ShopRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.StrictMath.sqrt;


@Service
public class JSONReader {
    @Autowired
    protected ShopRepository shopRepository;
    public Point a;
    public Point b;


    private static String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject read(final String url) throws IOException, JSONException {
        final InputStream is = new URL(url).openStream();
        try {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            final JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }

    private static final double EARTH_RADIUS = 6371.;

    public List<ShopInfo> beforeSearch(String myLocations) throws IOException {
        List<Shop> shopList = shopRepository.findAll();
        List<ShopInfo> distances = new ArrayList<>();
        for (Shop shop : shopList)
            distances.add(new ShopInfo(shop.getAddress(), findPlaces(myLocations, shop.getAddress()),b.lat,b.lng));

        return distances;
    }

    public double findPlaces(String myLocations, String shopAddresss) throws IOException {
        final Point myLocation = getPoint(myLocations);
        final Point shopAddress = getPoint(shopAddresss);
        this.a = myLocation;
        this.b = shopAddress;

//        final Point myLocation = new Point(50.032123, 18.24724);
//        final Point shopAddress = new Point(49.032123, 18.24724);



        final double dlng = deg2rad(myLocation.lng - shopAddress.lng);
        final double dlat = deg2rad(myLocation.lat - shopAddress.lat);

        final double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(shopAddress.lat))
                * cos(deg2rad(myLocation.lat)) * sin(dlng / 2) * sin(dlng / 2);
        final double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        System.out.println("distance: " + c * EARTH_RADIUS);
        return c*EARTH_RADIUS;
    }

    public static class Point {
        public double lat;
        public double lng;

        public Point(final double lng, final double lat) {
            this.lng = lng;
            this.lat = lat;
        }

        @Override
        public String toString() {
            return lat + "," + lng;
        }
    }

    public static Point getPoint(final String address) throws IOException, JSONException {
        final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";

        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");

        params.put("address", address);
        params.put("key", "AIzaSyAwki36PnXNTptksLUx_hUTzVufnsK3MXI");
        final String url = baseUrl + '?' + encodeParams(params);
        final JSONObject response = read(url);

        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");
        final double lat = location.getDouble("lat");
        final Point point = new Point(lng,lat);
        System.out.println(address + " " + point);
        return point;
    }

    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }
}