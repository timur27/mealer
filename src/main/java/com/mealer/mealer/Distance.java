package com.mealer.mealer;

import com.google.common.collect.Maps;
import com.mealer.mealer.Model.Shop;
import com.mealer.mealer.Repository.ShopRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Distance {
    @Autowired
    protected ShopRepository shopRepository;


    public List<List<String>> beforeDistances(String myLocation) throws IOException, JSONException{
        List<Shop> shops = shopRepository.findAll();
        List<List<String>> values = new ArrayList<>();
        for (Shop shop : shops){
            values.add(distances(myLocation, shop.getAddress()));
        }
        return values;
    }

    public List<String>  distances(String myLocation, String bLocation) throws IOException, JSONException {
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
        List<String> list = new ArrayList<>();
        list.add(distance);
        list.add(duration);
        return list;
    }
}
