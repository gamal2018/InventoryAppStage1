package com.example.hamza.inventoryappstage1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.hamza.inventoryappstage1.Data.ProductContract.ProductEntry;
import com.example.hamza.inventoryappstage1.Data.ProductDbHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    ProductDbHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertData();
        queryData();
    }
//method to insert data in database
    private void insertData() {
        try {
            ContentValues values = new ContentValues();
            values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Introduction to Android");
            values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 100);
            values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 5);
            values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Gamal AbdAllah");
            values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "+201222255798");
            helper = new ProductDbHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            long insertString = db.insert(ProductEntry.TABLE_NAME, null, values);
            Log.v(LOG_TAG, "New Product save with row ID :" + insertString);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error insert new product", e);
        }
    }

    //method to read data from database
    private void queryData() {
        helper = new ProductDbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);

        StringBuilder display = new StringBuilder();

        try {

            display.append("The products table contains " + cursor.getCount() + " Product.\n\n");
            display.append(ProductEntry._ID + " - " +
                    ProductEntry.COLUMN_PRODUCT_NAME + " - " +
                    ProductEntry.COLUMN_PRODUCT_PRICE + " - " +
                    ProductEntry.COLUMN_PRODUCT_QUANTITY + " - " +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " - " +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                display.append(("\n" + currentID + " - " +
                        currentProductName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhone));
            }
            Log.v(LOG_TAG, display.toString());
        }
        catch (Exception e)
        {
            Log.e(LOG_TAG, "Error read data from store database", e);
        }
        finally {
            cursor.close();
        }
    }
}





