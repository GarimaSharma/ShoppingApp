package com.example.ShoppingApplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ShoppingApplication.adapters.ShoppingItemsListingAdapter;
import com.example.ShoppingApplication.services.ImageDownloader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FullViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String imageUrl = extras.getString("image_url");
        TextView imageTitle = (TextView) findViewById(R.id.input_label);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageTitle.setText(title);
        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap bitmap = imageDownloader.downloadImage(imageUrl);
        imageView.setImageBitmap(bitmap);
    }

}
