package com.example.ShoppingApplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class ShoppingApplication extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageAdapter(this));
    }
}
