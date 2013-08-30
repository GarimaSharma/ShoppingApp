package com.example.ShoppingApplication.services;

import android.content.Context;
import android.util.Log;
import com.example.ShoppingApplication.repository.ProductRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonDeserializer {

    public static void deserializeJson(HttpResponse resp, Context context) throws IOException, JSONException {
        String strJSONData;

        strJSONData = processEntity(resp.getEntity());

        JSONArray productInfo = new JSONArray(strJSONData);
        com.example.ShoppingApplication.repository.ProductRepository productRepository = new com.example.ShoppingApplication.repository.ProductRepository(context);
        productRepository.clearDB();


        for (int index = 0; index < productInfo.length(); index++) {
            JSONObject x = new JSONObject(productInfo.getString(index));
            productRepository.createProduct(x);

        }
    }

    private static String processEntity(HttpEntity entity) throws IllegalStateException, IOException { {

        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String line, result = "";

        while ((line = br.readLine()) != null)
            result += line;
        br.close();
        return result;
    }
}
}
