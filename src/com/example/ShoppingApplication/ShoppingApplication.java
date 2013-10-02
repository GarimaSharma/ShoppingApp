package com.example.ShoppingApplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.repository.ProductRepository;
import com.example.ShoppingApplication.services.Callback;
import com.example.ShoppingApplication.services.JsonDeserializer;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ShoppingApplication extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        final ProgressDialog progressDialog = ProgressDialog.show(ShoppingApplication.this, "", "Loading...", true, true);
        final GridView gridView = (GridView) findViewById(R.id.grid_view);

        Time time = new Time();
        time.setToNow();
        Log.d("Start Time - ", DateFormat.getDateTimeInstance().format(new Date()));

        final Callback<Product> productCallback = downloadProductInfo(progressDialog, gridView);
        ProductRepository productRepo = new ProductRepository(getApplicationContext());
        productRepo.downloadProductInfo(productCallback);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FullViewActivity.class);
                Product product = (Product) v.getTag();
                intent.putExtra("description", product.getDescription());
                intent.putExtra("image_url", product.getImagePath());
                startActivity(intent);
            }
        });
    }

    private Callback<Product> downloadProductInfo(final ProgressDialog dialog, final GridView gridView) {
        return new Callback<Product>() {
            @Override
            public void execute() {
                Log.d("After JSON Deserialization Time - ", DateFormat.getDateTimeInstance().format(new Date()));
                gridView.setAdapter(new ShoppingItemsListingAdapter(ShoppingApplication.this));
                dialog.dismiss();
            }
        };
    }

}
