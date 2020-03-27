package com.example.factsafrica.ui.db;

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
        public static final String COLUMN_MULTIPLIED_TOTAL = "quantity_amount";
        public static final String COLUMN_SUB_TOTAL = "sub_total";
        public static final String COLUMN_DISCOUNT = "discount";
        public static final String COLUMN_VAT = "vat";
        public static final String COLUMN_NET_TOTAL = "total";
        public static final String COLUMN_STATUS = "status";

    }
}
