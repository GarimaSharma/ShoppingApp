package com.example.ShoppingApplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;
import com.example.ShoppingApplication.services.ImageDownloader;

public class FullViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        Intent i = getIntent();

        int position = i.getExtras().getInt("id");
        ShoppingItemsListingAdapter imageAdapter = new ShoppingItemsListingAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        ImageDownloader imageDownlaoder = new ImageDownloader();
        Bitmap bitmap = imageDownlaoder.downloadImage(imageAdapter.mThumbIds.get(position));

        imageView.setImageBitmap(bitmap);
    }
}
