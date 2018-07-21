package com.example.hamza.inventoryappstage1.Data;

import android.provider.BaseColumns;

public class ProductContract {
    ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {
        public final static String TABLE_NAME = "product";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "product_name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}
