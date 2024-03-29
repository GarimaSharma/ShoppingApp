package com.example.ShoppingApplication.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

public class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {

    ImageView imageView = null;
    private Context context;
    private static final String IMAGE_CACHING_PREFERENCES_FILE_NAME = "ShoppingImageCachingPreferences";
    private String rawImageUrl;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        this.context = imageView.getContext();
        rawImageUrl = (String) imageView.getTag();
        try {
            return getImage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        Log.d("After Image download Time - ", DateFormat.getDateTimeInstance().format(new Date()));
        imageView.setImageBitmap(result);
    }

    private Bitmap getImage() throws FileNotFoundException {
        ImageDownloader imageDownloader = new ImageDownloader();
        SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
        String fileName = cachingSharedPreferences.getString(rawImageUrl, "");
        return !fileName.isEmpty() ? BitmapFactory.decodeStream(inputFileStream(fileName)) : downloadAndCacheImage(imageDownloader);
    }

    private Bitmap downloadAndCacheImage(ImageDownloader imageDownloader) {
        Bitmap bitmap = imageDownloader.downloadImage(rawImageUrl);
        cacheImage(bitmap);
        return bitmap;
    }

    private void cacheImage(Bitmap result) {
        String fileName = "product_" + System.currentTimeMillis() + ".png";
        try {
            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
            result.compress(Bitmap.CompressFormat.PNG, 90, imageBytes);
            OutputStream imageOutputStream = outputFileStream(fileName);
            imageOutputStream.write(imageBytes.toByteArray());
            SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
            SharedPreferences.Editor editor = cachingSharedPreferences.edit();

            editor.putString(rawImageUrl, fileName);
            editor.commit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileInputStream inputFileStream(String fileName) throws FileNotFoundException {
        if (isExternalStorageUsed())
            return new FileInputStream(context.getExternalCacheDir() + "/" + fileName);
        else
            return context.openFileInput(fileName);
    }

    private FileOutputStream outputFileStream(String fileName) throws FileNotFoundException {
        if (isExternalStorageUsed())
            return new FileOutputStream(context.getExternalCacheDir() + "/" + fileName);
        else
            return context.openFileOutput(fileName, 0);
    }

    private boolean isExternalStorageUsed(){
        SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
        String isExternalMediaUsedKey = "isExternalMediaUsed";
        if (!cachingSharedPreferences.contains(isExternalMediaUsedKey)) {
            boolean mExternalStorageAvailable = false;
            boolean mExternalStorageWriteable = false;
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                mExternalStorageAvailable = mExternalStorageWriteable = true;
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                mExternalStorageAvailable = true;
                mExternalStorageWriteable = false;
            } else {
                // Something else is wrong. It may be one of many other states, but all we need
                //  to know is we can neither read nor write
                mExternalStorageAvailable = mExternalStorageWriteable = false;
            }
            cachingSharedPreferences.edit().putBoolean(isExternalMediaUsedKey,mExternalStorageAvailable && mExternalStorageWriteable).commit();
        }
        return cachingSharedPreferences.getBoolean(isExternalMediaUsedKey, false);


    }

}

