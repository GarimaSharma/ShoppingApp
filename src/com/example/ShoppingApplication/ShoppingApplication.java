package com.example.ShoppingApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.repository.ProductRepository;
import com.example.ShoppingApplication.services.JsonDeserializer;

import java.util.List;

public class ShoppingApplication extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        JsonDeserializer jsonDeserializer = new JsonDeserializer(getApplicationContext());
        jsonDeserializer.deserializeJson();

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ShoppingItemsListingAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FullViewActivity.class);
                ProductRepository productRepository = new ProductRepository(getApplicationContext());
                List<Product> products = productRepository.getProducts();
                intent.putExtra("description", products.get(position).getDescription());
                intent.putExtra("image_url", products.get(position).getImagePath());
                startActivity(intent);
            }
        });
    }
}
