package com.example.ShoppingApplication.repository;

import android.content.Context;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.storage.DataStorage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductRepository {

    private DataStorage dataStorage;
    public ProductRepository(Context context) {
        dataStorage = new DataStorage(context);
    }

    public void createProduct(JSONObject x) throws JSONException {
        Product product = new Product(x.getString("title"),x.getString("description"),x.getString("image_url"));
        dataStorage.store(product);
    }

    public List<Product> getProducts() {
        return dataStorage.get();
    }
}
