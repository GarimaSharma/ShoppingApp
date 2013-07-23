package com.example.ShoppingApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;

public class ShoppingApplication extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        gridView.setAdapter(new ShoppingItemsListingAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(getApplicationContext(), FullViewActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }
}
