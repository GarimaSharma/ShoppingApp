package com.example.ShoppingApplication.services;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.text.DateFormat;
import java.util.Date;

public class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {

    ImageView imageView = null;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        ImageDownloader imageDownloader = new ImageDownloader();
        return imageDownloader.downloadImage((String) imageView.getTag());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        Log.d("After Image download Time - ", DateFormat.getDateTimeInstance().format(new Date()));
        imageView.setImageBitmap(result);
    }
}

