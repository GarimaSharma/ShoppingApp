package com.example.ShoppingApplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FullViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        Intent i = getIntent();

        int position = i.getExtras().getInt("id");
        //ShoppingItemsListingAdapter imageAdapter = new ShoppingItemsListingAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        TextView title =(TextView) findViewById(R.id.input_label);


//        String str;
//
//        JSONObject objEnvelopeJSON = new JSONObject();
//
//        try {
//            JSONObject x = new JSONObject();
//            x.put("a", "b");
//            objEnvelopeJSON.put("trial",x);
//
//
//        JSONObject objEnvelopeData = objEnvelopeJSON
//                .getJSONObject("trial");
//
//        str = objEnvelopeData.getString("a");
//        title.setText(str);
//
//
//        } catch (JSONException e1) {
//            e1.printStackTrace();
//        }

        ImageDownloader imageDownlaoder = new ImageDownloader();
        //Bitmap bitmap = imageDownlaoder.downloadImage(imageAdapter.mThumbIds.get(position));
       // imageView.setImageBitmap(bitmap);
    }

    private String processEntity(HttpEntity entity)
            throws IllegalStateException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(entity
                .getContent()));
        String line, result = "";

        while ((line = br.readLine()) != null)
            result += line;
        br.close();
        return result;
    }
}
