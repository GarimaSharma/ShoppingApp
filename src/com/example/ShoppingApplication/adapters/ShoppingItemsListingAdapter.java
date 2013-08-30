package com.example.ShoppingApplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ShoppingApplication.R;
import com.example.ShoppingApplication.model.Product;
import com.example.ShoppingApplication.repository.ProductRepository;
import com.example.ShoppingApplication.services.DownloadImagesTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingItemsListingAdapter extends BaseAdapter {
    private Context mContext;

    public List<Product> products = new ArrayList<Product>();

    public ShoppingItemsListingAdapter(Context c) {
        mContext = c;
        ProductRepository productRepository = new ProductRepository(mContext);
        products = productRepository.getProducts();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = products.get(position);

        LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.cell_layout, null);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView);
        TextView textView = (TextView) layout.findViewById(R.id.title);
        imageView.setTag(product.getImagePath());
        new DownloadImagesTask().execute(imageView);
        Log.d("After Image download Time - ", DateFormat.getDateTimeInstance().format(new Date()));
        textView.setText(product.getTitle());
        layout.setTag(product);
        return layout;
    }
}


