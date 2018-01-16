package com.mealer.mealer;


import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.StrictMath.sqrt;

public class JSONReader {
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

    public static void main(final String[] args) throws IOException, JSONException {
        final Point myLocation = getPoint("Россия, Москва, улица Поклонная, 12");
        final Point shopAddress = getPoint("Россия, Москва, станция метро Парк Победы");

        final double dlng = deg2rad(myLocation.lng - shopAddress.lng);
        final double dlat = deg2rad(myLocation.lat - shopAddress.lat);

        final double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(shopAddress.lat))
                * cos(deg2rad(myLocation.lat)) * sin(dlng / 2) * sin(dlng / 2);
        final double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        System.out.println("distance: " + c * EARTH_RADIUS);
    }

    private static class Point {
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

    private static Point getPoint(final String address) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";

        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");

        params.put("address", address);
        final String url = baseUrl + '?' + encodeParams(params);
        final JSONObject response = read(url);

        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");
        final double lat = location.getDouble("lat");
        final Point point = new Point(lng, lat);
        System.out.println(address + " " + point);
        return point;
    }

    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }
}
