package com.example.vendor.db;

import android.provider.BaseColumns;

public final class InvoiceContract {

    public InvoiceContract() { }

    public static abstract class AddressEntry implements BaseColumns {

    public static final String TABLE_NAME = "address";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_BUSINESS_NAME = "business_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";
    }

    public static abstract class ItemsEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ITEM_NAME = "item_name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_QUANTITY = "quantity";
    }
}
