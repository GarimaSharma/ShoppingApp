package com.example.ShoppingApplication.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ShoppingApplication.R;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.repository.ProductRepository;
import com.example.ShoppingApplication.services.ImageDownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ShoppingItemsListingAdapter extends BaseAdapter {
    private Context mContext;

    public List<String> mThumbIds = new ArrayList<String>();
    public List<Product> products = new ArrayList<Product>();

    public ShoppingItemsListingAdapter(Context c) {
        mContext = c;
        ProductRepository productRepository = new ProductRepository(mContext);
        products = productRepository.getProducts();
        for (Product product : products) {
            mThumbIds.add(product.getImagePath());
        }
    }

    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = new ImageView(mContext);
        ImageDownloader imageDownlaoder = new ImageDownloader();
        Bitmap bitmap = imageDownlaoder.downloadImage(mThumbIds.get(position));
//
//        imageView.setImageBitmap(bitmap);
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
//        return imageView;
        LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.cell_layout, null);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        return layout;

    }
}


