package com.example.ShoppingApplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;
import com.example.ShoppingApplication.services.JsonDeserializer;

public class ShoppingApplication extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        JsonDeserializer jsonDeserializer = new JsonDeserializer(getApplicationContext());
        jsonDeserializer.deserializeJson();

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ShoppingItemsListingAdapter(this));

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//
//                Intent i = new Intent(getApplicationContext(), FullViewActivity.class);
//                i.putExtra("id", position);
//                startActivity(i);
//            }
//        });
    }
}
