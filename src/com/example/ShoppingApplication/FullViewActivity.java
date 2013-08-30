package com.example.ShoppingApplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ShoppingApplication.services.DownloadImagesTask;

public class FullViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        Bundle extras = getIntent().getExtras();
        String description = extras.getString("description");
        String imageUrl = extras.getString("image_url");
        TextView imageDescription = (TextView) findViewById(R.id.input_label);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageDescription.setText(description);
        imageView.setTag(imageUrl);
        new DownloadImagesTask().execute(imageView);
    }
}
