package com.mealer.mealer;

import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class Distance {
    public static void main(final String[] args) throws IOException, JSONException {
        final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ru");
        params.put("mode", "driving"); //driving, walking, bicycling
        params.put("origin", "Kraków, Bonarka");

        params.put("destination", "Kraków, Sliska 14");

        final String url = baseUrl + '?' + JSONReader.encodeParams(params);
        final JSONObject response = JSONReader.read(url);

        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        final String distance = location.getJSONObject("distance").getString("text");
        final String duration = location.getJSONObject("duration").getString("text");
        System.out.println(distance + "\n" + duration);
    }
}
