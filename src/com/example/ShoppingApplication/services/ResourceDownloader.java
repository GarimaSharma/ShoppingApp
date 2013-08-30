package com.example.ShoppingApplication.services;

import android.content.Context;
import android.os.AsyncTask;
import com.example.ShoppingApplication.model.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;

public class ResourceDownloader extends AsyncTask<String,Void,Void> {

    private final Callback<Product> callback;
    private final Context context;

    public ResourceDownloader(Callback<Product> productCallback, Context context) {
        this.callback = productCallback;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(params[0]);

        HttpResponse resp = null;
        try {
            resp = httpClient.execute(httpGet);
            JsonDeserializer.deserializeJson(resp, context);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.execute();
    }
}
