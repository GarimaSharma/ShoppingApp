package com.example.ShoppingApplication.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.example.ShoppingApplication.model.Product;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;


public class DataStorage extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "products";
    private static final String TITLE_COL = "title";
    private static final String DESCRIPTION_COL = "description";
    private static final String DB_NAME = "shopping.db";
    private static final String IMAGEPATH_COL = "imagepath";

    public DataStorage(Context context) {
        super(context, DB_NAME, null, 1);
    }

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE_COL + " TEXT," +
            DESCRIPTION_COL + " TEXT, " +
            IMAGEPATH_COL+" TEXT );";


    public void store(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("insert into " + TABLE_NAME + " (" + TITLE_COL + "," + DESCRIPTION_COL + ","+ IMAGEPATH_COL +") values ( ?, ?, ?)");
        statement.bindString(1, product.getTitle());
        statement.bindString(2, product.getDescription());
        statement.bindString(3, product.getImagePath());
        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Product> get() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        List<Product> products = new ArrayList<Product>();
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String title = c.getString(c.getColumnIndex(TITLE_COL));
                    String description = c.getString(c.getColumnIndex(DESCRIPTION_COL));
                    String imagePath = c.getString(c.getColumnIndex(IMAGEPATH_COL));
                    Product product = new Product(title,description,imagePath);
                    products.add(product);
                }while (c.moveToNext());
            }
        }
        return products;
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

