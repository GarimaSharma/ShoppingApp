package com.example.ShoppingApplication.repository;

import android.content.Context;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.services.Callback;
import com.example.ShoppingApplication.services.JsonDeserializer;
import com.example.ShoppingApplication.services.ResourceDownloader;
import com.example.ShoppingApplication.storage.DataStorage;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ProductRepository {

    private DataStorage dataStorage;
    private final Context context;

    public ProductRepository(Context context) {
        this.context = context;
        dataStorage = new DataStorage(context);
    }

    public void createProduct(JSONObject x) throws JSONException {
        Product product = new Product(x.getString("title"),x.getString("description"),x.getString("image_url"));
        dataStorage.store(product);
    }

    public List<Product> getProducts() {
        return dataStorage.get();
    }

    public void clearDB() {
        dataStorage.clear();
    }

    public void downloadProductInfo(Callback<Product> productCallback) {

        executeAsyncTask(productCallback);
    }

    private void executeAsyncTask(Callback<Product> productCallback) {
        ResourceDownloader resourceDownloader = new  ResourceDownloader(productCallback,context);
        resourceDownloader.execute("http://xplorationstudio.com/sample_images/products_json.json");

    }

}
